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
import logic.dao.FeedbackDao;
import logic.entity.Feedback;

public class FeedbacksGUI extends GUIGeneral {
	
	private Integer listIndex;
    private List<Feedback> feedback;
    private FeedbackDao feedbackDao;
    
    public FeedbacksGUI() {
    	this.feedbackDao = null;
    	this.feedback = new ArrayList<>();
    	this.listIndex = -1;
    }
	
	@FXML
    private Label lbTextTop;

    @FXML
    private ListView<String> lvFeedbacks;

    @FXML
    private Button btnBack;

    @FXML
    void btnBackPressed(ActionEvent event) {
    	try {
			handlerFXML.setGui("LibrarianGUI", new LibrarianGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void lvItemPressed() {
    	listIndex = lvFeedbacks.getSelectionModel().getSelectedIndex();
		myLogger.info("selected " + listIndex);
    }
    
    @Override
   	public void initialize(URL location, ResourceBundle resources) {
   		myLogger.info("init class: " + this.getClass().toString());
		fillImageView(ivLogoTop, "resources/logoB.png");
   		this.listIndex = -1;
   		this.lbTextTop.setText(Main.getUserBean().getUtenteUsername());
   		feedbackDao = new FeedbackDao();
   		feedback = feedbackDao.selectFeedbacksFromLibrary(Main.getUserBean().getEmailUtente());
   		updateFeedbackList();
   	}

	private void updateFeedbackList() {
		myLogger.info("updateFeedbacks");
		lvFeedbacks.getItems().clear();
		for(int i=0; i<feedback.size(); i++) {
			lvFeedbacks.getItems().add(feedback.get(i).getTitolo());
			myLogger.info("list feedback insert " + i);
		}
	}

}
