package logic.bean;

public class BookingSeatBean extends BookingBean {
	
	private String room;
	private int seatNumber;
	
	public BookingSeatBean(String libraryId, String studentId) {
		super(libraryId, studentId);
	}
	
	/*
	 * constructor 
	 * BookingSeatBean(String libraryId, String studentId, String hour, String date, String stato, String tipo, String room, String seatNumber)
	 */
	public BookingSeatBean(String...args) {
		super(args[0], args[1], args[2], args[3], args[4], args[5]);
		this.room = args[6];
		this.seatNumber = (int)Integer.valueOf(args[7]);
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

}
