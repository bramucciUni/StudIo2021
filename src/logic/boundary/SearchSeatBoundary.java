package logic.boundary;

import java.util.logging.Logger;

import logic.bean.SearchSeatBean;
import logic.controller.SearchSeatController;

public class SearchSeatBoundary {
	
	protected static Logger myLogger = Logger.getLogger("logger");	
	
	private SearchSeatBean searchSeatBean;
	private SearchSeatController searchSeatController;
	
	public SearchSeatBoundary(SearchSeatBean searchSeatBean) {
		this.searchSeatBean = searchSeatBean;
		this.searchSeatController = null;
	}
	
	public boolean searchSeat() {
		boolean res = false;
		searchSeatController = new SearchSeatController(searchSeatBean);
		if(searchSeatController.searchSeat()) {
			myLogger.info("Trovati " + searchSeatBean.getSearchResults().size() + " risultati");
			res = true;
		}
		return res;
	}

}
