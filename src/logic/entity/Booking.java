package logic.entity;

import logic.constants.BookingConstants;

public abstract class Booking {
		
	private String mailBiblioteca;
	private String mailStudente;
	private String orarioPrenotazione;
	private String dataPrenotazione;
	private String statoPrenotazione;
	private String tipoPrenotazione;
	
	protected Booking() {
		/*
		 * default costructor
		 */
	}
	
	protected Booking(String mailBiblioteca, String mailStudente, String orarioPrenotazione, String dataPrenotazione,
			String statoPrenotazione, String tipoPrenotazione) {
		this.mailBiblioteca = mailBiblioteca;
		this.mailStudente = mailStudente;
		this.orarioPrenotazione = orarioPrenotazione;
		this.dataPrenotazione = dataPrenotazione;
		this.statoPrenotazione = statoPrenotazione;
		this.tipoPrenotazione = tipoPrenotazione;
	}
	
	protected Booking(String mailBiblioteca, String mailStudente, String tipoPrenotazione, String orarioPrenotazione, String dataPrenotazione) {
		this.mailBiblioteca = mailBiblioteca;
		this.mailStudente = mailStudente;
		this.orarioPrenotazione = orarioPrenotazione;
		this.dataPrenotazione = dataPrenotazione;
		this.tipoPrenotazione = tipoPrenotazione;
	}
	
	public abstract String getItemInformation();

	public void accepted() {
		this.statoPrenotazione = BookingConstants.ACCEPTED;
	}
	
	public void declined() {
		this.statoPrenotazione = BookingConstants.DECLINED;
	}
	
	public void deleted() {
		this.statoPrenotazione = BookingConstants.DELETED;
	}
	
	public void hanging() {
		this.statoPrenotazione = BookingConstants.HANGING;
	}
	
	public String getMailBiblioteca() {
		return mailBiblioteca;
	}

	public void setMailBiblioteca(String mailBiblioteca) {
		this.mailBiblioteca = mailBiblioteca;
	}

	public String getMailStudente() {
		return mailStudente;
	}

	public void setMailStudente(String mailStudente) {
		this.mailStudente = mailStudente;
	}
	
	public String getDateTimeBooking() {
		return(dataPrenotazione + " - " + orarioPrenotazione);
	}

	public String getOrarioPrenotazione() {
		return orarioPrenotazione;
	}

	public void setOrarioPrenotazione(String orarioPrenotazione) {
		this.orarioPrenotazione = orarioPrenotazione;
	}

	public String getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(String dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public String getStatoPrenotazione() {
		return statoPrenotazione;
	}

	public void setStatoPrenotazione(String dataPrenotazione) {
		this.statoPrenotazione = dataPrenotazione;
	}

	public String getTipoPrenotazione() {
		return tipoPrenotazione;
	}

	public void setTipoPrenotazione(String tipoPrenotazione) {
		this.tipoPrenotazione = tipoPrenotazione;
	}
	
}
