package logic.entity;

import logic.constants.BookingConstants;

public class BookableItemHandler {
	
	private BookableItem bookableItem;
	private Booking booking;
	
	public BookableItemHandler(Booking booking, BookableItem bookableItem) {
		this.bookableItem = bookableItem;
		this.booking = booking;
	}
	
	public BookableItemHandler() {
		/*
		 * default constr
		 */
	}

	public boolean setItemValuesToBooking(String type) {
		boolean res = true;
		if(type.equals(BookingConstants.TYPESEAT)) {
			((BookingSeat)booking).setSeatValues(((Seat)bookableItem).getSeatRoom(), ((Seat)bookableItem).getSeatNum());	
		} else 
		if(type.equals(BookingConstants.TYPEPC)) {
			((BookingPC)booking).setPcValues(((PC)bookableItem).getPcName());
		} else res = false;
		return res;
	}

}
