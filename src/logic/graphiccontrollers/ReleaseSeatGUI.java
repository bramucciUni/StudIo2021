package logic.graphiccontrollers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.application.Main;
import logic.bean.LibrarianBean;
import logic.boundary.ManageSeatsBoundary;

public class ReleaseSeatGUI extends GUIGeneral {
	
	private ManageSeatsBoundary manageSeatsBoundary;

    public ReleaseSeatGUI() {
		super();
		this.manageSeatsBoundary = null;
	}

    @FXML
    private Label lbTextTop;

    @FXML
    private Label lbErrorReleaseSeat;

    @FXML
    private TextField tfRoom;

    @FXML
    private TextField tfSeatNumber;

    @FXML
    private Button btnRelease;
    
    @FXML
    private Button btnBack;

    @FXML
    void btnReleasePressed(ActionEvent event) {
    	if(tfRoom.getText().toString().isBlank() || tfSeatNumber.getText().toString().isBlank()) {
    		lbErrorReleaseSeat.setText("Please fill all fields");
    	} else {
    		manageSeatsBoundary = new ManageSeatsBoundary(((LibrarianBean)Main.getUserBean()).getBiblioteca());
    		if(manageSeatsBoundary.releaseSeat(((LibrarianBean)Main.getUserBean()).getBiblioteca(), tfRoom.getText().toString(), (int)Integer.valueOf(tfSeatNumber.getText().toString()))) {
    			alertShow(AlertType.INFORMATION, "Release item", "Releasing seat", "Operation completed");
    			goBack();
    		} else {
    			alertShow(AlertType.WARNING, "Release item", "Releasing seat", "Operation failed");
    		}
    	}
    }
    
    @FXML
    void btnBackPressed(ActionEvent event) {
    	myLogger.info("back pressed");
		goBack();
    }
    
    private void goBack() {
    	try {
			handlerFXML.setGui("UpdateSeatsGUI", new UpdateSeatsGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}

