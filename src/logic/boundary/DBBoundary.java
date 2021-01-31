package logic.boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import logic.constants.DatabaseConstants;
import logic.exceptions.DatabaseLoadingException;
import logic.utils.DBConfigurationLoader;

public class DBBoundary {
	private Connection connection;
	private static Logger myLogger = Logger.getLogger("logger");
	private String dbUri = DatabaseConstants.DB_URI;
	private String dbUsername = DatabaseConstants.DB_USERNAME;
	private String dbPassword = DatabaseConstants.DB_PASSWORD;
	
	public DBBoundary() {
		try {
			connection = DriverManager.getConnection(dbUri, dbUsername, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			myLogger.info("Connessione fallita");
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			myLogger.info("Connection closure failed");
			e.printStackTrace();
		}
	}

	public Connection openConnection() {
		/* loading_db() */
		try {
			if(!connection.isClosed()) closeConnection();
			connection = DriverManager.getConnection(dbUri, dbUsername, dbPassword);
		} catch (SQLException e) {
			myLogger.info("Connessione fallita");
			e.printStackTrace();
		}
		return connection;
	}
	
	/*
	 * dynamic db loading
	 */
	@SuppressWarnings("unused")
	private void loadingDb() {
		DBConfigurationLoader dbConfigLoader = new DBConfigurationLoader();
		List<String> dbConfigVars = null;
		try {
			dbConfigVars = dbConfigLoader.loadDBConfig();
		} catch (DatabaseLoadingException e) {
			e.getErrorMessage();
			/*
			 * advertise
			 */
		}
		dbUri = dbConfigVars.get(0);
		dbUsername = dbConfigVars.get(1);
		dbPassword = dbConfigVars.get(2);
	}
}
