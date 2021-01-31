package logic.bean;

public class BookingBean {
	
	private String libraryId;
	private String studentId;
	private String hour;
	private String date;
	private String stato;
	private String tipo;
	private String bookingAdvertise;
	private String bookingLabel;

	public BookingBean() {
		/*
		 * default constructor
		 */
	}
	
	public BookingBean(String libraryId, String studentId) {
		this.libraryId = libraryId;
		this.studentId = studentId;
		this.bookingAdvertise = "";
	}
	
	public BookingBean(String libraryId, String studentId, String hour, String date, String stato, String tipo) {
		this.libraryId = libraryId;
		this.studentId = studentId;
		this.hour = hour;
		this.date = date;
		this.stato = stato;
		this.tipo = tipo;
	}

	public String getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBookingAdvertise() {
		return bookingAdvertise;
	}

	public void setBookingAdvertise(String bookingAdvertise) {
		this.bookingAdvertise = bookingAdvertise;
	}

	public String getBookingLabel() {
		return bookingLabel;
	}
	
	public void setBookingLabel(String bookingLabel) {
		this.bookingLabel = bookingLabel;
	}

	public void empty() {
		this.libraryId = "";
		this.studentId = "";
		this.hour = "";
		this.date = "";
		this.stato = "";
		this.tipo = "";
	}

}
