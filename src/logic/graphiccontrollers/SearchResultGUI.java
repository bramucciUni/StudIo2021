package logic.graphiccontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import logic.application.Main;
import logic.bean.BookingPCBean;
import logic.bean.BookingSeatBean;
import logic.bean.LibraryBean;
import logic.bean.SearchSeatBean;
import logic.boundary.BookingBoundary;
import logic.constants.BookingConstants;
import logic.dao.BookableItemDao;

/*
 * priva trav commit
 */
public class SearchResultGUI extends GUIGeneral {

    @FXML private Label lbTextTop;
    @FXML private Label lbTopAlerts;
    @FXML private Text txNome;
    @FXML private Text txIndirizzo;
    @FXML private Text txTelefono;
    @FXML private Text txMail;
    @FXML private Text txPostiLiberi;
    @FXML private Text txPCLiberi;
    @FXML private Button btnBookSeat;
    @FXML private Button btnBookPC;
    @FXML private Button btnReportIssue;
    @FXML private Button btnNoticeboard;
    @FXML private Button btnTimetable;
    @FXML private Button btnMap;
    @FXML private Button btnBack;
    
    private SearchSeatBean searchSeatBean;
    private BookingSeatBean bookSeatBean;
    private BookingPCBean bookPCBean;
    private BookingBoundary bookingBoundary;
    
    /*coming from searchSeat*/
    public SearchResultGUI(SearchSeatBean searchSeatBean) {
    	this.searchSeatBean = searchSeatBean;
    	this.bookSeatBean = null;
    	this.bookPCBean = null;
	}
    
    @FXML
    void btnBackPressed(ActionEvent event) throws IOException {
    	myLogger.info("backPressed");
    	if(searchSeatBean.getSearchResults().size() == 0) {
    		handlerFXML.setGui("StudentGUI", new StudentGUI());
    	}
    	else {
			handlerFXML.setGui("SearchSeatGUI", new SearchSeatGUI(searchSeatBean));
		}
    }

    @FXML
    void btnBookSeatPressed(ActionEvent event) {
    	myLogger.info("btnBookSeatPressed");
    	
    	bookSeatBean = new BookingSeatBean(searchSeatBean.getSearchResults().get(searchSeatBean.getIndexSelected()).getMailB(), Main.getUserBean().getEmailUtente());
    	bookingBoundary = new BookingBoundary(bookSeatBean);
    	if(bookingBoundary.book(BookingConstants.TYPESEAT)) {
        	myLogger.info("GUI: OK seat booked " + bookSeatBean.getHour() + " " + bookSeatBean.getTipo() + " " + bookSeatBean.getStato());
			alertShow(AlertType.INFORMATION, "Seat booking outcome", "Seat booking created", "Your seat booking is guaranteed for 15 minutes");
    	} else {
    		myLogger.info("GUI: Booking seat failed");
    	}
		lbTopAlerts.setText(bookSeatBean.getBookingAdvertise());
    	
    }     
    
    @FXML
    void btnBookPCPressed(ActionEvent event) {
    	myLogger.info("btnBookPCPressed");
    	
    	bookPCBean = new BookingPCBean(searchSeatBean.getSearchResults().get(searchSeatBean.getIndexSelected()).getMailB(), Main.getUserBean().getEmailUtente());
    	bookingBoundary = new BookingBoundary(bookPCBean);
    	if(bookingBoundary.book(BookingConstants.TYPEPC)) {
        	myLogger.info("GUI: OK pc booked " + bookPCBean.getHour() + " " + bookPCBean.getTipo() + " " + bookPCBean.getStato());
			alertShow(AlertType.INFORMATION, "PC booking outcome", "PC booking created", "Your PC booking is guaranteed for 15 minutes");
    	} else {
    		myLogger.info("GUI: Booking PC failed");
    	}
		lbTopAlerts.setText(bookPCBean.getBookingAdvertise());
    }
    
    @FXML
    void btnMapPressed(ActionEvent event) {
    	myLogger.info("btnMapPressed");
    }

    @FXML
    void btnNoticeboardPressed(ActionEvent event) {
    	myLogger.info("btnNoticeboardPressed");
    }

    @FXML
    void btnReportIssuePressed(ActionEvent event) {
    	myLogger.info("btnReportIssuePressed");
    }

    @FXML
    void btnTimetablePressed(ActionEvent event) {
    	myLogger.info("btnTimetablePressed");
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	myLogger.info("init class: " + this.getClass().toString());
		fillImageView(ivLogoTop, "resources/logoB.png");
    	this.lbTopAlerts.setText("");
		this.lbTextTop.setText(Main.getUserBean().getUtenteUsername());
		LibraryBean librarySelected = searchSeatBean.getSearchResults().get(searchSeatBean.getIndexSelected());
		BookableItemDao itemDao = new BookableItemDao();
		int fSeats = itemDao.selectCountFreeItems(librarySelected.getMailB(), BookingConstants.TYPESEAT);
		int fPc = itemDao.selectCountFreeItems(librarySelected.getMailB(), BookingConstants.TYPEPC);
		this.txNome.setText(librarySelected.getNameB());
		this.txIndirizzo.setText(librarySelected.getIndirizzoB());
		this.txMail.setText(librarySelected.getMailB());
		this.txTelefono.setText(librarySelected.getTelefonoB());
		this.txPostiLiberi.setText(String.valueOf(fSeats));
		this.txPCLiberi.setText(String.valueOf(fPc));
	}

}
