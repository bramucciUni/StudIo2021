package logic.graphiccontrollers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.application.Main;
import logic.bean.LoginBean;
import logic.boundary.LoginBoundary;

public class LoginGUI extends GUIGeneral {

	@FXML private Label lbAvvisoFallimento;
	@FXML private Button btnLoginOk;
	@FXML private Button btnBack;
	@FXML private TextField tfUsername;
	@FXML private PasswordField tfPassword;

	private LoginBoundary loginBoundary;
	private LoginBean loginBean;

	public LoginGUI() {
		loginBean = null;
		loginBoundary = null;
	}
	
	@FXML
    void btnBackPressed(ActionEvent event) {
    	try {
			handlerFXML.setGui("StartGUI", new StartGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
	private void btnLoginOkPressed(ActionEvent event) {
		
		/*check input*/
		if(tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty()) {
			lbAvvisoFallimento.setText("Fill in all fields");
		} else {
			/*set bean*/
			loginBean = new LoginBean();
			loginBean.setMailIn(tfUsername.getText().toString());
			loginBean.setPasswordIn(tfPassword.getText().toString());
			/*invoke boundary op*/
			loginBoundary = new LoginBoundary(loginBean);
			int loginResult = loginBoundary.login();
			
			if(loginResult == 0) {
				Main.setUserBean(loginBoundary.getStudentBean());
				try {
					handlerFXML.setGui("StudentGUI", new StudentGUI());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(loginResult == 1) {
				/*librarian view*/
				Main.setUserBean(loginBoundary.getLibrarianBean());
				try {
					handlerFXML.setGui("LibrarianGUI", new LibrarianGUI());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(loginResult == -1) {
				alertShow(AlertType.WARNING, "Attention", "Login failed", loginBean.getLoginErrorMessage());
				lbAvvisoFallimento.setText("");
			}
		}
	}
}
