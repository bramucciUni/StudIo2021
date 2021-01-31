package logic.application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.bean.UserBean;
import logic.graphiccontrollers.FXMLHandler;
import logic.graphiccontrollers.StartGUI;


public class Main extends Application {
	
    private static UserBean userBean;
    
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Stud.io");
		FXMLHandler handlerFXML = new FXMLHandler(this, primaryStage);
		try {
			handlerFXML.setGui("StartGUI", new StartGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static UserBean getUserBean() {
		return userBean;
	}

	public static void setUserBean(UserBean userBean) {
		Main.userBean = userBean;
	}
}
