package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.entity.Feedback;

public class FeedbackDao extends DAOGeneral {
	
	public List<Feedback> selectFeedbacksFromLibrary(String mail) {
		ResultSet rs = null;
		List<Feedback> results = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement("SELECT * FROM Report WHERE mailBiblioteca = ?");
			ps.setString(1,  mail);
			rs = ps.executeQuery();
			while(rs.next()) {    
				Feedback feedback = new Feedback(
							rs.getString("mailBiblioteca"), 
							rs.getString("mailStudente"), 
							rs.getString("titolo"),   
							rs.getString("testo"));
					results.add(feedback);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Search feedback from mail failed");
		} finally {
			finallyClosure(ps);
		}
		return results;
	}

	public int saveFeedback(Feedback feedback) {
		int status = 0;
		try {
			ps = con.prepareStatement(
					"INSERT INTO `mydb`.`report` (`mailBiblioteca`, `mailStudente`, `titolo`, `testo`) VALUES (?,?,?,?)");
			ps.setString(1, feedback.getBibl());
			ps.setString(2, feedback.getStud());
			ps.setString(3, feedback.getTitolo());
			ps.setString(4, feedback.getFeed());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			myLogger.info("Salvataggio feedback fallito");
		} finally {
			finallyClosure(ps);
		}
		return status;
	}

}
