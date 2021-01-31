package logic.controller;

import java.util.List;
import java.util.logging.Logger;

import logic.bean.LibraryBean;
import logic.bean.SearchSeatBean;
import logic.dao.LibraryDao;

public class SearchSeatController {
	
	private static Logger myLogger = Logger.getLogger("logger");
	
	private SearchSeatBean searchSeatBean;
	private LibraryDao libraryDao;

	public SearchSeatController(SearchSeatBean searchSeatBean) {
		this.searchSeatBean = searchSeatBean;
		libraryDao = null;
	}
	
	public boolean searchSeat() {
		myLogger.info("SearchSeat_controller: search_seat");
		boolean res = false;
		libraryDao = new LibraryDao();
		List<LibraryBean> results = libraryDao.selectLibrariesFromCity(searchSeatBean.getLocationIn());
		if(!results.isEmpty()) {
			searchSeatBean.setSearchResults(results);
			res = true;
		}
		return res;
	}

}
