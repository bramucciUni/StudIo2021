package logic.bean;

import java.util.List;

public class SearchSeatBean {
	
	private int indexSelected;
	private String locationIn;
	private List<LibraryBean> searchResults;
	
	public SearchSeatBean() {
		//default constructor
	}
	
	public SearchSeatBean(String locationIn) {
		this.locationIn = locationIn;
	}

	public String getLocationIn() {
		return locationIn;
	}

	public void setLocationIn(String locationIn) {
		this.locationIn = locationIn;
	}

	public List<LibraryBean> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<LibraryBean> searchResults) {
		this.searchResults = searchResults;
	}

	public int getIndexSelected() {
		return indexSelected;
	}

	public void setIndexSelected(int indexSelected) {
		this.indexSelected = indexSelected;
	}

}
