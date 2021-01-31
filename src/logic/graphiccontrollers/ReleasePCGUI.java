package logic.graphiccontrollers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import logic.application.Main;
import logic.bean.LibrarianBean;
import logic.boundary.ManageSeatsBoundary;

public class ReleasePCGUI extends GUIGeneral {
	
	private ManageSeatsBoundary manageSeatsBoundary;

    public ReleasePCGUI() {
		super();
		this.manageSeatsBoundary = null;
	}

    @FXML
    private Label lbTextTop;

    @FXML
    private Label lbErrorReleaseSeat;

    @FXML
    private TextField tfPCName;

    @FXML
    private Button btnRelease;

    @FXML
    private Button btnBack;

    @FXML
    void btnBackPressed(ActionEvent event) {
    	myLogger.info("back pressed");
    	goBack();
    }

    @FXML
    void btnReleasePressed(ActionEvent event) {
    	myLogger.info("release PC pressed");
    	if(tfPCName.getText().toString().isBlank()) {
    		lbErrorReleaseSeat.setText("Please fill the field");
    	} else {
    		manageSeatsBoundary = new ManageSeatsBoundary(((LibrarianBean)Main.getUserBean()).getBiblioteca());
    		if(manageSeatsBoundary.releasePC(((LibrarianBean)Main.getUserBean()).getBiblioteca(), tfPCName.getText().toString())) {
    			alertShow(AlertType.INFORMATION, "Release item", "Releasing PC", "Operation completed");
    			goBack();
    		} else {
    			alertShow(AlertType.WARNING, "Release item", "Releasing PC", "Operation failed");
    		}
    	}
    }
    
    private void goBack() {
    	try {
			handlerFXML.setGui("UpdateSeatsGUI", new UpdateSeatsGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
