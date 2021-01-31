package logic.boundary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import logic.bean.AdvertiseBean;
import logic.bean.BookingBean;
import logic.bean.LibraryBean;
import logic.controller.ManageSeatsController;
import logic.exceptions.NoItemAvailableException;
import logic.exceptions.NoItemException;

public class ManageSeatsBoundary {
	protected static Logger myLogger = Logger.getLogger("logger");	

	private LibraryBean myLibraryBean;
	private List<BookingBean> myLibraryBookings;
	private ManageSeatsController manageSeatsController;
	private AdvertiseBean advertiseBean;
	
	public ManageSeatsBoundary(String libraryId) {
		this.myLibraryBean = new LibraryBean();
		this.myLibraryBookings = new ArrayList<>();
		this.myLibraryBean.setMailB(libraryId);
		this.manageSeatsController = null;
		this.advertiseBean = null;
	}

	public boolean updateSeats() {
		boolean res = false;
		manageSeatsController = new ManageSeatsController(myLibraryBean, myLibraryBookings);
		if(manageSeatsController.updateSeats()) {
			myLibraryBean = manageSeatsController.getMyLibraryBean();
			res = true;
		} else {
			myLogger.info("boundary: update seats failed");
		}
		return res;
	}
	
	public int visitorIn(String type) {
		int res = -1;
		manageSeatsController = new ManageSeatsController(myLibraryBean, myLibraryBookings);
		try {
			manageSeatsController.visitorIn(myLibraryBean.getMailB(), type, advertiseBean);
			res = 0;
		} catch (NoItemAvailableException e) {
			e.printStackTrace();
			advertiseBean.setMessage("No items available");
		} catch (SQLException e) {
			e.printStackTrace();
			advertiseBean.setMessage("ERROR: database item updating");
		}
		return res;
	}
	
	public void visitorOut(String type) {
		/*
		 * uscita visitatore
		 */
	}

	public LibraryBean getMyLibraryBean() {
		return myLibraryBean;
	}

	public void setMyLibraryBean(LibraryBean myLibraryBean) {
		this.myLibraryBean = myLibraryBean;
	}

	public List<BookingBean> getMyLibraryBookings() {
		return myLibraryBookings;
	}

	public void setMyLibraryBookings(List<BookingBean> myLibraryBookings) {
		this.myLibraryBookings = myLibraryBookings;
	}

	public void setAdvertiseBean(AdvertiseBean advertiseBean) {
		this.advertiseBean = advertiseBean;
	}

	public boolean releaseSeat(String biblioteca, String room, int seatNum) {
		boolean res = false;
		manageSeatsController = new ManageSeatsController();
		try {
			manageSeatsController.seatVisitorOut(biblioteca, room, seatNum);
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoItemException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean releasePC(String biblioteca, String pcName) {
		boolean res = false;
		manageSeatsController = new ManageSeatsController();
		try {
			manageSeatsController.pcVisitorOut(biblioteca, pcName);
			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoItemException e) {
			e.printStackTrace();
		}
		return res;
	}

}
