package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.BookingPCBean;
import logic.bean.BookingSeatBean;
import logic.bean.BookingBean;
import logic.constants.BookingConstants;
import logic.constants.DatabaseColumns;
import logic.constants.DatabaseConstants;
import logic.entity.Booking;
import logic.entity.BookingPC;
import logic.entity.BookingSeat;

public class BookingDao extends DAOGeneral {
	
	public int insertBookSeat(String idBiblioteca, String idStudente, String orario, String data) {
		int status = 0;
		try {
			ps = con.prepareStatement(
					"INSERT INTO Prenotazione(mailBiblioteca,mailStudente,orarioPrenotazione, dataPrenotazione) VALUES(?,?,?,?)");
			ps.setString(1, idBiblioteca);
			ps.setString(2, idStudente);
			ps.setString(3, orario);
			ps.setString(4, data);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			myLogger.info("Salvataggio prenotazione fallito");
		} finally {
			finallyClosure(ps);
		}
		return status;
	}

	public List<BookingBean> selectBookingsFromLibraryIdAndType(String mailL, String tipoP) {
		ResultSet rs = null;
		List<BookingBean> results = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_BOOKINGS_FROM_LIBRARYID_TYPE);
			ps.setString(1,  mailL);
			ps.setString(2,  tipoP);
			rs = ps.executeQuery();
			while(rs.next()) {  
				BookingBean bookingBean;
				if(tipoP.equals(BookingConstants.TYPESEAT)) {
					bookingBean = new BookingSeatBean(
							rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString(DatabaseColumns.MAILSTUDENTE), 
						    rs.getString(DatabaseColumns.PRENOTAZIONEORARIO), rs.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    rs.getString(DatabaseColumns.PRENOTAZIONESTATO), rs.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    rs.getString(DatabaseColumns.SEATROOM), String.valueOf(rs.getInt(DatabaseColumns.SEATNUM)));
					results.add(bookingBean);
				} else if(tipoP.equals(BookingConstants.TYPEPC)) {
					bookingBean = new BookingPCBean(
							rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString(DatabaseColumns.MAILSTUDENTE), 
						    rs.getString(DatabaseColumns.PRENOTAZIONEORARIO), rs.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    rs.getString(DatabaseColumns.PRENOTAZIONESTATO), rs.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    rs.getString(DatabaseColumns.PCNAME));
					results.add(bookingBean);
				}
				 
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Select bookings from library id and type");
		} finally {
			finallyClosure(ps);
		}
		return results;
	}
	
	public List<Booking> selectActiveBookingFromStudentId(String mailS, String state) {
		ResultSet rs = null;
		List<Booking> bookings = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_HANGING_BOOKINGS_FROM_STUDENT);
			ps.setString(1,  mailS);
			ps.setString(2,  state);
			rs = ps.executeQuery();
			while(rs.next()) {
				String type = rs.getString(DatabaseColumns.PRENOTAZIONETIPO);
				Booking booking = null;
				if(type.equals(BookingConstants.TYPESEAT)) {
					booking = new BookingSeat(
							rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString(DatabaseColumns.MAILSTUDENTE), 
						    rs.getString(DatabaseColumns.PRENOTAZIONEORARIO), rs.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    rs.getString(DatabaseColumns.PRENOTAZIONESTATO), rs.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    rs.getString(DatabaseColumns.SEATROOM), String.valueOf(rs.getInt(DatabaseColumns.SEATNUM)));
				} else if(type.equals(BookingConstants.TYPEPC)) {
					booking = new BookingPC(
							rs.getString(DatabaseColumns.MAILBIBLIOTECA), rs.getString(DatabaseColumns.MAILSTUDENTE), 
						    rs.getString(DatabaseColumns.PRENOTAZIONEORARIO), rs.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    rs.getString(DatabaseColumns.PRENOTAZIONESTATO), rs.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    rs.getString(DatabaseColumns.PCNAME));
				}
				bookings.add(booking);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Select bookings from student id failed");
		} finally {
			finallyClosure(ps);
		}
		return bookings;
	}
	
	
	public List<Booking> selectBookingStudent(String mailS, String state, String tipo) {
		ResultSet resultSet = null;
		List<Booking> bookings = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_BOOKING_FROM_STUDENTID_STATE_TYPE);
			ps.setString(1,  mailS);
			ps.setString(2,  state);
			ps.setString(3,  tipo);
			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				String typeBooking = resultSet.getString(DatabaseColumns.PRENOTAZIONETIPO);
				Booking book = null;
				if(typeBooking.equals(BookingConstants.TYPESEAT)) {
					book = new BookingSeat(
							resultSet.getString(DatabaseColumns.MAILBIBLIOTECA), resultSet.getString(DatabaseColumns.MAILSTUDENTE), 
						    resultSet.getString(DatabaseColumns.PRENOTAZIONEORARIO), resultSet.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    resultSet.getString(DatabaseColumns.PRENOTAZIONESTATO), resultSet.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    resultSet.getString(DatabaseColumns.SEATROOM), String.valueOf(resultSet.getInt(DatabaseColumns.SEATNUM)));
				} else if(typeBooking.equals(BookingConstants.TYPEPC)) {
					book = new BookingPC(
							resultSet.getString(DatabaseColumns.MAILBIBLIOTECA), resultSet.getString(DatabaseColumns.MAILSTUDENTE), 
						    resultSet.getString(DatabaseColumns.PRENOTAZIONEORARIO), resultSet.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    resultSet.getString(DatabaseColumns.PRENOTAZIONESTATO), resultSet.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    resultSet.getString(DatabaseColumns.PCNAME));
				}
				bookings.add(book);
			}
			
		} catch (SQLException es) {
			es.printStackTrace();
			myLogger.info("Select bookings from student id, state, type failed");
		} finally {
			myLogger.info("Finally closing");
			finallyClosure(ps);
		}
		return bookings;
	}
	

	public int selectCountBookingsFromStudentId(String mailS) {
		
		ResultSet rs = null;
		int res = 0;
		try {
			ps = null;
			ps = con.prepareStatement(
						"SELECT COUNT(*) FROM Prenotazione WHERE mailStudente = ? AND statoPrenotazione = 'Hanging'");
			ps.setString(1, mailS);
			rs = ps.executeQuery();
			if (rs.next()) {
		        res = rs.getInt(1);
		        myLogger.info("numberOfRows= " + res);
		      } else {
		    	myLogger.info("error: could not get the record counts");
		      }

		} catch (SQLException e) {
			myLogger.info("Select booking failed");
		} finally {
			finallyClosure(ps);
		}
		return res;
	}
	
	public boolean saveBooking(Booking booking, String type) {
		boolean res = false;
		try {
			ps = con.prepareStatement(
					"INSERT INTO prenotazione (`mailStudente`, `mailBiblioteca`, `orarioPrenotazione`, `dataPrenotazione`, `statoPrenotazione`, `tipoPrenotazione`, `room`, `seatNum`, `pcName`) VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, booking.getMailStudente());
			ps.setString(2, booking.getMailBiblioteca());
			ps.setString(3, booking.getOrarioPrenotazione());
			ps.setString(4, booking.getDataPrenotazione());
			ps.setString(5, booking.getStatoPrenotazione());
			ps.setString(6, booking.getTipoPrenotazione());
			if(type.equals(BookingConstants.TYPESEAT)) {
				ps.setString(7, ((BookingSeat)booking).getRoom());
				ps.setInt(8, ((BookingSeat)booking).getSeatNum());
				ps.setString(9, "");
			} else if(type.equals(BookingConstants.TYPEPC)) {
				ps.setString(7, "");
				ps.setInt(8, -1);
				ps.setString(9, ((BookingPC)booking).getPcName());
			}
			
			ps.execute();
			res = true;
			
		} catch (SQLException e) {
			myLogger.info("Eccezione booking save seat");
		} finally {
			myLogger.info("Finally save");
			finallyClosure(ps);
		}

		return res;
	}

	public boolean deleteBooking(String mailS, String type, String state) {
		boolean res = false;
		try {
			ps = con.prepareStatement(
					"DELETE FROM prenotazione WHERE mailStudente=? AND tipoPrenotazione=? AND statoPrenotazione=?");
			ps.setString(1, mailS);
			ps.setString(2, type);
			ps.setString(3, state);
			ps.execute();
			res = true;
		} catch (SQLException e) {
			myLogger.info("Eccezione booking delete");
		} finally {
			myLogger.info("Finally delete");
			finallyClosure(ps);
		}
		return res;
	}

	public int updateBookingState(Booking book) {
		int status = 0;
		ps = null;
		try {
			ps = con.prepareStatement("UPDATE prenotazione SET statoPrenotazione=? WHERE mailStudente = ? AND mailBiblioteca = ? AND tipoPrenotazione = ? AND orarioPrenotazione=? AND dataPrenotazione=?");
			ps.setString(1, book.getStatoPrenotazione());
			ps.setString(2, book.getMailStudente());
			ps.setString(3, book.getMailBiblioteca());
			ps.setString(4, book.getTipoPrenotazione());
			ps.setString(5, book.getOrarioPrenotazione());
			ps.setString(6, book.getDataPrenotazione());
			status = ps.executeUpdate();
		}

		catch (SQLException | NullPointerException e) {
			myLogger.info("Aggiornamento booking fallito");
			e.printStackTrace();
			return status;
		} finally {
			myLogger.info("Aggiornamento booking finally");
			finallyClosure(ps);
		}
		return status;		
	}
	
	public List<Booking> selectBookingFromStudentAndDateTime(String mailS, String date, String hour) throws SQLException {
		ResultSet resultSetB = null;
		List<Booking> books = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement("SELECT * FROM prenotazione WHERE mailStudente=? AND dataPrenotazione=? AND orarioPrenotazione=?");
			ps.setString(1,  mailS);
			ps.setString(2,  date);
			ps.setString(3,  hour);
			resultSetB = ps.executeQuery();
			while(resultSetB.next()) {
				String tipoB = resultSetB.getString(DatabaseColumns.PRENOTAZIONETIPO);
				Booking bookB = null;
				if(tipoB.equals(BookingConstants.TYPESEAT)) {
					bookB = new BookingSeat(
							resultSetB.getString(DatabaseColumns.MAILBIBLIOTECA), resultSetB.getString(DatabaseColumns.MAILSTUDENTE), 
						    resultSetB.getString(DatabaseColumns.PRENOTAZIONEORARIO), resultSetB.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    resultSetB.getString(DatabaseColumns.PRENOTAZIONESTATO), resultSetB.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    resultSetB.getString(DatabaseColumns.SEATROOM), String.valueOf(resultSetB.getInt(DatabaseColumns.SEATNUM)));
				} else if(tipoB.equals(BookingConstants.TYPEPC)) {
					bookB = new BookingPC(
							resultSetB.getString(DatabaseColumns.MAILBIBLIOTECA), resultSetB.getString(DatabaseColumns.MAILSTUDENTE), 
						    resultSetB.getString(DatabaseColumns.PRENOTAZIONEORARIO), resultSetB.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    resultSetB.getString(DatabaseColumns.PRENOTAZIONESTATO), resultSetB.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    resultSetB.getString(DatabaseColumns.PCNAME));
				}
				books.add(bookB);
			}
			
		} finally {
			myLogger.info("Finally selectBookingFromStudentAndDateTime");
			finallyClosure(ps);
		}
		return books;
	}

	public List<BookingBean> selectBookingsForLibrary(String biblioteca) {
		ResultSet resSet = null;
		List<BookingBean> results = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement("SELECT * FROM prenotazione WHERE mailBiblioteca = ? AND statoPrenotazione = 'Hanging'");
			ps.setString(1,  biblioteca);
			resSet = ps.executeQuery();
			while(resSet.next()) {  
				BookingBean bookingBeanB;
				if(resSet.getString(DatabaseColumns.PRENOTAZIONETIPO).equals(BookingConstants.TYPESEAT)) {
					bookingBeanB = new BookingSeatBean(
							resSet.getString(DatabaseColumns.MAILBIBLIOTECA), resSet.getString(DatabaseColumns.MAILSTUDENTE), 
						    resSet.getString(DatabaseColumns.PRENOTAZIONEORARIO), resSet.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    resSet.getString(DatabaseColumns.PRENOTAZIONESTATO), resSet.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    resSet.getString(DatabaseColumns.SEATROOM), String.valueOf(resSet.getInt(DatabaseColumns.SEATNUM)));
					results.add(bookingBeanB);
				} else if(resSet.getString(DatabaseColumns.PRENOTAZIONETIPO).equals(BookingConstants.TYPEPC)) {
					bookingBeanB = new BookingPCBean(
							resSet.getString(DatabaseColumns.MAILBIBLIOTECA), resSet.getString(DatabaseColumns.MAILSTUDENTE), 
						    resSet.getString(DatabaseColumns.PRENOTAZIONEORARIO), resSet.getString(DatabaseColumns.PRENOTAZIONEDATA), 
						    resSet.getString(DatabaseColumns.PRENOTAZIONESTATO), resSet.getString(DatabaseColumns.PRENOTAZIONETIPO), 
						    resSet.getString(DatabaseColumns.PCNAME));
					results.add(bookingBeanB);
				}
				 
			}	
		} catch (SQLException eq) {
			eq.printStackTrace();
			myLogger.info("Select bookngs from library id");
		} finally {
			myLogger.info("Closure of Select bookings from library id");
			finallyClosure(ps);
		}
		return results;
	}

	public int selectCountBookingsFromLibrary(String biblioteca, String typeseat) {
		ResultSet resultSetInt = null;
		int risult = 0;
		try {
			ps = null;
			ps = con.prepareStatement(
						"SELECT COUNT(*) FROM Prenotazione WHERE mailBiblioteca = ? AND tipoPrenotazione = ? AND statoPrenotazione = 'Hanging'");
			ps.setString(1, biblioteca);
			ps.setString(2, typeseat);
			resultSetInt = ps.executeQuery();
			if (resultSetInt.next()) {
		        risult = resultSetInt.getInt(1);
		        myLogger.info("numberOfRows= " + risult);
		      } else {
		    	myLogger.info("error: could not get the record counts");
		      }

		} catch (SQLException e) {
			myLogger.info("Select numb bookings failed");
		} finally {
			myLogger.info("Select num bookings closing");
			finallyClosure(ps);
		}
		return risult;
	}

}
