package logic.exceptions;

import logic.constants.ExceptionConstants;

public class NoPCAvailableException extends NoItemAvailableException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4122434480676335242L;

	@Override
	public String getErrorItemLoadingMessage() {
		return ExceptionConstants.MESSAGE_NO_PC_AVAILABLE;
	}

}
