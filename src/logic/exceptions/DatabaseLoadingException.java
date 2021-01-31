package logic.exceptions;

public class DatabaseLoadingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4739451949463017076L;
	
	private final String errorMessage;
	
	public DatabaseLoadingException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
