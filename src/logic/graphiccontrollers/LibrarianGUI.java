package logic.graphiccontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import logic.application.Main;
import logic.bean.LibrarianBean;

public class LibrarianGUI extends GUIGeneral {

	    @FXML
	    private Label lbTextTop;

	    @FXML
	    private Button btnUpdateSeats;

	    @FXML
	    private Button btnBookings;

	    @FXML
	    private Button btnFeedback;

	    @FXML
	    private Button btnLogout;

	    @FXML
	    private Button btnUser;

	    @FXML
	    private ImageView ivUser;
	    
	    @FXML 
	    private ImageView ivHowToLibrarian;

	    @FXML
	    void btnBookingsPressed(ActionEvent event) {
	    	try {
				handlerFXML.setGui("LibrarianBookingsGUI", new LibrarianBookingsGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void btnFeedbackPressed(ActionEvent event) {
	    	try {
				handlerFXML.setGui("FeedbacksGUI", new FeedbacksGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void btnLogoutPressed(ActionEvent event) {
	    	alertShow(AlertType.INFORMATION, "Logout librarian", "Logout successfully", "Bye!");
	    	try {
				handlerFXML.setGui("StartGUI", new StartGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void btnUpdateSeatsPressed(ActionEvent event) {
	    	try {
				handlerFXML.setGui("UpdateSeatsGUI", new UpdateSeatsGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void btnUserPressed(ActionEvent event) {
	    	/*
	    	 * user
	    	 */
	    }
	    
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			myLogger.info("initialize librarianGUI" + Main.getUserBean().getUtenteUsername());
			fillImageView(ivLogoTop, "resources/logoB.png");
			fillImageView(ivUser, "resources/guest.png");
			fillImageView(ivHowToLibrarian, "resources/logo200.png");
			this.lbTextTop.setText(((LibrarianBean)Main.getUserBean()).getMatricola());
		}	
	
}
