package logic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.bean.LibraryBean;
import logic.constants.DatabaseConstants;

public class LibraryDao extends DAOGeneral {
	
	public LibraryBean selectLibraryFromId(String libraryId) {
		ResultSet rs = null;
		LibraryBean result = null;
		myLogger.info("DAO = " + libraryId);
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_LIBRARY_FROM_MAIL);
			ps.setString(1,  libraryId);
			rs = ps.executeQuery();
			while(rs.next()) {    
					result = new LibraryBean(rs.getString("mailBiblioteca"), rs.getString("nomeBiblioteca"), 
							    rs.getString("indirizzoBiblioteca"),   rs.getString("cittaBiblioteca"), rs.getString("telefonoBiblioteca"), String.valueOf(rs.getInt("postiTotali")), String.valueOf(rs.getInt("postiOccupati")), String.valueOf(rs.getInt("pcTotali")), String.valueOf(rs.getInt("pcOccupati")));
					myLogger.info("DAO result = " + result.getNameB());
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Search library from id failed");
		} finally {
			finallyClosure(ps);
		}
		return result;
	}
	
	public List<LibraryBean> selectLibrariesFromCity(String cityIn) {
		ResultSet rs = null;
		List<LibraryBean> results = new ArrayList<>();
		try {
			ps = null;
			ps = con.prepareStatement(DatabaseConstants.SELECT_LIBRARIES_FROM_CITY);
			ps.setString(1,  cityIn);
			rs = ps.executeQuery();
			while(rs.next()) {    
					LibraryBean libraryBean = new LibraryBean(rs.getString("mailBiblioteca"), rs.getString("nomeBiblioteca"), 
							    rs.getString("indirizzoBiblioteca"),   rs.getString("cittaBiblioteca"), rs.getString("telefonoBiblioteca"), String.valueOf(rs.getInt("postiTotali")), String.valueOf(rs.getInt("postiOccupati")), String.valueOf(rs.getInt("pcTotali")), String.valueOf(rs.getInt("pcOccupati")));
					results.add(libraryBean);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			myLogger.info("Search libraries from city failed");
		} finally {
			finallyClosure(ps);
		}
		return results;
	}

	public int updateLibrary(LibraryBean libraryBean) {
		int status = 0;
		ps = null;
		try {
			ps = con.prepareStatement("UPDATE biblioteca SET postiOccupati=? AND pcOccupati=? WHERE mailBiblioteca = ?");
			ps.setInt(1, libraryBean.getSedieOccupate());
			ps.setInt(2, libraryBean.getSedieOccupate());
			ps.setString(3, libraryBean.getMailB());
			status = ps.executeUpdate();
		}

		catch (SQLException | NullPointerException e) {
			myLogger.info("Aggiornamento library fallito");
			e.printStackTrace();
			return status;
		} finally {
			myLogger.info("Aggiornamento library finally");
			finallyClosure(ps);
		}
		return status;	
	}
	
}
