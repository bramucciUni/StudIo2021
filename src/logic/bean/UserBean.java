package logic.bean;

public class UserBean {
	
	private String usernameUtente;
	private String pswUtente;
	private String emailUtente;
	private String nomeUtente;
	private String cognomeUtente;
	
	public UserBean(String usernameUtente, String pswUtente, String emailUtente, String nomeUtente, String cognomeUtente) {
		this.usernameUtente = usernameUtente;
		this.pswUtente = pswUtente;
		this.emailUtente = emailUtente;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
	}
	public String getUtenteUsername() {
		return usernameUtente;
	}
	public void setUtenteUsername(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}
	public String getPswUtente() {
		return pswUtente;
	}
	public void setPswUtente(String pswUtente) {
		this.pswUtente = pswUtente;
	}
	public String getEmailUtente() {
		return emailUtente;
	}
	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}
	public String getUtenteNome() {
		return nomeUtente;
	}
	public void setUtenteNome(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public String getUtenteCognome() {
		return cognomeUtente;
	}
	public void setUtenteCognome(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}

}
