package logic.exceptions;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8435169010639022513L;

	private final String errorMessage;
	
	public LoginException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
}
