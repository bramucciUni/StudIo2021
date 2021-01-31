package logic.entity;

public class PC extends BookableItem {
	
	private String pcName;
	/*
	 * altro, modello, sistema operativo, ...
	 */

	public PC(String state, String libraryId, String pcName) {
		super(state, libraryId);
		this.pcName = pcName;
	}
	
	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

}
