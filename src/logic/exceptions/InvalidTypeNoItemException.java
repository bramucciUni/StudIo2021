package logic.exceptions;

public class InvalidTypeNoItemException extends Exception {
	
	private final String typeNoItemExcMessage;

	public InvalidTypeNoItemException(String typeNoItemExcMessage) {
		this.typeNoItemExcMessage = typeNoItemExcMessage;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7198356253922745063L;

	@Override
	public String getMessage() {
		return typeNoItemExcMessage;
	}

}
