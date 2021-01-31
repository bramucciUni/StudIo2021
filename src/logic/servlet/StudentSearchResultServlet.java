package logic.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.bean.LibraryBean;

/**
 * Search results handler
 */
@WebServlet("/StudentSearchResultServlet")
public class StudentSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger myStudentSearchResultServletLog = Logger.getLogger("logger");

	String index;
	int ind;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSearchResultServlet() {
        super();
        index = null;
        ind = -1;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LibraryBean> biblioteche = ((List<LibraryBean>)request.getSession().getAttribute("librResults"));
		for(int i=0;i<biblioteche.size();i++) {
			if(request.getParameter("lib".concat(biblioteche.get(i).getMailB()))!=null) {
				LibraryBean selectedLibrary=biblioteche.get(i);
				request.getSession().setAttribute("selectedLibrary", selectedLibrary);
				myStudentSearchResultServletLog.info("Selected: " + selectedLibrary.getNameB());
				try {
					request.getRequestDispatcher("studentSearchLibrPage.jsp").forward(request, response);
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
