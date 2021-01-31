package logic.entity;

public class User {
	
	private String username;
	private String password;
	private String mail;
	private String nome;
	private String cognome;
	
	public User(String username, String password, String mail, String nome, String cognome) {
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.nome = nome;
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
}
