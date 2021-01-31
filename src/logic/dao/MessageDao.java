package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.constants.DatabaseConstants;
import logic.entity.Message;

public class MessageDao extends DAOGeneral {

	public List<Message> selectMessagesFromStudent(String mail) {
		ResultSet rs = null;
		List<Message> results = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_MESSAGES_FROM_STUDENT);
			ps.setString(1,  mail);
			rs = ps.executeQuery();
			while(rs.next()) {    
					Message message = new Message(
							rs.getString("mailBiblioteca"), 
							rs.getString("mailStudente"), 
							rs.getString("testoMessaggio"),   
							rs.getString("dataMessaggio"), 
							rs.getString("oraMessaggio"));
					results.add(message);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Search messages from student failed");
		} finally {
			finallyClosure(ps);
		}
		return results;
	}

	public int saveMessage(Message message) {
		int status = 0;
		try {
			ps = con.prepareStatement(
					"INSERT INTO `mydb`.`messaggio` (`mailBiblioteca`, `mailStudente`, `testoMessaggio`, `dataMessaggio`, `oraMessaggio`) VALUES (?,?,?,?,?)");
			ps.setString(1, message.getMittente());
			ps.setString(2, message.getDestinatario());
			ps.setString(3, message.getMessageText());
			ps.setString(4, message.getData());
			ps.setString(5, message.getOra());
			status = ps.executeUpdate();
		} catch (SQLException e) {
			myLogger.info("Salvataggio messaggio fallito");
		} finally {
			finallyClosure(ps);
		}
		return status;
	}

}
