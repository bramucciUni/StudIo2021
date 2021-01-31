package logic.bean;

public class SeatBean {
	private String mailBiblioteca;
	private String room;
	private int seatNumber;
	private String statoSedia;
	private String mailStudent;
	
	public SeatBean(String mailBiblioteca, String room, int seatNumber, String statoSedia) {
		this.mailBiblioteca = mailBiblioteca;
		this.room = room;
		this.seatNumber = seatNumber;
		this.statoSedia = statoSedia;
		this.mailStudent = null;
	}
	
	public SeatBean(String mailBiblioteca, String room, int seatNumber, String statoSedia, String mailStudent) {
		this.mailBiblioteca = mailBiblioteca;
		this.room = room;
		this.seatNumber = seatNumber;
		this.statoSedia = statoSedia;
		this.mailStudent = mailStudent;
	}

	public String getMailBiblioteca() {
		return mailBiblioteca;
	}

	public void setMailBiblioteca(String mailBiblioteca) {
		this.mailBiblioteca = mailBiblioteca;
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

	public String getStatoSedia() {
		return statoSedia;
	}

	public void setStatoSedia(String statoSedia) {
		this.statoSedia = statoSedia;
	}

	public String getMailStudent() {
		return mailStudent;
	}

	public void setMailStudent(String mailStudent) {
		this.mailStudent = mailStudent;
	}
	
	
	
}
