package logic.constants;

public class DatabaseConstants {
	
	private DatabaseConstants() {
		/* default constructor */
	}
	
	public static final String DB_CONFIG_FILEPATH = "src/resources/db_config.txt";
	public static final String DB_URI = "jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "root";
	
	/*
	 * queries student
	 */
	public static final String SELECT_STUDENT_FROM_MAIL_PASSWORD = "SELECT * FROM Studente WHERE mailStudente = ? AND passwordStudente = ?";
	public static final String SELECT_STUDENTE_FROM_MAIL = "SELECT * FROM Studente WHERE mailStudente = ?";
	public static final String SELECT_MESSAGES_FROM_STUDENT = "SELECT * FROM messaggio WHERE mailStudente = ?";

	
	/*
	 * queries librarian
	 */
	public static final String SELECT_LIBRARIAN_FROM_MAIL_PASSWORD = "SELECT * FROM Bibliotecario WHERE mailL = ? AND passwordL = ?";
	
	/*
	 * queries library
	 */
	public static final String SELECT_LIBRARIES_FROM_CITY = "SELECT * FROM Biblioteca WHERE cittaBiblioteca=?";
	public static final String SELECT_LIBRARY_FROM_MAIL = "SELECT * FROM Biblioteca WHERE mailBiblioteca=?";
	
	/*
	 * queries bookings
	 */
	public static final String SELECT_BOOKINGS_FROM_LIBRARYID_TYPE = "SELECT * FROM Prenotazione WHERE mailBiblioteca=? AND tipoPrenotazione=?";
	public static final String INSERT_BOOKING_SEAT = "INSERT INTO `mydb`.`prenotazione` (`mailStudente`, `mailBiblioteca`, `orarioPrenotazione`, `dataPrenotazione`, `statoPrenotazione`, `tipoPrenotazione`, `room`, `seatNum`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_BOOKING_PC = "INSERT INTO `mydb`.`prenotazione` (`mailStudente`, `mailBiblioteca`, `orarioPrenotazione`, `dataPrenotazione`, `statoPrenotazione`, `tipoPrenotazione`, `pcName`) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String SELECT_HANGING_BOOKINGS_FROM_STUDENT = "SELECT * FROM prenotazione WHERE mailStudente=? AND statoPrenotazione=?";
	public static final String SELECT_BOOKING_FROM_STUDENTID_STATE_TYPE = "SELECT * FROM prenotazione WHERE mailStudente=? AND statoPrenotazione=? AND tipoPrenotazione=?";
	
	/*
	 * queries seats
	 */
	public static final String SELECT_AVAILABLE_SEAT_FROM_LIBRARY = "SELECT * FROM Sedia WHERE mailBiblioteca=? AND statoSedia='Disponibile'";
	public static final String SELECT_SEAT_FROM_LIBRARYID_FIELDS = "SELECT * FROM Sedia WHERE mailBiblioteca=? AND room=? AND seatNumber=?";

	
	/*
	 * queries pc
	 */
	public static final String SELECT_AVAILABLE_PC_FROM_LIBRARY = "SELECT * FROM PC WHERE mailBiblioteca=? AND statoPC='Disponibile'";
	public static final String SELECT_PC_FROM_LIBRARY_PCNAME = "SELECT * FROM PC WHERE mailBiblioteca=? AND pcName=?";
	
}
