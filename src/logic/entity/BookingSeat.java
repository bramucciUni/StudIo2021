package logic.entity;

public class BookingSeat extends Booking {
	
	private String room;
	private int seatNum;
	
	public BookingSeat() {
		/*
		 * default constructor
		 */
	}
	
	public BookingSeat(String mailBiblioteca, String mailStudente, String tipoPrenotazione, String orarioPrenotazione,
			String dataPrenotazione) {
		super(mailBiblioteca, mailStudente, tipoPrenotazione, orarioPrenotazione, dataPrenotazione);
	}

	/*BookingSeat(String mailBiblioteca, String mailStudente, String orarioPrenotazione,
			String dataPrenotazione, String statoPrenotazione, String tipoPrenotazione, String room, int seatNum)
	*/
	public BookingSeat(String...args) {
		super(args[0], args[1], args[2], args[3], args[4], args[5]);
		this.room = args[6];
		this.seatNum = (int)Integer.valueOf(args[7]);
	}

	@Override
	public String getItemInformation() {
		/*call labelSeat_decrypter()*/
		return ("ROOM: " + room + " - " + "SEAT: " + seatNum);
	}

	public String getRoom() {
		return room;
	}

	public int getSeatNum() {
		return seatNum;
	}
	
	public void setSeatValues(String room, int seatNum) {
		this.room = room;
		this.seatNum = seatNum;
	}

}
