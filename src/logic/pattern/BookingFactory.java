package logic.pattern;

import logic.constants.BookingConstants;
import logic.entity.Booking;
import logic.entity.BookingPC;
import logic.entity.BookingSeat;
import logic.exceptions.InvalidTypeBookingException;

/*
 * GOF Factory Method
 * Classe con responsabilità di Creator nei confronti di BookingSeat/BookingPC
 */
public class BookingFactory {
	
	public BookingFactory() {
		/*
		 * default constructor
		 */
	}

	public Booking createBooking(String type) throws InvalidTypeBookingException {
		switch(type) {
			case BookingConstants.TYPESEAT:
				return createSeatBooking();
			case BookingConstants.TYPEPC:
				return createPCBooking();
			default: 
				throw new InvalidTypeBookingException("Invalid type selected: " + type);
		}
	}
	
	private Booking createSeatBooking() {
		return new BookingSeat();
	}	

	private Booking createPCBooking() {
		return new BookingPC();
	}
	
}
