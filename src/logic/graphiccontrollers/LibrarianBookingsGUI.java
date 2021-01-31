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
import logic.bean.BookingBean;
import logic.bean.LibrarianBean;
import logic.dao.BookingDao;

public class LibrarianBookingsGUI extends GUIGeneral  {
	
	private Integer listIndex;
    private List<BookingBean> bookings;
    
    public LibrarianBookingsGUI() {
    	this.lvBookings = new ListView<>();
    	this.bookings = new ArrayList<>();
	}

    @FXML
    private Label lbTextTop;

    @FXML
    private Label lbErrorMessagesLib;

    @FXML
    private ListView<String> lvBookings;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    void btnBackPressed(ActionEvent event) {
    	try {
			handlerFXML.setGui("LibrarianGUI", new LibrarianGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnNextPressed(ActionEvent event) {
    	myLogger.info("btnNextLibrarianBookingsPressed");
    	if(listIndex == -1) {
    		if(!lbErrorMessagesLib.isVisible()) lbErrorMessagesLib.setVisible(true);
    		lbErrorMessagesLib.setText("Please select a booking");
    		return;
    	}
    	if(lbErrorMessagesLib.isVisible()) lbErrorMessagesLib.setVisible(false);
    	try {
			handlerFXML.setGui("LibrarianBookingDetailsGUI", new LibrarianBookingDetailsGUI(bookings.get(listIndex)));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void lvItemPressed() {
    	myLogger.info("lvItemPressed");
    	listIndex = lvBookings.getSelectionModel().getSelectedIndex();
    }
    
    /*
   	 * Metodo per aggiornare la listView
   	 */
   	private void updateListView() {
   		myLogger.info("updateListView");
   		lvBookings.getItems().clear();
   		for(int i=0; i<bookings.size(); i++) {
   			lvBookings.getItems().add(bookings.get(i).getHour() + " - " + bookings.get(i).getTipo() + " - " + bookings.get(i).getStudentId());
   		}
   	}
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		myLogger.info("init class: " + this.getClass().toString());
		fillImageView(ivLogoTop, "resources/logoB.png");
		this.lbErrorMessagesLib.setVisible(false);
		this.listIndex = -1;
		this.lbTextTop.setText(((LibrarianBean)Main.getUserBean()).getMatricola());
		BookingDao bookingDao = new BookingDao();
		bookings = bookingDao.selectBookingsForLibrary(((LibrarianBean)Main.getUserBean()).getBiblioteca());
		updateListView();
	}

}
