package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import logic.constants.BookingConstants;
import logic.constants.DatabaseColumns;
import logic.constants.DatabaseConstants;
import logic.entity.BookableItem;
import logic.entity.PC;
import logic.entity.Seat;

public class BookableItemDao extends DAOGeneral {

	public BookableItem selectAvailableItem(String mailL, String type) {
		ResultSet rs = null;
		BookableItem result = null;
		String query = null;
		if(type.equals(BookingConstants.TYPESEAT)) {
			query = DatabaseConstants.SELECT_AVAILABLE_SEAT_FROM_LIBRARY;
		} else if(type.equals(BookingConstants.TYPEPC)) {
			query = DatabaseConstants.SELECT_AVAILABLE_PC_FROM_LIBRARY;
		}
		try {
			ps = null;
			ps = con.prepareStatement(query);
			ps.setString(1,  mailL);
			rs = ps.executeQuery();
			rs.first();
			if(type.equals(BookingConstants.TYPESEAT)) {
				result = new Seat(rs.getString("statoSedia"), rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString("room"), 
						    rs.getInt("seatNumber"));
				myLogger.info("SEAT - ROOM: " + ((Seat)result).getSeatRoom() +  "NUM: " + ((Seat)result).getSeatNum());
			} else if(type.equals(BookingConstants.TYPEPC)) {
				result = new PC(rs.getString("statoPC"), rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString("pcName"));
				myLogger.info("PC: " + ((PC)result).getPcName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Search available item from library id failed");
		} finally {
			finallyClosure(ps);
		}
		return result;
	}
	
	public BookableItem selectSeat(String mail, String room, int seatNum) {
		ResultSet rs = null;
		BookableItem result = null;
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_SEAT_FROM_LIBRARYID_FIELDS);
			ps.setString(1,  mail);
			ps.setString(2,  room);
			ps.setInt(3, seatNum);
			rs = ps.executeQuery();
			rs.first();
			result = new Seat(rs.getString("statoSedia"), rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString("room"), 
					    rs.getInt("seatNumber"));
			myLogger.info("SEAT - ROOM: " + ((Seat)result).getSeatRoom() +  "NUM: " + ((Seat)result).getSeatNum());
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Select seat failed");
		} finally {
			finallyClosure(ps);
		}
		return result;
	}
	
	public int updateBookableItem(BookableItem bookableItem, String type) throws SQLException {
		int status = 0;
		ps = null;
		try {
			if(type.equals(BookingConstants.TYPESEAT)) {
				ps = con.prepareStatement("UPDATE Sedia SET statoSedia = ? WHERE mailBiblioteca = ? AND room = ? AND seatNumber = ?");
				ps.setString(1, bookableItem.getStateItem());
				ps.setString(2, bookableItem.getIdLibrary());
				ps.setString(3, ((Seat)bookableItem).getSeatRoom());
				ps.setInt(4, ((Seat)bookableItem).getSeatNum());
			} else if(type.equals(BookingConstants.TYPEPC)) {
				ps = con.prepareStatement("UPDATE PC SET statoPC = ? WHERE mailBiblioteca = ? AND pcName = ?");
				ps.setString(1, bookableItem.getStateItem());
				ps.setString(2, bookableItem.getIdLibrary());
				ps.setString(3, ((PC)bookableItem).getPcName());
			}
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

	public BookableItem selectPC(String mailL, String pcName) {
		ResultSet rs = null;
		BookableItem result = null;
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_PC_FROM_LIBRARY_PCNAME);
			ps.setString(1,  mailL);
			ps.setString(2,  pcName);
			rs = ps.executeQuery();
			rs.first();
			result = new PC(rs.getString("statoPC"), rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString("pcName"));
			myLogger.info("PC - NAME: " + ((PC)result).getPcName());
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Select pc failed");
		} finally {
			finallyClosure(ps);
		}
		return result;
	}

	public int selectCountFreeItems(String biblioteca, String typeseat) {
		ResultSet rs = null;
		int res = 0;
		String query = null;
		if(typeseat.equals(BookingConstants.TYPESEAT)) {
			query = "SELECT COUNT(*) FROM Sedia WHERE mailBiblioteca = ? AND statoSedia = 'Disponibile'";
		} else if(typeseat.equals(BookingConstants.TYPEPC)) {
			query = "SELECT COUNT(*) FROM PC WHERE mailBiblioteca = ? AND statoPC = 'Disponibile'";
		}
		try {
			ps = null;
			ps = con.prepareStatement(query);
			ps.setString(1, biblioteca);
			rs = ps.executeQuery();
			if (rs.next()) {
		        res = rs.getInt(1);
		      } else {
		    	myLogger.info("error: could not get count free items");
		      }

		} catch (SQLException e) {
			myLogger.info("Select count free items failed");
		} finally {
			finallyClosure(ps);
		}
		return res;
	}

}
