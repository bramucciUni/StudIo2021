package logic.entity;

public class Message {
	
	private String mitt;
	private String dest;
	private String contenuto;
	private String data;
	private String ora;
	
	public Message(String mitt, String dest, String contenuto, String data, String ora) {
		super();
		this.mitt = mitt;
		this.dest = dest;
		this.contenuto = contenuto;
		this.data = data;
		this.ora = ora;
	}
	
	public void setMessageDateTime(String data, String ora) {
		setData(data);
		setOra(ora);
	}
	
	public void setMessage(String mitt, String dest, String contenuto) {
		setMitt(mitt);
		setDest(dest);
		setContenuto(contenuto);
	}
	
	public String getMessageText() {
		return contenuto;
	}
	
	public String getDateTime() {
		return(getData() + getOra());
	}
	
	public String getMittente() {
		return mitt;
	}
	
	public String getDestinatario() {
		return dest;
	}

	private void setMitt(String mitt) {
		this.mitt = mitt;
	}

	private void setDest(String dest) {
		this.dest = dest;
	}

	private void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public String getData() {
		return data;
	}

	private void setData(String data) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}

	private void setOra(String ora) {
		this.ora = ora;
	}
}
