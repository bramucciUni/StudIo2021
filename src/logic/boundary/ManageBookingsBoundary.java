package logic.boundary;

import java.sql.SQLException;

import logic.bean.BookingBean;
import logic.constants.BookingConstants;
import logic.controller.ManageBookingController;

public class ManageBookingsBoundary {
	
	private BookingBean bookingBean;
	private ManageBookingController manageBookingController;
	
	public ManageBookingsBoundary(BookingBean bookingBean) {
		super();
		this.bookingBean = bookingBean;
		this.manageBookingController = new ManageBookingController(bookingBean);
	}
	
	public int validateBooking() {
		int res = -1;
		try {
			manageBookingController.validateBooking(bookingBean.getStudentId(), bookingBean.getDate(), bookingBean.getHour(), BookingConstants.ACCEPTED);
			res = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int invalidateBooking() {
		int res = -1;
		try {
			manageBookingController.validateBooking(bookingBean.getStudentId(), bookingBean.getDate(), bookingBean.getHour(), BookingConstants.DECLINED);
			res = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}
