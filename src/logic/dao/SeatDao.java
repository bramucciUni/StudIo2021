package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import logic.bean.SeatBean;
import logic.constants.DatabaseConstants;

public class SeatDao extends DAOGeneral {

	public SeatBean selectAvailableSeat(String mailL) {
		ResultSet rs = null;
		SeatBean result = null;
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_AVAILABLE_SEAT_FROM_LIBRARY);
			ps.setString(1,  mailL);
			rs = ps.executeQuery();
			rs.first();
			result = new SeatBean(rs.getString("mailBiblioteca"), rs.getString("room"), 
					    rs.getInt("seatNumber"),   rs.getString("statoSedia"));
			myLogger.info("SEAT: " + result.getSeatNumber());

		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Search available seat from library id failed");
		} finally {
			finallyClosure(ps);
		}
		return result;
	}

	public int updateBusySeat(String mailL, String room, int seatNumber) throws SQLException {
		int status = 0;
		ps = null;
		try {
			ps = con.prepareStatement("UPDATE Sedia SET statoSedia = 'Occupato' WHERE mailBiblioteca = ? AND room=? AND seatNumber=?");
			ps.setString(1, mailL);
			ps.setString(2, room);
			ps.setInt(3, seatNumber);
			status = ps.executeUpdate();
		}

		catch (SQLException | NullPointerException e) {
			myLogger.info("Aggiornamento posto fallito");
			e.printStackTrace();
			return status;
		} finally {
			finallyClosure(ps);
		}
		return status;		
	}

}
