package logic.entity;

public class BookingPC extends Booking {
	
	private String pcName;

	public BookingPC(String mailBiblioteca, String mailStudente, String orarioPrenotazione, String dataPrenotazione,
			String statoPrenotazione, String tipoPrenotazione, String pcName) {
		super(mailBiblioteca, mailStudente, orarioPrenotazione, dataPrenotazione, statoPrenotazione, tipoPrenotazione);
		this.pcName = pcName;
	}
	
	public BookingPC(String mailBiblioteca, String mailStudente, String tipoPrenotazione, String orarioPrenotazione,
			String dataPrenotazione) {
		super(mailBiblioteca, mailStudente, tipoPrenotazione, orarioPrenotazione, dataPrenotazione);
	}

	public BookingPC() {
		/*
		 * default constructor
		 */
	}

	@Override
	public String getItemInformation() {
		/*call labelPC_decrypter()*/
		return ("PC: " + pcName);
	}

	public String getPcName() {
		return pcName;
	}
	
	public void setPcValues(String pcName) {
		this.pcName = pcName;
	}
	
}
