package logic.bean;

public class BookingPCBean extends BookingBean {
	
	private String pcName;
	
	public BookingPCBean(String libraryId, String studentId) {
		super(libraryId, studentId);
	}
	
	public BookingPCBean(String libraryId, String studentId, String hour, String date, String stato, String tipo, String pcName) {
		super(libraryId, studentId, hour, date, stato, tipo);
		this.pcName = pcName;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

}
