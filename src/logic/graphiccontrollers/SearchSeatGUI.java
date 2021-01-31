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
import javafx.scene.control.TextField;
import logic.application.Main;
import logic.bean.LibraryBean;
import logic.bean.SearchSeatBean;
import logic.boundary.SearchSeatBoundary;

public class SearchSeatGUI extends GUIGeneral {

	@FXML private TextField tfSearchSeat;
	@FXML private Button btnSearchSeatOk;
	@FXML private Button btnBack;
	@FXML private Button btnNext;
	@FXML private Label lbTextTop;
	@FXML private Label lbErrorMessages;
	@FXML private ListView<String> lvSearchSeatResults;
	
    private Integer lvIndexSelected;
    private List<LibraryBean> results;
    private SearchSeatBean searchSeatBean;
    private SearchSeatBoundary searchSeatBoundary;
    
    public SearchSeatGUI() {
    	this.lvSearchSeatResults = new ListView<>();
    	this.searchSeatBean = new SearchSeatBean();
    	this.results = new ArrayList<>();
    	this.searchSeatBoundary = null;
	}
    
    /*usata se ho gia' effettuato la ricerca*/
    public SearchSeatGUI(SearchSeatBean searchSeatBean) {
    	this.lvSearchSeatResults = new ListView<>();
    	this.searchSeatBean = searchSeatBean;
    	this.results = searchSeatBean.getSearchResults();
	}
    
    /*
	 * Metodo per aggiornare la listView
	 */
	private void updateListView() {
		myLogger.info("updateListView");
		lvSearchSeatResults.getItems().clear();
		for(int i=0; i<results.size(); i++) {
			lvSearchSeatResults.getItems().add(results.get(i).getNameB());
			myLogger.info("list insert " + i);
		}
	}

    @FXML
    void btnSearchSeatOkPressed(ActionEvent event) {
    	myLogger.info("btnSearchSeatOkPressed");
    	lbErrorMessages.setVisible(false);
    	if(tfSearchSeat.getText().isEmpty()) {
			lbErrorMessages.setText("Please insert a city");
			lbErrorMessages.setVisible(true);
		} else {
			searchSeatBean.setLocationIn(tfSearchSeat.getText().toString());
			searchSeatBoundary = new SearchSeatBoundary(searchSeatBean);
			if(searchSeatBoundary.searchSeat()) {
				results = searchSeatBean.getSearchResults();
			} else {
				lbErrorMessages.setText("Libraries not found");
				lbErrorMessages.setVisible(true);
				results = new ArrayList<>();
			}
			updateListView();
		}
	}

    @FXML
    void btnBackPressed(ActionEvent event) {
    	myLogger.info("btnBackPressed");
    	try {
			handlerFXML.setGui("StudentGUI", new StudentGUI());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnNextPressed(ActionEvent event) {
    	myLogger.info("btnNextPressed");
    	if(lvIndexSelected == -1) {
    		if(!lbErrorMessages.isVisible()) lbErrorMessages.setVisible(true);
    		lbErrorMessages.setText("Please select a result");
    		return;
    	}
    	if(lbErrorMessages.isVisible()) lbErrorMessages.setVisible(false);
    	searchSeatBean.setIndexSelected(lvIndexSelected);
    	try {
			handlerFXML.setGui("SearchResultGUI", new SearchResultGUI(searchSeatBean));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void lvItemPressed() {	/* !non deve avere action event */
    	myLogger.info("lvItemPressed");
    	lvIndexSelected = lvSearchSeatResults.getSelectionModel().getSelectedIndex();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		myLogger.info("init class: " + this.getClass().toString());
		fillImageView(ivLogoTop, "resources/logoB.png");
		this.lbErrorMessages.setVisible(false);
		this.lvIndexSelected = -1;
		this.tfSearchSeat.setText("");
		this.tfSearchSeat.setPromptText("Insert here your city...");
		this.lbTextTop.setText(Main.getUserBean().getUtenteUsername());
		updateListView();
	}

}
