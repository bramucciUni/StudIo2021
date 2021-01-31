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
 * Servlet implementation class ReleasePcServlet
 */
@WebServlet("/ReleasePcServlet")
public class ReleasePcServlet extends HttpServlet {
	
	private ManageSeatsBoundary manSeatBound;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleasePcServlet() {
        super();
        this.manSeatBound = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibrarianBean librarianBeann = (LibrarianBean)request.getSession().getAttribute("librarianBean");
		manSeatBound = new ManageSeatsBoundary(librarianBeann.getBiblioteca());
		if(request.getParameter("releasePcBtn") != null) {
			String pcName;
			pcName = request.getParameter("pcNameField").toString();
			if(manSeatBound.releasePC(librarianBeann.getBiblioteca(), pcName)) {
    			request.getSession().setAttribute("releasePcOutcome", "Operation completed");
    		} else {
    			request.getSession().setAttribute("releasePcOutcome", "Operation failed");
    		}
			request.getRequestDispatcher("releasePc.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
