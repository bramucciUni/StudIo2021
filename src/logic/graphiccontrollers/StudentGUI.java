package logic.graphiccontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import logic.application.Main;

public class StudentGUI extends GUIGeneral {
	
	@FXML private Label lbTextTop;
	@FXML private Button btnUser;
	@FXML private ImageView ivUserStudent;
	@FXML private ImageView ivHowToStudent;
	@FXML private Button btnSearchSeat;
	@FXML private Button btnMessages;
	@FXML private Button btnBooking;
	@FXML private Button btnLogout;
	
	@FXML
	private void btnUserPressed(ActionEvent event) {
		myLogger.info("btnUserPressed");
	}
	
	@FXML
	private void btnSearchSeatPressed(ActionEvent event) {
		myLogger.info("btnSearchSeatPressed");
		try {
			handlerFXML.setGui("SearchSeatGUI", new SearchSeatGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void btnMessagesPressed(ActionEvent event) {
		myLogger.info("btnMessagesPressed");
		try {
			handlerFXML.setGui("MessagesGUI", new MessagesGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void btnBookingPressed(ActionEvent event) {
		myLogger.info("btnBookingPressed");
		try {
			handlerFXML.setGui("BookingGUI", new BookingGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void btnLogoutPressed(ActionEvent event) {
		myLogger.info("btnLogoutPressed");
    	alertShow(AlertType.INFORMATION, "Logout student", "Logout successfully", "Bye!");
		try {
			handlerFXML.setGui("StartGUI", new StartGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myLogger.info("initialize studentGUI" + Main.getUserBean().getUtenteUsername());
		fillImageView(ivLogoTop, "resources/logoB.png");
		fillImageView(ivUserStudent, "resources/guest.png");
		fillImageView(ivHowToStudent, "resources/logo200.png");
		this.lbTextTop.setText(Main.getUserBean().getUtenteUsername());
	}		
	
}
