package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import logic.bean.StudentBean;
import logic.constants.DatabaseConstants;

public class StudentDao extends DAOGeneral {
	
	private StudentBean studentBean;
	
	public StudentDao() {
		super();
	}
	
	public StudentDao(StudentBean studentBean) {
		super();
		this.studentBean = studentBean;
	}

	/*query specifica per login (statement con piu' parametri)*/
	public StudentBean selectStudentByMailPassword(String mail, String password) {
		ResultSet rs = null;
		studentBean = null;
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_STUDENT_FROM_MAIL_PASSWORD);
			ps.setString(1,  mail);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
			    studentBean = new StudentBean(rs.getString("usernameStudente"), rs.getString("passwordStudente"), 
						    rs.getString("mailStudente"),   rs.getString("nomeStudente"), rs.getString("cognomeStudente"), rs.getString("telefonoStudente"), rs.getInt("isBooked"));
		    }			
		}
		catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Login select from student failed");	//fare conversione d'errore throw LoginFailedException
		} finally {
			finallyClosure(ps);
		}
		return studentBean;
	}
	
	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}

}
