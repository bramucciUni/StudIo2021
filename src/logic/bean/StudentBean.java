package logic.bean;

public class StudentBean extends UserBean {
	
	private String phone;
	private int isBooked;

	public StudentBean(String username, String password, String mail, String nome, String cognome, String phone, int isBooked) {
		super(username, password, mail, nome, cognome);
		this.phone = phone;	
		this.isBooked = isBooked;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(int isBooked) {
		this.isBooked = isBooked;
	}

}
