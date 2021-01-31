package logic.entity;

import logic.constants.BookingConstants;

public class Library {
	
	private String mailLibrary;
	private String nameLibrary;
	private String address;
	private String city;
	private String phone;
	private int totalSeats;
	private int totalPc;
	private int busySeats;
	private int busyPc;
	
	/*
	 * constructor: String mailLibrary, String nameLibrary, String address, String city, String phone, int totalSeats, int totalPc, int busySeats, int busyPc
	 */
	public Library(String...args) {
		this.mailLibrary = args[0];
		this.nameLibrary = args[1];
		this.address = args[2];
		this.city = args[3];
		this.phone = args[4];
		this.totalSeats = (int)Integer.valueOf(args[5]);
		this.totalPc = (int)Integer.valueOf(args[6]);
		this.busySeats = (int)Integer.valueOf(args[7]);
		this.busyPc = (int)Integer.valueOf(args[8]);
	}

	public Library() {
	}
	
	public int getFreeSeats() {
		return (totalSeats - busySeats);
	}
	
	public int getFreePC() {
		return (totalPc - busyPc);
	}

	public String getMailLibrary() {
		return mailLibrary;
	}

	public void setMailLibrary(String mailLibrary) {
		this.mailLibrary = mailLibrary;
	}

	public String getNameLibrary() {
		return nameLibrary;
	}

	public void setNameLibrary(String nameLibrary) {
		this.nameLibrary = nameLibrary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getTotalPc() {
		return totalPc;
	}

	public void setTotalPc(int totalPc) {
		this.totalPc = totalPc;
	}

	public int getBusySeats() {
		return busySeats;
	}

	public void setBusySeats(int busySeats) {
		this.busySeats = busySeats;
	}

	public int getBusyPc() {
		return busyPc;
	}

	public void setBusyPc(int busyPc) {
		this.busyPc = busyPc;
	}

	public void visitorIn(String type) {
		if(type.equals(BookingConstants.TYPESEAT)) {
			busySeats+=1;
		} else if(type.equals(BookingConstants.TYPESEAT)) {
			busyPc+=1;
		}
	}
	
}