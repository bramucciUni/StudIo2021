package logic.constants;

public class BookingConstants {
	
	private BookingConstants() {
		/* default constructor */
	}
	
	/*
	 * stato prenotazione
	 */
	public static final String ACCEPTED = "Accepted";
	public static final String DECLINED = "Declined";
	public static final String DELETED = "Deleted";
	public static final String HANGING = "Hanging";
	
	/*
	 * tipo prenotazione
	 */
	public static final String TYPESEAT = "posto";
	public static final String TYPEPC = "pc";
	public static final String ALLTYPES = "all";
	
	/*
	 * codice prenotazione
	 */
	public static final String BOOKINGCODSEAT = "@SEAT";
	public static final String BOOKINGCODPC = "@PC";
	public static final String BOOKINGCODSEPARATOR = "_";
	
	/*
	 * messaggi prenotazione
	 */
	public static final String BOOKEDOK = "You have successfully booked";
	
}
