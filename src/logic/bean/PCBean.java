package logic.bean;

public class PCBean {
	private String mailBiblioteca;
	private String pcName;
	private String statoPC;
	private String mailStudent;
	
	public PCBean(String mailBiblioteca, String pcName, String statoPC, String mailStudent) {
		this.mailBiblioteca = mailBiblioteca;
		this.pcName = pcName;
		this.statoPC = statoPC;
		this.mailStudent = mailStudent;
	}

	public String getMailBiblioteca() {
		return mailBiblioteca;
	}

	public void setMailBiblioteca(String mailBiblioteca) {
		this.mailBiblioteca = mailBiblioteca;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getStatoPC() {
		return statoPC;
	}

	public void setStatoPC(String statoPC) {
		this.statoPC = statoPC;
	}

	public String getMailStudent() {
		return mailStudent;
	}

	public void setMailStudent(String mailStudent) {
		this.mailStudent = mailStudent;
	}
	
}
