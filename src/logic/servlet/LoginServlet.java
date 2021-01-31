package logic.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.LoginBean;
import logic.boundary.LoginBoundary;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private LoginBoundary loginBoundary;
	private LoginBean loginBean;
	private static Logger myLogger = Logger.getLogger("logger");
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        this.loginBean = null;
        this.loginBoundary = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mailIn;
		String passwordIn;
		mailIn = request.getParameter("emailLogin").toString();
		passwordIn = request.getParameter("passwordLogin").toString();
		loginBean = new LoginBean();
		loginBean.setMailIn(mailIn);
		loginBean.setPasswordIn(passwordIn);
		loginBoundary = new LoginBoundary(loginBean);
		int loginResult = loginBoundary.login();
		if(loginResult == 0) {
			myLogger.info("Student");
			request.getSession().setAttribute("studentBean", loginBoundary.getStudentBean());
			request.getRequestDispatcher("studentHome.jsp").forward(request, response);
		} else if(loginResult == 1) {
			myLogger.info("Librarian");
			request.getSession().setAttribute("librarianBean", loginBoundary.getLibrarianBean());
			request.getRequestDispatcher("librarianHome.jsp").forward(request, response);
		} else if(loginResult == -1) {
			myLogger.info("Login failed");
			response.sendRedirect("erroreLogin.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
