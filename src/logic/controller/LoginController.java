package logic.controller;

import logic.bean.LoginBean;
import logic.bean.UserBean;
import logic.dao.LibrarianDao;
import logic.dao.StudentDao;

public class LoginController {
	
	private LoginBean loginBean;
	private UserBean userBean;
	
	private StudentDao studentDao;
	private LibrarianDao librarianDao;
	
	public LoginController() {
		/*default constructor*/
	}

	public LoginController(LoginBean loginBean) {
		this.loginBean = loginBean;
		studentDao = null;
		librarianDao = null;
	}
	
	public int login(String mail, String password) {
		
		studentDao = new StudentDao();
		userBean = studentDao.selectStudentByMailPassword(mail, password);
		if(userBean != null) return 0;
		
		librarianDao = new LibrarianDao();
		userBean = librarianDao.selectLibrarianByMailPassword(mail, password);
		if(userBean != null) return 1;
		
		return -1;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
