package logic.exceptions;

import logic.constants.ExceptionConstants;

public class StudentRegisteredYetException extends Exception {
	
	private static final long serialVersionUID = 7166784068724578002L;
	
	private final String errorMessage;
	
	public StudentRegisteredYetException() {
		this.errorMessage = ExceptionConstants.MESSAGE_STUDENT_REGISTERED_YET;
	}
	
	@Override
	public String getMessage() {
		return errorMessage;
	}
	
}
