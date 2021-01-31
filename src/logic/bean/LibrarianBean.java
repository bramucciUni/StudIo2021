package logic.bean;

public class LibrarianBean extends UserBean {
	
	private String matricola;
	private String biblioteca;

	public LibrarianBean(String username, String password, String mail, String nome, String cognome, String matricola, String biblioteca) {
		super(username, password, mail, nome, cognome);
		this.matricola = matricola;
		this.biblioteca = biblioteca;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(String biblioteca) {
		this.biblioteca = biblioteca;
	}

}
