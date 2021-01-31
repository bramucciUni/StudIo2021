package logic.graphiccontrollers;

import logic.entity.Message;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MessageDetailsGUI extends GUIGeneral {
	
	private Message message;

	public MessageDetailsGUI(Message message) {
		this.message = message;
	}

    @FXML
    private Label lbTextTop;

    @FXML
    private Label lbTopAlerts;

    @FXML
    private Label lbTitleBookingGUI;

    @FXML
    private Label lbLibrary;

    @FXML
    private Text txMitt;

    @FXML
    private Label lbAddress;

    @FXML
    private Text txContenuto;

    @FXML
    private HBox hboxButtons;

    @FXML
    private Button btnBack;

    @FXML
    void btnBackPressed(ActionEvent event) {
    	try {
			handlerFXML.setGui("MessagesGUI", new MessagesGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
   	public void initialize(URL location, ResourceBundle resources) {
   		myLogger.info("init class: " + this.getClass().toString());
		fillImageView(ivLogoTop, "resources/logoB.png");
   		this.txMitt.setText(message.getMittente());
   		this.txContenuto.setText(message.getMessageText());
    }

}
