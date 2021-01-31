package logic.exceptions;

import logic.constants.ExceptionConstants;

public class NoSeatsAvailableException extends NoItemAvailableException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1539301670184013200L;

	@Override
	public String getErrorItemLoadingMessage() {
		return ExceptionConstants.MESSAGE_NO_SEATS_AVAILABLE;
	}

}
