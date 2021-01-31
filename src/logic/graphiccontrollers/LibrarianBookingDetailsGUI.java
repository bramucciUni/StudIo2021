package logic.graphiccontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import logic.bean.BookingBean;
import logic.boundary.ManageBookingsBoundary;
import logic.constants.GuiConstants;

public class LibrarianBookingDetailsGUI extends GUIGeneral {	
	
	private BookingBean bookingBean;
	private ManageBookingsBoundary manageBookingsBoundary;
	
	@FXML
	private Label lbTopLib;
	
	@FXML
	private Label lbLibTopAlert;
	
	@FXML
	private Label lbTitleLibBookingGUI;
	
	@FXML
	private Label lbLibWarnMsg;
	
	@FXML
	private Label lbStudent;
	
	@FXML
	private Text txStudent;
	
	@FXML
	private Label lbMailStudent;
	
	@FXML
	private Text txMailStudent;
	
	@FXML
	private Label lbBookingTime;
	
	@FXML
	private Text txBookingTime;
	
	@FXML
	private Label lbBookingType;
	
	@FXML
	private Text txBookingType;
	
	@FXML
	private Label lbBookingItem;
	
	@FXML
	private Text txBookingItem;
	
	@FXML
	private Label lbInstructionsBookingGUI;
	
	@FXML
	private HBox hboxButtons;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnAccept;
	
	@FXML
	private Button btnDecline;
	
	@FXML
	void btnBackPressed(ActionEvent event) {
		try {
			handlerFXML.setGui(GuiConstants.LIBR_BOOK_GUI, new LibrarianBookingsGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void btnDeclinePressed(ActionEvent event) {
		myLogger.info("decline pressed");
		if(manageBookingsBoundary.invalidateBooking() == 0) {
			alertShow(AlertType.INFORMATION, "Booking", "Booking invalidation", "Operation successfully completed.");
			try {
				handlerFXML.setGui(GuiConstants.LIBR_BOOK_GUI, new LibrarianBookingsGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	void btnAcceptPressed(ActionEvent event) {
		myLogger.info("accept pressed");
		if(manageBookingsBoundary.validateBooking() == 0) {
			alertShow(AlertType.INFORMATION, "Booking", "Booking validation", "Operation successfully completed.");
			try {
				handlerFXML.setGui(GuiConstants.LIBR_BOOK_GUI, new LibrarianBookingsGUI());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		myLogger.info("init class: " + this.getClass().toString());
		fillImageView(ivLogoTop, "resources/logoB.png");
		updateView();
	}
	
	public LibrarianBookingDetailsGUI(BookingBean bookingBean) {
		this.bookingBean = bookingBean;
		this.manageBookingsBoundary = new ManageBookingsBoundary(bookingBean);
	}
	
	private void updateView() {
		txBookingTime.setText(bookingBean.getDate() + " - " + bookingBean.getHour());
		txBookingType.setText(bookingBean.getTipo());
		txMailStudent.setText(bookingBean.getStudentId());
		txBookingItem.setText("booking item");
	}

}
