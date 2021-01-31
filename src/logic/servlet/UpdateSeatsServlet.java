package logic.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.AdvertiseBean;
import logic.bean.LibrarianBean;
import logic.bean.LibraryBean;
import logic.boundary.ManageSeatsBoundary;
import logic.constants.BookingConstants;

/**
 * Servlet implementation class UpdateSeatsServlet
 */
@WebServlet("/UpdateSeatsServlet")
public class UpdateSeatsServlet extends HttpServlet {
	
	private ManageSeatsBoundary manSeatBoundary;
	private AdvertiseBean advBean;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSeatsServlet() {
        super();
        new LibraryBean();
    	new ArrayList<>();
    	manSeatBoundary = null;
    	advBean = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibrarianBean librarian = (LibrarianBean)request.getSession().getAttribute("librarianBean");
		manSeatBoundary = new ManageSeatsBoundary(librarian.getBiblioteca());
		if(request.getParameter("addVisitor") != null) {
			addVisit(BookingConstants.TYPESEAT, request, response);
		} else if(request.getParameter("addPcUser") != null) {
			addVisit(BookingConstants.TYPEPC, request, response);
		} else if(request.getParameter("releaseSeat") != null) {
			request.getRequestDispatcher("releaseSeat.jsp").forward(request, response);
		} else if(request.getParameter("releasePc") != null) {
			request.getRequestDispatcher("releasePc.jsp").forward(request, response);
		} else if(request.getParameter("backBtn") != null) {
			request.getRequestDispatcher("librarianHome.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void addVisit(String tipo, HttpServletRequest request, HttpServletResponse response) {
		advBean = new AdvertiseBean();
		manSeatBoundary.setAdvertiseBean(advBean);
		if(manSeatBoundary.visitorIn(tipo) == 0) {
			request.getSession().setAttribute("addVisitMessage", advBean.getResult());
		} else {
			request.getSession().setAttribute("addVisitMessage", advBean.getMessage());
		}
		try {
			request.getRequestDispatcher("itemVisitor.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
