package logic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookingPCBean;
import logic.bean.BookingSeatBean;
import logic.bean.LibraryBean;
import logic.bean.StudentBean;
import logic.boundary.BookingBoundary;
import logic.constants.BookingConstants;
import logic.entity.Booking;

/**
 * Servlet implementation class BookingItemServlet
 */
@WebServlet("/BookingItemServlet")
public class BookingItemServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String FAILURE_MSG = "BookingItemFailureMessage";
	private static final String SERVLET_MSG = "BookingItemServletMessage";
	
    private BookingSeatBean bookSeatBean;
    private BookingPCBean bookPCBean;
    private BookingBoundary bookingBoundary;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingItemServlet() {
        super();
    	this.bookSeatBean = null;
    	this.bookPCBean = null;
    	this.bookingBoundary = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibraryBean librSel = (LibraryBean)request.getSession().getAttribute("selectedLibrary");
		StudentBean studBean = (StudentBean)request.getSession().getAttribute("studentBean");
		if(request.getParameter("bookPC") != null) {
			bookPCBean = new BookingPCBean(librSel.getMailB(), studBean.getEmailUtente());
	    	bookingBoundary = new BookingBoundary(bookPCBean);
	    	if(bookingBoundary.book(BookingConstants.TYPEPC)) {
	        	request.getSession().setAttribute("bookingBean", bookPCBean);
	    		request.getSession().setAttribute(FAILURE_MSG, "Pc booked");
	    	} else {
	    		request.getSession().setAttribute(FAILURE_MSG, "Pc booking failed");
	    	}
			request.getRequestDispatcher("studentSearchLibrPage.jsp").forward(request, response);

		} else 
			if(request.getParameter("bookSeat") != null) {
				bookSeatBean = new BookingSeatBean(librSel.getMailB(), studBean.getEmailUtente());
		    	bookingBoundary = new BookingBoundary(bookSeatBean);
		    	if(bookingBoundary.book(BookingConstants.TYPESEAT)) {
		        	request.getSession().setAttribute("bookingBean", bookSeatBean);
		    		request.getSession().setAttribute(FAILURE_MSG, "Seat booked");
		    	} else {
		    		request.getSession().setAttribute(FAILURE_MSG, "Seat booking failed");
		    	}
				request.getRequestDispatcher("studentSearchLibrPage.jsp").forward(request, response);
			}
			else 
				if(request.getParameter("deleteBook") != null) {
					Booking book = null;
					book = (Booking)request.getSession().getAttribute("book");
					if(book != null) {
			    		bookingBoundary = new BookingBoundary();
			    		if(bookingBoundary.unbook(book.getMailStudente(), book.getTipoPrenotazione(), book.getStatoPrenotazione())) {
			    			request.getSession().setAttribute(SERVLET_MSG, "Operation completed.");
			    		}
			    	} else {
		    			request.getSession().setAttribute(SERVLET_MSG, "You have no booking to delete.");
			    	}
					request.getRequestDispatcher("Booking.jsp").forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
