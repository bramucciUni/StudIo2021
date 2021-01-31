package logic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.LibrarianBean;
import logic.boundary.ManageSeatsBoundary;

/**
 * Servlet implementation class ReleaseSeatServlet
 */
@WebServlet("/ReleaseSeatServlet")
public class ReleaseSeatServlet extends HttpServlet {
	
    private ManageSeatsBoundary managSeatBoundary;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseSeatServlet() {
        super();
        this.managSeatBoundary = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibrarianBean librarian = (LibrarianBean)request.getSession().getAttribute("librarianBean");
		managSeatBoundary = new ManageSeatsBoundary(librarian.getBiblioteca());
		if(request.getParameter("releaseSeatBtn") != null) {
			String room;
			String seatNumber;
			room = request.getParameter("roomField").toString();
			seatNumber = request.getParameter("seatNumberField").toString();
			if(managSeatBoundary.releaseSeat(librarian.getBiblioteca(), room, (int)Integer.valueOf(seatNumber))) {
    			request.getSession().setAttribute("releaseSeatOutcome", "Operation completed");
    		} else {
    			request.getSession().setAttribute("releaseSeatOutcome", "Operation failed");
    		}
			request.getRequestDispatcher("releaseSeat.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
