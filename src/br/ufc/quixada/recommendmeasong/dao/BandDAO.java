package br.ufc.quixada.recommendmeasong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.Band;

public class BandDAO {
	private Connection bandConnection;
	
	public BandDAO(){}
	
	public boolean addBand(Band band){
		String sql = "insert into band(name_band) values (?)";
		this.bandConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = bandConnection.prepareStatement(sql);
			stmt.setString(1, band.getName_band());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.bandConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<Band> getAllBands(){
		String sql = "select * from band";
		List<Band> bands = new ArrayList<Band>();
		this.bandConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = bandConnection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id_band"));
				String name = rs.getString("name_band");
				
				Band band = new Band(id, name);
				
				bands.add(band);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.bandConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return bands;
	}
	
	public Band getBandById(int id){
String sql = "SELECT * FROM band WHERE id_band = ?;";
		
		this.bandConnection = new ConnectionFactory().getConnection();
		try {
			// prepared statement para inserção
			PreparedStatement stmt = bandConnection.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			Band band = new Band(id, rs.getString("name_band"));
			
			stmt.close();
			
			return band;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.bandConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean upBand(int id, String name) {
		String sql = "UPDATE band SET name_band = ? where id_band = ?";
		
		this.bandConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = bandConnection.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, id);
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.bandConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean rmBand(int id) {
		String sql = "DELETE FROM band WHERE id_band = ?";
		
		this.bandConnection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = bandConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if (qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.bandConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
