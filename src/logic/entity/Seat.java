package logic.entity;

public class Seat extends BookableItem {
	
	private String seatRoom;
	private int seatNum;
	/*
	 * altro Tipo di sedia, girevole, rotelle...
	 */
	
	public Seat(String state, String idLibrary, String seatRoom, int seatNum) {
		super(state, idLibrary);
		this.seatRoom = seatRoom;
		this.seatNum = seatNum;
	}

	public String getSeatRoom() {
		return seatRoom;
	}

	public void setSeatRoom(String seatRoom) {
		this.seatRoom = seatRoom;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	
}
