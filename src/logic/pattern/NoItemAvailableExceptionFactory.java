package logic.pattern;

import logic.constants.BookingConstants;
import logic.exceptions.InvalidTypeNoItemException;
import logic.exceptions.NoItemAvailableException;
import logic.exceptions.NoPCAvailableException;
import logic.exceptions.NoSeatsAvailableException;

public class NoItemAvailableExceptionFactory {
	
	public NoItemAvailableExceptionFactory() {
		/*
		 * default constructor
		 */
	}
	
	public NoItemAvailableException createNoItemAvailableException(String itemType) throws InvalidTypeNoItemException {
		switch(itemType) {
			case BookingConstants.TYPESEAT:
				return createNoSeatsAvailableException();
			case BookingConstants.TYPEPC:
				return createNoPCAvailableException();
			default: 
				throw new InvalidTypeNoItemException("Invalid item type selected: " + itemType);
		}
	}

	private NoItemAvailableException createNoPCAvailableException() {
		return new NoPCAvailableException();
	}

	private NoItemAvailableException createNoSeatsAvailableException() {
		return new NoSeatsAvailableException();
	}

}
