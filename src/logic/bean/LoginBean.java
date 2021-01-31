package logic.bean;

public class LoginBean {
	
	private String mailIn;
	private String passwordIn;
	private String loginErrorMessage;
	
	public String getMailIn() {
		return mailIn;
	}
	public void setMailIn(String mailIn) {
		this.mailIn = mailIn;
	}
	public String getPasswordIn() {
		return passwordIn;
	}
	public void setPasswordIn(String passwordIn) {
		this.passwordIn = passwordIn;
	}
	public String getLoginErrorMessage() {
		return loginErrorMessage;
	}
	public void setLoginErrorMessage(String loginErrorMessage) {
		this.loginErrorMessage = loginErrorMessage;
	}

}
