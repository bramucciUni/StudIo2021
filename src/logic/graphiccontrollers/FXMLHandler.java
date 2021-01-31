package logic.graphiccontrollers;

import java.io.IOException;

import logic.application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLHandler {
	
	private Main main;
	private Stage stage;
	private BorderPane borderPane;
	
	public FXMLHandler(Main main, Stage stage) {
		this.main = main;
		this.stage = stage;
		this.borderPane = null;
	}
	
	public void setGui(String guiName, GUIGeneral guiController) throws IOException {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../graphicstandalone/" + guiName + ".fxml"));
        loader.setController(guiController);
        guiController.setHandlerFXML(this);
        borderPane = (BorderPane) loader.load();
        Scene scene = new Scene(borderPane);
    	stage.setScene(scene);
    	stage.show();
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
