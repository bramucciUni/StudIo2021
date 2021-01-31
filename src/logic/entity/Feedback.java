package logic.entity;

public class Feedback {
	
	private String bibl;
	private String stud;
	private String titolo;
	private String feed;
	
	public Feedback(String bibl, String stud, String titolo, String feed) {
		super();
		this.bibl = bibl;
		this.stud = stud;
		this.titolo = titolo;
		this.feed = feed;
	}

	public String getBibl() {
		return bibl;
	}

	public void setBibl(String bibl) {
		this.bibl = bibl;
	}

	public String getStud() {
		return stud;
	}

	public void setStud(String stud) {
		this.stud = stud;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}
	

}
