package logic.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.bean.LibraryBean;
import logic.bean.SearchSeatBean;
import logic.boundary.SearchSeatBoundary;

/**
 * Servlet implementation class SearchSeatServlet
 */
@WebServlet("/SearchSeatServlet")
public class SearchSeatServlet extends HttpServlet {
	
	private static Logger myLogger = Logger.getLogger("logger");
	
	private static final long serialVersionUID = 1L;
	
	private List<LibraryBean> results;
    private SearchSeatBean searchSeatBean;
    private SearchSeatBoundary searchSeatBoundary;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchSeatServlet() {
        super();
        this.searchSeatBean = new SearchSeatBean();
    	this.results = new ArrayList<>();
    	searchSeatBoundary = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("searchBtn") != null) {
			searchSeatBean.setLocationIn(request.getParameter("searchField"));
			searchSeatBoundary = new SearchSeatBoundary(searchSeatBean);
			if(searchSeatBoundary.searchSeat()) {
				results = searchSeatBean.getSearchResults();
			} else {
				myLogger.info("Servlet: libraries not found ");
			}
			request.getSession().setAttribute("librResults", results);
			request.getRequestDispatcher("studentSearchResult.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
