package logic.controller;

import java.sql.SQLException;
import java.util.logging.Logger;

import logic.bean.BookingBean;
import logic.bean.BookingPCBean;
import logic.bean.BookingSeatBean;
import logic.bean.LibraryBean;
import logic.constants.BookingConstants;
import logic.constants.CurrentDateTime;
import logic.dao.BookableItemDao;
import logic.dao.BookingDao;
import logic.dao.LibraryDao;
import logic.entity.BookableItem;
import logic.entity.BookableItemHandler;
import logic.entity.Booking;
import logic.entity.BookingPC;
import logic.entity.BookingSeat;
import logic.entity.Library;
import logic.exceptions.BookingException;
import logic.exceptions.InvalidTypeBookingException;
import logic.exceptions.InvalidTypeNoItemException;
import logic.exceptions.NoItemAvailableException;
import logic.exceptions.SelectLibraryFromDBException;
import logic.exceptions.StudentRegisteredYetException;
import logic.pattern.BookingFactory;
import logic.pattern.NoItemAvailableExceptionFactory;

public class BookingController {
	
	private LibraryBean libraryBean;
	private BookingBean bookingBean;
	private BookingDao bookingDao;
	private BookableItemDao bookableItemDao;
	private LibraryDao libraryDao;
	private Booking booking;
	
	private static Logger myLogger = Logger.getLogger("logger");

	public BookingController(LibraryBean libraryBean, BookingBean bookingBean) {
		this.libraryBean = libraryBean;
		this.bookingBean = bookingBean;
		bookableItemDao = null;
		libraryDao = null;
		booking = null;
	}

	public BookingController(BookingBean bookingBean) {
		this.libraryBean = null;
		this.bookingBean = bookingBean;
		bookableItemDao = null;
		libraryDao = null;
		booking = null;
	}

	public BookingController() {
	}

