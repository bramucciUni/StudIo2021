package logic.bean;

public class LibraryBean {
	
	private String mailB;
	private String nameB;
	private String addressB;
	private String cityB;
	private String phoneB;
	private int totSeatsB;
	private int totPcB;
	private int busySeatsB;
	private int busyPcB;
	
	public LibraryBean() {
		//default constructor
	}

	public LibraryBean(String mailB) {
		this.mailB = mailB;
	}
	
	/* 
	 * constructor String mailLibrary, String nameLibrary, String address, String city, String phone, int totalSeats, int busySeats, int totalPc,  int busyPc
	 */
	public LibraryBean(String...args) {
		this.mailB = args[0];
		this.nameB = args[1];
		this.addressB = args[2];
		this.cityB = args[3];
		this.phoneB = args[4];
		this.totSeatsB = (int)Integer.valueOf(args[5]);
		this.busySeatsB = (int)Integer.valueOf(args[6]);
		this.totPcB = (int)Integer.valueOf(args[7]);
		this.busyPcB = (int)Integer.valueOf(args[8]);
	}
	
	public int freeSeats() {
		return (totSeatsB - busySeatsB);
	}
	
	public int freePc() {
		return (totPcB - busyPcB);
	}

	public String getMailB() {
		return mailB;
	}

	public void setMailB(String mailB) {
		this.mailB = mailB;
	}

	public String getNameB() {
		return nameB;
	}

	public void setNameB(String nameB) {
		this.nameB = nameB;
	}

	public String getIndirizzoB() {
		return addressB;
	}

	public void setIndirizzoB(String addressB) {
		this.addressB = addressB;
	}

	public String getCittaB() {
		return cityB;
	}

	public void setCittaB(String city) {
		this.cityB = city;
	}

	public String getTelefonoB() {
		return phoneB;
	}

	public void setTelefonoB(String phoneB) {
		this.phoneB = phoneB;
	}

	public int getTotalSedie() {
		return totSeatsB;
	}

	public void setTotalSedie(int totSeatsB) {
		this.totSeatsB = totSeatsB;
	}

	public int getTotPc() {
		return totPcB;
	}

	public void setTotPc(int totPcB) {
		this.totPcB = totPcB;
	}

	public int getSedieOccupate() {
		return busySeatsB;
	}

	public void setSedieOccupate(int busySeatsB) {
		this.busySeatsB = busySeatsB;
	}

	public int getPcOccupati() {
		return busyPcB;
	}

	public void setPcOccupati(int busyPcB) {
		this.busyPcB = busyPcB;
	}

	public void aggiungiSediaOccupata() {
		this.busySeatsB += 1;
	}
	
	public void aggiungiPcOccupato() {
		this.busyPcB += 1;
	}
	
	public void rimuoviSediaOccupata() {
		this.busySeatsB += 1;
	}
	
	public void rimuoviPcOccupato() {
		this.busyPcB += 1;
	}

}
