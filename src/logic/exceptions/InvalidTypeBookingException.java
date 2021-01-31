package logic.exceptions;

public class InvalidTypeBookingException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7700622874040178445L;
	
	private final String errorType;

	public InvalidTypeBookingException(String type) {
		this.errorType = type;
	}

	@Override
	public String getMessage() {
		return errorType;
	}
	
	
	
}
