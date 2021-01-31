package logic.graphiccontrollers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIGeneral implements Initializable {
	
	@FXML
	protected ImageView ivLogoTop;
	
	protected FXMLHandler handlerFXML;
	protected static Logger myLogger = Logger.getLogger("logger");
	
	public FXMLHandler getHandlerFXML() {
		return handlerFXML;
	}

	public void setHandlerFXML(FXMLHandler handlerFXML) {
		this.handlerFXML = handlerFXML;
	}
	
	public void alertShow(AlertType alertType, String title, String header, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myLogger.info("init GUIGeneral");
		fillImageView(ivLogoTop, "resources/logoB.png");
	}
	
	public void fillImageView(ImageView iv, String pathImage) {
		File file = new File(pathImage);
        Image image = new Image(file.toURI().toString());
        iv.setImage(image);
	}

}