	public void book(String mailS, String mailL, String type) throws BookingException, SelectLibraryFromDBException, InvalidTypeBookingException, NoItemAvailableException, StudentRegisteredYetException {
		myLogger.info("Booking_controller: book");
		
		bookingDao = new BookingDao();
		
		/*check student bookings*/
		if(bookingDao.selectCountBookingsFromStudentId(mailS) != 0) {
			throw(new StudentRegisteredYetException());
		}
		
		/*check library availability*/
		libraryDao = new LibraryDao();
		libraryBean = libraryDao.selectLibraryFromId(mailL);
		Library libr = mapLibraryFromBean(libraryBean);
			
		/*prendo item label*/
		myLogger.info("Booking_controller: load item");
		BookableItem bookableItem = null;
		bookableItemDao = new BookableItemDao();
		bookableItem = bookableItemDao.selectAvailableItem(mailL, type);
		if(bookableItem == null) {
			myLogger.info("Booking controller: item (NULL) not available");
			NoItemAvailableExceptionFactory noItemAvFactory = new NoItemAvailableExceptionFactory();
			NoItemAvailableException exc = null;
			try {
				exc = noItemAvFactory.createNoItemAvailableException(type);
			} catch (InvalidTypeNoItemException e) {
				e.printStackTrace();
				myLogger.info("Booking controller: item invalid type");
			}
			throw(exc);
		}
		
		/*creazione prenotazione (GOF: Factory Method)*/
		myLogger.info("Booking_controller: booking creating");
		booking = null;
		BookingFactory bf = new BookingFactory();
		try {
			/* chiamata a factoryMethod(int type)*/
			booking = bf.createBooking(type);
		} catch (InvalidTypeBookingException e) {
			throw(new InvalidTypeBookingException(type));
		}
		
		booking.setMailBiblioteca(mailL);
		booking.setMailStudente(mailS);
		booking.setTipoPrenotazione(type);
		booking.setDataPrenotazione(CurrentDateTime.getCurrentDate());
		booking.setOrarioPrenotazione(CurrentDateTime.getCurrentTime());
		
		/*set booking values*/
		BookableItemHandler bookableItemHandler = new BookableItemHandler(booking, bookableItem);
		bookableItemHandler.setItemValuesToBooking(type);
		
		booking.hanging();
		bookableItem.busyItem();
		
		libr.visitorIn(type);
		libraryBean.setSedieOccupate(libr.getBusySeats());
		libraryBean.setPcOccupati(libr.getBusyPc());

		myLogger.info("Booking mail: " + booking.getMailBiblioteca() + " studente: " + booking.getMailStudente() + " data: " + booking.getDataPrenotazione() + " ora: " + booking.getOrarioPrenotazione() +	" item: " + booking.getItemInformation());
		
		/*salvo prenotazione*/
		if(!bookingDao.saveBooking(booking, type)) {
			myLogger.info("Booking controller: saveBooking failed");
		}
		
		/*aggiorno item*/
		try {
			bookableItemDao.updateBookableItem(bookableItem, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		libraryDao.updateLibrary(libraryBean);
		
		mapBeanFromBooking(booking, type);
	}

	/*
	 * mapper bean to entity
	 */
	private Library mapLibraryFromBean(LibraryBean libraryBean) {
		return new Library(
					libraryBean.getMailB(),
					libraryBean.getNameB(),
					libraryBean.getIndirizzoB(),
					libraryBean.getCittaB(),
					libraryBean.getTelefonoB(),
					String.valueOf(libraryBean.getTotalSedie()),
					String.valueOf(libraryBean.getTotPc()),
					String.valueOf(libraryBean.getSedieOccupate()),
					String.valueOf(libraryBean.getPcOccupati())
				);
	}
	
	/*
	 * mapper entity to bean
	 */
	private void mapBeanFromBooking(Booking booking, String type) {
		bookingBean.setDate(booking.getDataPrenotazione());
		bookingBean.setHour(booking.getOrarioPrenotazione());
		bookingBean.setStato(booking.getStatoPrenotazione());
		bookingBean.setTipo(booking.getTipoPrenotazione());		
		if(type.equals(BookingConstants.TYPESEAT)) {
			((BookingSeatBean)bookingBean).setRoom(((BookingSeat)booking).getRoom());
			((BookingSeatBean)bookingBean).setSeatNumber(((BookingSeat)booking).getSeatNum());
		} else if(type.equals(BookingConstants.TYPEPC)) {
			((BookingPCBean)bookingBean).setPcName(((BookingPC)booking).getPcName());
		}
	}

	public boolean unbook(String mailS, String type, String state) {
		
		boolean res = false;
		
		bookingDao = new BookingDao();
		
		/*check student bookings*/
		if(bookingDao.selectCountBookingsFromStudentId(mailS) == 0) {
			return res;
		}
		
		/*load booking e item*/
		Booking book = bookingDao.selectBookingStudent(mailS, state, type).get(0);
		
		/*booking.unbook e item.release*/
		book.deleted();
		BookableItem bookableItem = null;
		bookableItemDao = new BookableItemDao();
		if(book.getTipoPrenotazione().equals(BookingConstants.TYPESEAT)) {
			bookableItem = bookableItemDao.selectSeat(book.getMailBiblioteca(), ((BookingSeat)book).getRoom(), ((BookingSeat)book).getSeatNum());
		} else 
		if(book.getTipoPrenotazione().equals(BookingConstants.TYPEPC)) {
			bookableItem = bookableItemDao.selectPC(book.getMailBiblioteca(), ((BookingPC)book).getPcName());
		}
		
		bookableItem.releaseItem();
		
		/*save (UPDATE prenotazione e UPDATE item) on db*/
		bookingDao.updateBookingState(book);
		try {
			bookableItemDao.updateBookableItem(bookableItem, type);
			res = true;
		} catch (SQLException e) {
			myLogger.info("bookableItem update fallito, rettifico prenotazione");
			e.printStackTrace();
			bookableItem.releaseItem();
			book.hanging();
			bookingDao.updateBookingState(book);
		}
		
		return res;
	}

}
