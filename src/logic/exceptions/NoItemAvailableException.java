package logic.exceptions;

public abstract class NoItemAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5093030060490386684L;
	
	public abstract String getErrorItemLoadingMessage();

}
