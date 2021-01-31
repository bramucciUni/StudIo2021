package logic.entity;

import logic.constants.ItemConstants;

public class BookableItem {
	
	private String stateItem;
	private String idLibrary;

	public BookableItem(String stateItem, String idLibrary) {
		this.idLibrary = idLibrary;
		this.stateItem = stateItem;
	}
	
	public void releaseItem() {
		this.stateItem = ItemConstants.FREE;
	}
	
	public void busyItem() {
		this.stateItem = ItemConstants.BUSY;
	}

	public String getStateItem() {
		return stateItem;
	}

	public void setStateItem(String stateItem) {
		this.stateItem = stateItem;
	}

	public String getIdLibrary() {
		return idLibrary;
	}

	public void setIdLibrary(String idLibrary) {
		this.idLibrary = idLibrary;
	}

}
