package logic.entity;

public class Librarian extends User {
	
	private String matricola;
	private String libraryKey;
	
	public Librarian(String username, String password, String mail, String nome, String cognome, String matricola, String libraryKey) {
		super(username, password, mail, nome, cognome);
		this.matricola = matricola;
		this.libraryKey = libraryKey;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getLibraryKey() {
		return libraryKey;
	}

	public void setLibraryKey(String libraryKey) {
		this.libraryKey = libraryKey;
	}

}
