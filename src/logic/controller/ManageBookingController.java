package logic.controller;

import java.sql.SQLException;

import logic.bean.BookingBean;
import logic.bean.BookingPCBean;
import logic.bean.BookingSeatBean;
import logic.bean.LibraryBean;
import logic.constants.BookingConstants;
import logic.constants.CurrentDateTime;
import logic.dao.BookableItemDao;
import logic.dao.BookingDao;
import logic.dao.LibraryDao;
import logic.dao.MessageDao;
import logic.entity.BookableItem;
import logic.entity.Booking;
import logic.entity.Message;

public class ManageBookingController {
	
	private BookingBean bookingBean;
	private LibraryBean libraryBean;
	private Booking booking;
	private BookableItem bookableItem;
	private LibraryDao libraryDao;
	private BookingDao bookingDao;
	private BookableItemDao bookableItemDao;
	
	public ManageBookingController(BookingBean bookingBean) {
		this.bookingBean = bookingBean;
		this.libraryDao = new LibraryDao();
		this.bookingDao = new BookingDao();
		this.bookableItemDao = new BookableItemDao();
		this.libraryBean = null;
		this.booking = null;
		this.bookableItem = null;
	}

	public void validateBooking(String mailStudent, String currentDate, String currentHour, String stato) throws SQLException {
	
		try {
			/*select booking from db*/
			booking = bookingDao.selectBookingFromStudentAndDateTime(mailStudent, currentDate, currentHour).get(0);
		} catch (SQLException e) {
			bookingBean.setBookingAdvertise("ERROR: getting booking from database");
			throw(e);
		}
		
		if(stato.equals(BookingConstants.ACCEPTED)) {
			/*validate booking*/
			booking.accepted();
		} else if(stato.equals(BookingConstants.DECLINED)) {
			/*select item from db*/
			if(bookingBean.getTipo().equals(BookingConstants.TYPESEAT)) {
				bookableItem = bookableItemDao.selectSeat(bookingBean.getLibraryId(), ((BookingSeatBean)bookingBean).getRoom(), ((BookingSeatBean)bookingBean).getSeatNumber());
			} else if(bookingBean.getTipo().equals(BookingConstants.TYPEPC)) {
				bookableItem = bookableItemDao.selectPC(bookingBean.getLibraryId(), ((BookingPCBean)bookingBean).getPcName());
			}
			/*invalidate booking*/
			booking.declined();	
			/*release item*/
			bookableItem.releaseItem();	
			/*create message*/
			String messageText = "Your reservation (" + booking.getItemInformation() + ") has expired";
			Message message = new Message(bookingBean.getLibraryId(), mailStudent, messageText, CurrentDateTime.getCurrentDate(), CurrentDateTime.getCurrentTime());
			/*update item*/
			try {
				bookableItemDao.updateBookableItem(bookableItem, bookingBean.getTipo());
				MessageDao messageDao = new MessageDao();
				messageDao.saveMessage(message);
			} catch (SQLException e) {
				bookingBean.setBookingAdvertise("ERROR: update item to database");
				bookableItem.busyItem();
				booking.hanging();
				throw(e);
			}
			
			/*select library*/
			libraryBean = libraryDao.selectLibraryFromId(bookingBean.getLibraryId());
			
			/*update free item number*/
			if(bookingBean.getTipo().equals(BookingConstants.TYPESEAT)) {
				libraryBean.rimuoviSediaOccupata();
			} else if(bookingBean.getTipo().equals(BookingConstants.TYPEPC)) {
				libraryBean.rimuoviPcOccupato();
			}
			
			/*update library*/
			libraryDao.updateLibrary(libraryBean);
		}
		
		/*update booking*/
		bookingDao.updateBookingState(booking);
		
	}

}
