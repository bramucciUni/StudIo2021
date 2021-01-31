package logic.entity;

public class Student extends User {
	
	private String phone;
	private int isBooked;
	
	public Student(String username, String password, String mail, String nome, String cognome, String phone, int isBooked) {
		super(username, password, mail, nome, cognome);
		this.phone = phone;
		this.isBooked = isBooked;
	}

	public String getTelefono() {
		return phone;
	}

	public void setTelefono(String telefono) {
		this.phone = telefono;
	}

	public int getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(int isBooked) {
		this.isBooked = isBooked;
	}
	
}
