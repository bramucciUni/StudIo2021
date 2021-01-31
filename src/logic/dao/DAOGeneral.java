package logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import logic.boundary.DBBoundary;

/*
 * Classe che riempie i prepared statement per le 4 operazioni base su db (insert, select, update, delete)
 */
public class DAOGeneral {

	protected static Logger myLogger = Logger.getLogger("logger");
	protected Connection con;
	protected PreparedStatement ps;
	private DBBoundary dbBoundary; 
	
	public DAOGeneral() {
		dbBoundary = new DBBoundary();
		con=dbBoundary.getConnection();
	}
	
	protected void finallyClosure(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
