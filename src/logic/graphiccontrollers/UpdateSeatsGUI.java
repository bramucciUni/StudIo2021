package logic.graphiccontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.application.Main;
import logic.bean.AdvertiseBean;
import logic.bean.LibrarianBean;
import logic.bean.LibraryBean;
import logic.boundary.ManageSeatsBoundary;
import logic.constants.BookingConstants;
import logic.dao.BookableItemDao;
import logic.dao.BookingDao;

public class UpdateSeatsGUI extends GUIGeneral {
	
	private ManageSeatsBoundary manageSeatsBoundary;
	private BookingDao bookingDao;
	private BookableItemDao bookableItemDao;
	private AdvertiseBean advertiseBean;
	public UpdateSeatsGUI() {
    	new LibraryBean();
    	new ArrayList<>();
    	manageSeatsBoundary = null;
    	bookingDao = null;
    	bookableItemDao = null;
    	advertiseBean = null;
    }

    @FXML
    private Label lbTextTop;

    @FXML
    private Label lbFreeSeats;

    @FXML
    private Label lbBookedSeats;

    @FXML
    private Button btnAddVisitor;

    @FXML
    private Button btnReleaseSeat;
    
    @FXML
    private Button btnRefresh;

    @FXML
    private Label lbFreePC;

    @FXML
    private Label lbBookedPC;

    @FXML
    private Button btnAddPcUser;

    @FXML
    private Button btnReleasePC;

    @FXML
    private Button btnBack;

    @FXML
    void btnBackPressed(ActionEvent event) {
		myLogger.info("back pressed");
		try {
			handlerFXML.setGui("LibrarianGUI", new LibrarianGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void btnRefreshPressed(ActionEvent event) {
		myLogger.info("refresh pressed");
		try {
			handlerFXML.setGui("UpdateSeatsGUI", new UpdateSeatsGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnAddPcUserPressed(ActionEvent event) {
    	addGeneralVisitor(BookingConstants.TYPEPC);
    }

    @FXML
    void btnAddVisitorPressed(ActionEvent event) {
    	addGeneralVisitor(BookingConstants.TYPESEAT);
    }

    @FXML
    void btnReleasePCPressed(ActionEvent event) {
    	myLogger.info("release PC pressed");
		try {
			handlerFXML.setGui("ReleasePCGUI", new ReleasePCGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnReleaseSeatPressed(ActionEvent event) {
    	myLogger.info("release seat pressed");
		try {
			handlerFXML.setGui("ReleaseSeatGUI", new ReleaseSeatGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /*chiamata a metodo update per aggiornare i valori di Library_bean e List<Booking_bean> per l'interfaccia*/
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myLogger.info("initialize updateSeatsGUI");
		fillImageView(ivLogoTop, "resources/logoB.png");
		this.lbTextTop.setText("Mat. " + ((LibrarianBean)Main.getUserBean()).getMatricola());
		updateGUI();
	}

	private void updateGUI() {
		myLogger.info("update gui");
		manageSeatsBoundary = new ManageSeatsBoundary( ((LibrarianBean)Main.getUserBean()).getBiblioteca() );
		bookingDao = new BookingDao();
		bookableItemDao = new BookableItemDao();
		int freeSeats = bookableItemDao.selectCountFreeItems(((LibrarianBean)Main.getUserBean()).getBiblioteca(), BookingConstants.TYPESEAT);
		int freePC = bookableItemDao.selectCountFreeItems(((LibrarianBean)Main.getUserBean()).getBiblioteca(), BookingConstants.TYPEPC);
		int seatBookings = bookingDao.selectCountBookingsFromLibrary(((LibrarianBean)Main.getUserBean()).getBiblioteca(), BookingConstants.TYPESEAT);
		int pcBookings = bookingDao.selectCountBookingsFromLibrary(((LibrarianBean)Main.getUserBean()).getBiblioteca(), BookingConstants.TYPEPC);

		/*SETTARE I VALORI DELLE LABELS*/
		this.lbFreeSeats.setText(String.valueOf(freeSeats));
		this.lbBookedSeats.setText(String.valueOf(seatBookings));
		this.lbFreePC.setText(String.valueOf(freePC));
		this.lbBookedPC.setText(String.valueOf(pcBookings));
	}
	
	private void addGeneralVisitor(String type) {
		advertiseBean = new AdvertiseBean();
		manageSeatsBoundary.setAdvertiseBean(advertiseBean);
		if(manageSeatsBoundary.visitorIn(type) == 0) {
			alertShow(AlertType.INFORMATION, "Seat visitor", advertiseBean.getResult(), "Operation completed");
			updateGUI();
		} else {
			alertShow(AlertType.WARNING, "PC visitor", advertiseBean.getMessage(), "An error occurred");
		}
	}
    
}
