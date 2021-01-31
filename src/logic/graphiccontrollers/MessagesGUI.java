package logic.graphiccontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import logic.application.Main;
import logic.dao.MessageDao;
import logic.entity.Message;

public class MessagesGUI extends GUIGeneral {
	
	private Integer lvIndexSelected;
    private List<Message> messages;
    private MessageDao messageDao;
    
    public MessagesGUI() {
    	this.lvIndexSelected = -1;
    	this.messages = new ArrayList<>();
    	this.messageDao = null;
    }

    @FXML
    private Label lbTextTop;

    @FXML
    private Label lbErrorMessages;

    @FXML
    private ListView<String> lvMessages;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    void btnBackPressed(ActionEvent event) {
    	try {
			handlerFXML.setGui("StudentGUI", new StudentGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnNextPressed(ActionEvent event) {
    	if(lvIndexSelected == -1) {
    		if(!lbErrorMessages.isVisible()) lbErrorMessages.setVisible(true);
    		lbErrorMessages.setText("Please select a message");
    		return;
    	}
    	if(lbErrorMessages.isVisible()) lbErrorMessages.setVisible(false);
    	try {
			handlerFXML.setGui("MessageDetailsGUI", new MessageDetailsGUI(messages.get(lvIndexSelected)));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void lvItemPressed() {
    	myLogger.info("ItemPressed");
    	lvIndexSelected = lvMessages.getSelectionModel().getSelectedIndex();
    }
    
    @Override
   	public void initialize(URL location, ResourceBundle resources) {
   		myLogger.info("init class: " + this.getClass().toString());
		fillImageView(ivLogoTop, "resources/logoB.png");
   		this.lbErrorMessages.setVisible(false);
   		this.lvIndexSelected = -1;
   		this.lbTextTop.setText(Main.getUserBean().getUtenteUsername());
   		messageDao = new MessageDao();
   		messages = messageDao.selectMessagesFromStudent(Main.getUserBean().getEmailUtente());
   		updateMessages();
   	}

	private void updateMessages() {
		myLogger.info("updateMessages");
		lvMessages.getItems().clear();
		for(int i=0; i<messages.size(); i++) {
			lvMessages.getItems().add(messages.get(i).getData() + " - " + messages.get(i).getOra() + " - " + messages.get(i).getMittente());
			myLogger.info("list insert " + i);
		}
	}

}

