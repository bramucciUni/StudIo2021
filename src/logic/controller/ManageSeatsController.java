package logic.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import logic.bean.AdvertiseBean;
import logic.bean.BookingBean;
import logic.bean.LibraryBean;
import logic.constants.BookingConstants;
import logic.dao.BookableItemDao;
import logic.dao.LibraryDao;
import logic.entity.BookableItem;
import logic.entity.PC;
import logic.entity.Seat;
import logic.exceptions.InvalidTypeNoItemException;
import logic.exceptions.NoItemAvailableException;
import logic.exceptions.NoItemException;
import logic.pattern.NoItemAvailableExceptionFactory;

public class ManageSeatsController {
	
	private static Logger myLogger = Logger.getLogger("logger");
	
	private LibraryBean myLibraryBean;
	private List<BookingBean> myLibraryBookings;
	private LibraryDao libraryDao;
	private BookableItem bookItem;
	private BookableItemDao bookableItemDao;
	
	public ManageSeatsController() {
		/* default constructor */
	}
	
	public ManageSeatsController(LibraryBean myLibraryBean, List<BookingBean> myLibraryBookings) {
		this.myLibraryBean = myLibraryBean;
		this.myLibraryBookings = myLibraryBookings;
		bookItem = null;
		libraryDao = null;
		bookableItemDao = null;
	}

	public boolean updateSeats() {
		boolean res = false;
		LibraryBean libraryBeanUpdated;
		libraryDao = new LibraryDao();
		libraryBeanUpdated = libraryDao.selectLibraryFromId(myLibraryBean.getMailB());
		if(libraryBeanUpdated != null) {
			myLibraryBean = libraryBeanUpdated;
			res = true;
		}
		return res;
	}

	public LibraryBean getMyLibraryBean() {
		return myLibraryBean;
	}

	public void setMyLibraryBean(LibraryBean myLibraryBean) {
		this.myLibraryBean = myLibraryBean;
	}

	public List<BookingBean> getMyLibraryBookings() {
		return myLibraryBookings;
	}

	public void setMyLibraryBookings(List<BookingBean> myLibraryBookings) {
		this.myLibraryBookings = myLibraryBookings;
	}

	public void visitorIn(String mailL, String type, AdvertiseBean advertiseBean) throws NoItemAvailableException, SQLException {
		NoItemAvailableExceptionFactory noItemExcFactory = null;
		NoItemAvailableException noItemExc = null;
		bookableItemDao = new BookableItemDao();
		bookItem = bookableItemDao.selectAvailableItem(mailL, type);
		if(bookItem == null) {
			myLogger.info("Booking ontroller: item unavailable");
			noItemExcFactory = new NoItemAvailableExceptionFactory();
			noItemExc = null;
			try {
				noItemExc = noItemExcFactory.createNoItemAvailableException(type);
			} catch (InvalidTypeNoItemException e) {
				e.printStackTrace();
				myLogger.info("Booking controller: noItemExcFactory invalid type");
			}
			throw(noItemExc);
		}
		bookItem.busyItem();
		try {
			bookableItemDao.updateBookableItem(bookItem, type);
			if(type.equals(BookingConstants.TYPESEAT)) {
				advertiseBean.setResult("Room: " + ((Seat)bookItem).getSeatRoom() + " - " + "Seat number: " + String.valueOf(((Seat)bookItem).getSeatNum()));
			} else if(type.equals(BookingConstants.TYPEPC)) {
				advertiseBean.setResult("PC name: " + ((PC)bookItem).getPcName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			bookItem.releaseItem();
			throw(e);
		}
	}
	
	public void seatVisitorOut(String mailL, String room, int seatNumber) throws SQLException, NoItemException {
		bookableItemDao = new BookableItemDao();
		bookItem = bookableItemDao.selectSeat(mailL, room, seatNumber);
		if(bookItem == null) {
			myLogger.info("Booking_controller: seat item (NULL) not valid");
			throw(new NoItemException());
		}
		bookItem.releaseItem();
		try {
			bookableItemDao.updateBookableItem(bookItem, BookingConstants.TYPESEAT);
		} catch (SQLException e) {
			e.printStackTrace();
			bookItem.releaseItem();
			throw(e);
		}
		
	}
	
	public void pcVisitorOut(String mailL, String pcName) throws SQLException, NoItemException {
		bookableItemDao = new BookableItemDao();
		bookItem = bookableItemDao.selectPC(mailL, pcName);
		if(bookItem == null) {
			myLogger.info("Booking_controller: PC item (NULL) not valid");
			throw(new NoItemException());
		}
		bookItem.releaseItem();
		try {
			bookableItemDao.updateBookableItem(bookItem, BookingConstants.TYPEPC);
		} catch (SQLException e) {
			e.printStackTrace();
			bookItem.releaseItem();
			throw(e);
		}
		
	}
	
}
