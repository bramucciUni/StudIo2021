package logic.boundary;

import logic.bean.LibrarianBean;
import logic.bean.LoginBean;
import logic.bean.StudentBean;
import logic.controller.LoginController;

public class LoginBoundary {
	
	private LoginBean loginBean;
	private StudentBean studentBean;
	private LibrarianBean librarianBean;
	private LoginController loginController;
	
	public LoginBoundary() {
		//default constructor
	}
	
	public LoginBoundary(LoginBean loginBean) {
		this.loginBean = loginBean;
		loginController = null;
	}

	public int login() {
		int resLogin = -1;
		/*
		 * chiamata a controller
		 */
		loginController = new LoginController(loginBean);
		
		resLogin = loginController.login(loginBean.getMailIn(), loginBean.getPasswordIn());
			
		if(resLogin == 0) {
			studentBean = (StudentBean) loginController.getUserBean();
		} else if(resLogin == 1) {
			librarianBean = (LibrarianBean) loginController.getUserBean();
		} else if(resLogin == -1) {
			loginBean.setLoginErrorMessage("Wrong credentials");
		}
		return resLogin;
	}

	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}

	public LibrarianBean getLibrarianBean() {
		return librarianBean;
	}

	public void setLibrarianBean(LibrarianBean librarianBean) {
		this.librarianBean = librarianBean;
	}

}
