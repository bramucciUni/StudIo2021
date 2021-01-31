package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import logic.bean.LibrarianBean;
import logic.bean.UserBean;
import logic.constants.DatabaseConstants;

public class LibrarianDao extends DAOGeneral {

	public UserBean selectLibrarianByMailPassword(String mailIn, String passwordIn) {
		ResultSet rs = null;
		LibrarianBean librarianBean = null;
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_LIBRARIAN_FROM_MAIL_PASSWORD);
			ps.setString(1,  mailIn);
			ps.setString(2, passwordIn);
			rs = ps.executeQuery();
			while(rs.next()) {
			    librarianBean = new LibrarianBean(rs.getString("matricolaL"), rs.getString("passwordL"), 
						    rs.getString("mailL"),   rs.getString("nomeL"), rs.getString("cognomeL"), rs.getString("matricolaL"), rs.getString("bibliotecaL"));
		    }			
		}
		catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Login select from student failed");
		} finally {
			finallyClosure(ps);
		}
		return librarianBean;
	}

}
