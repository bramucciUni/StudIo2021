package logic.graphiccontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import logic.application.Main;
import logic.bean.LibraryBean;
import logic.boundary.BookingBoundary;
import logic.constants.BookingConstants;
import logic.dao.BookingDao;
import logic.dao.LibraryDao;
import logic.entity.Booking;

public class BookingGUI extends GUIGeneral {
	
		private Booking booking = null;

	    @FXML
	    private Label lbTextTop;

	    @FXML
	    private Label lbTopAlerts;

	    @FXML
	    private Label lbTitleBookingGUI;

	    @FXML
	    private Label lbWarningPrenotazione;

	    @FXML
	    private Label lbLibrary;

	    @FXML
	    private Text txLibrary;

	    @FXML
	    private Label lbAddress;

	    @FXML
	    private Text txAddress;

	    @FXML
	    private Label lbContacts;

	    @FXML
	    private Text txContacts;

	    @FXML
	    private Label lbBookingTime;

	    @FXML
	    private Text txBookingTime;

	    @FXML
	    private Label lbBookingType;

	    @FXML
	    private Text txBookingType;

	    @FXML
	    private Label lbInstructionsBookingGUI;

	    @FXML
	    private HBox hboxButtons;

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Button btnDelete;
	    
	    @FXML
	    private Label lbBookingItem;

	    @FXML
	    private Text txBookingItem;

	    @FXML
	    void btnBackPressed(ActionEvent event) {
	    	try {
				handlerFXML.setGui("StudentGUI", new StudentGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void btnDeletePressed(ActionEvent event) {
	    	if(booking != null) {
	    		BookingBoundary bookingBoundary = new BookingBoundary();
	    		if(bookingBoundary.unbook(booking.getMailStudente(), booking.getTipoPrenotazione(), booking.getStatoPrenotazione())) {
	    			alertShow(AlertType.INFORMATION, "Information", "Booking deleted", "Your booking was deleted successfully");
	    			booking = null;
	    			updateBookingView();
	    		}
	    	} else {
	    		lbWarningPrenotazione.setText("You have no booking to delete");
	    	}
	    }
	    
	    private void updateBookingView() {
	    	BookingDao bookingDao = new BookingDao();
			List<Booking> bookings = null;
			bookings = bookingDao.selectActiveBookingFromStudentId(Main.getUserBean().getEmailUtente(), BookingConstants.HANGING);
			if(bookings.size() == 0) {
				myLogger.info("bookings = 0");
				lbTopAlerts.setText("Student have no hanging booking");
				txLibrary.setText("");
				txAddress.setText("");
				txContacts.setText("");
				txBookingTime.setText("");
				txBookingType.setText("");
				txBookingItem.setText("");
			} else {
				booking = bookings.get(0);
				myLogger.info("bookings > 0");
				LibraryDao libraryDao = new LibraryDao();
				LibraryBean libraryBean = libraryDao.selectLibraryFromId(booking.getMailBiblioteca());
				txLibrary.setText(libraryBean.getNameB());
				txAddress.setText(libraryBean.getIndirizzoB());
				txContacts.setText(libraryBean.getTelefonoB() + " - " + libraryBean.getMailB());
				txBookingTime.setText(booking.getDateTimeBooking());
				txBookingType.setText(booking.getTipoPrenotazione());
				txBookingItem.setText(booking.getItemInformation());
			}
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			myLogger.info("initialize studentGUI: " + Main.getUserBean().getUtenteUsername());
			this.lbTextTop.setText(Main.getUserBean().getUtenteUsername());
			updateBookingView();
			fillImageView(ivLogoTop, "resources/logoB.png");
		}	

}
