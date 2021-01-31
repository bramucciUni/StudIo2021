package logic.boundary;

import java.util.logging.Logger;

import logic.bean.BookingBean;
import logic.constants.BookingConstants;
import logic.controller.BookingController;
import logic.exceptions.BookingException;
import logic.exceptions.InvalidTypeBookingException;
import logic.exceptions.NoItemAvailableException;
import logic.exceptions.SelectLibraryFromDBException;
import logic.exceptions.StudentRegisteredYetException;

public class BookingBoundary {
	
	protected static Logger myLogger = Logger.getLogger("logger");	
	private BookingController bookingController;
	private BookingBean bookingBean;
	
	public BookingBoundary(BookingBean bookingBean) {
		this.bookingBean = bookingBean;
	}
	
	public BookingBoundary() {
		//default constructor
	}

	public boolean book(String type) {
		boolean res = false;
		bookingController = new BookingController(bookingBean);
		
		try {
			myLogger.info("Boundary: calling book(" + bookingBean.getLibraryId() + bookingBean.getStudentId() + type + ")");
			bookingController.book(bookingBean.getStudentId(), bookingBean.getLibraryId(), type);
			bookingBean.setBookingAdvertise(BookingConstants.BOOKEDOK);
			res = true;
		} catch (BookingException | SelectLibraryFromDBException | InvalidTypeBookingException  e) {
			e.printStackTrace();
		} catch (StudentRegisteredYetException e) {
			bookingBean.setBookingAdvertise(e.getMessage()); 
			/*
			 * inserire msg relativo all'eccezione
			 */
		} catch (NoItemAvailableException e) {
			myLogger.info("Booking boundary: " + e.getErrorItemLoadingMessage());
			bookingBean.setBookingAdvertise(e.getErrorItemLoadingMessage());
		} 
		
		return res;
	}
	
	public boolean unbook(String mailS, String type, String state) {
		boolean res = false;
		bookingController = new BookingController();
		myLogger.info("Boundary: calling unbook");
		if(bookingController.unbook(mailS, type, state)) res = true;
		return res;
	}
}
