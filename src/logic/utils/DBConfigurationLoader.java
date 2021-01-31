package logic.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import logic.constants.DatabaseConstants;
import logic.exceptions.DatabaseLoadingException;

public class DBConfigurationLoader {
	
	private static Logger myLogger = Logger.getLogger("logger");
	
	private List<String> dbConfigVars;
	
	public DBConfigurationLoader() {
		this.dbConfigVars = new ArrayList<>();
	}
	
	/*
	 * Read a file and return array:
	 * [0] : database URI
	 * [1] : database username
	 * [2] : database password
	 */
	public List<String> loadDBConfig() throws DatabaseLoadingException {
		String line;
		BufferedReader reader;
		try {
			
			reader = new BufferedReader(new FileReader(DatabaseConstants.DB_CONFIG_FILEPATH));
			do {
				line = reader.readLine();
				if(line != null) {
					dbConfigVars.add(line);
				}
			} while(line!=null);
			
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			myLogger.info("Exception database loading: File not found");
			throw(new DatabaseLoadingException("File config.txt not found"));
		} catch (IOException e) {
			e.printStackTrace();
			myLogger.info("Exception database loading: IO exception");
			throw(new DatabaseLoadingException("Read config.txt failed"));
		} 
	    return dbConfigVars;
	}	
	
}
