package logic.graphiccontrollers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartGUI extends GUIGeneral {

    @FXML private Button btnLogin;
    @FXML private Button btnSignUp;
    @FXML private Button btnGuestMode;

    /**
     * 
     */
    @FXML
    private void btnLoginPressed(ActionEvent event) {
    	myLogger.info("btnLoginPressed");
    	try {
			handlerFXML.setGui("LoginGUI", new LoginGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * 
     */
    @FXML
    private void btnSignUpPressed(ActionEvent event) {
    	myLogger.info("btnSignUpPressed");
    }

    /**
     * 
     */
    @FXML
    private void btnGuestModePressed(ActionEvent event) {
    	myLogger.info("btnGuestModePressed");
    }

}
