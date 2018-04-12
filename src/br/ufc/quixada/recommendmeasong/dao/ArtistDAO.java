package br.ufc.quixada.recommendmeasong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.Artist;
import br.ufc.quixada.recommendmeasong.pojo.Band;

public class ArtistDAO {
	private Connection artistConnection;
	private BandDAO bandDAO = new BandDAO();
	
	public ArtistDAO(){}
	
	public boolean addArtist(Artist artist) {
		String sql = "INSERT INTO artist (name_artist, band) VALUES (?, ?)";
		
		this.artistConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = artistConnection.prepareStatement(sql);
			stmt.setString(1, artist.getName_artist());
			stmt.setInt(2, artist.getBand().getId_band());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				artistConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean upArtist(int id, String name, Band band) {
		String sql = "UPDATE artist SET name_artist = ?, band = ? WHERE id_artist = ?;";
		
		this.artistConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = artistConnection.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, band.getId_band());
			stmt.setInt(3, id);
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				artistConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean rmArtist(int id) {
		String sql = "DELETE FROM artist WHERE id_artist = ?;";
		
		this.artistConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = artistConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				artistConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Artist getArtistById(int id) {
		String sql = "SELECT * FROM artist where id_artist = ?";
		
		this.artistConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = artistConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Artist artist = new Artist(id, rs.getString("name_artist"), 
					bandDAO.getBandById(rs.getInt("band")));
			
			stmt.close();
			
			return artist;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				artistConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Artist> getAllArtists() {
		String sql = "SELECT * FROM artist;";
		List<Artist> artists = new ArrayList<Artist>();
		this.artistConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = artistConnection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id_artist"));
				String name = rs.getString("name_artist");
				Band band = bandDAO.getBandById(rs.getInt("band"));
				
				Artist artist = new Artist(id, name, band);
				
				artists.add(artist);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				artistConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return artists;
	}
}
