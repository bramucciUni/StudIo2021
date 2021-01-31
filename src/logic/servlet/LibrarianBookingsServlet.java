package logic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.BookingBean;

/**
 * Servlet implementation class LibrarianBookingsServlet
 */
@WebServlet("/LibrarianBookingsServlet")
public class LibrarianBookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianBookingsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BookingBean> bookings = ((List<BookingBean>)request.getSession().getAttribute("listBookingsResults"));
		for(int i=0;i<bookings.size();i++) {
			if(request.getParameter("lib".concat(bookings.get(i).getStudentId()))!=null) {
				BookingBean booking=bookings.get(i);
				request.getSession().setAttribute("selectedBooking", booking);
				try {
					request.getRequestDispatcher("bookingDetailLibrarian.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
