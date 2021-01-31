package logic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookingBean;
import logic.boundary.ManageBookingsBoundary;

/**
 * Servlet implementation class LibrarianManageBookingServlet
 */
@WebServlet("/LibrarianManageBookingServlet")
public class LibrarianManageBookingServlet extends HttpServlet {
	
	private BookingBean bookingBean;
	private ManageBookingsBoundary manageBookingsBoundary;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianManageBookingServlet() {
        super();
        bookingBean = null;
        manageBookingsBoundary = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookingBean = (BookingBean)request.getSession().getAttribute("selectedBooking");
		manageBookingsBoundary = new ManageBookingsBoundary(bookingBean);
		String outcome = "Booking operation completed";
		if(request.getParameter("btnAccept") != null && manageBookingsBoundary.validateBooking() != 0) {
			outcome = "Validation failed";
		} else if(request.getParameter("btnDecline") != null && manageBookingsBoundary.invalidateBooking() != 0) {
			outcome = "Invalidation failed";
		}
		request.getSession().setAttribute("outcomeValidating", outcome);
		request.getRequestDispatcher("bookingDetailLibrarian.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
