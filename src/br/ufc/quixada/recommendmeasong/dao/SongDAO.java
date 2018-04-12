package br.ufc.quixada.recommendmeasong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.Band;
import br.ufc.quixada.recommendmeasong.pojo.Genre;
import br.ufc.quixada.recommendmeasong.pojo.Song;

public class SongDAO {
	private Connection songConnection;
	private GenreDAO genreDAO = new GenreDAO();
	private BandDAO bandDAO = new BandDAO();
	
	public SongDAO(){}
	
	public boolean addSong(Song song) {
		String sql = "INSERT INTO song (name_song, genre, band) VALUES (?, ?, ?)";
		
		this.songConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = songConnection.prepareStatement(sql);
			stmt.setString(1, song.getName_song());
			stmt.setInt(2, song.getGenre().getId_genre());
			stmt.setInt(3, song.getBand().getId_band());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				songConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean upSong(Song song) {
		String sql = "UPDATE song SET name_band = ? and genre = ? and " +
				"band = ? WHERE id_song = ?";
		this.songConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = songConnection.prepareStatement(sql);
			stmt.setString(1, song.getName_song());
			stmt.setInt(2, song.getGenre().getId_genre());
			stmt.setInt(3, song.getBand().getId_band());
			stmt.setInt(4, song.getId_song());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				songConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean rmSong(int id) {
		String sql = "DELETE FROM song id_song = ?";
		
		this.songConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = songConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				songConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Song getSongById(int id) {
		String sql = "SELECT * FROM song WHERE id_song = ?";
		
		this.songConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = songConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Song song = new Song(id, rs.getString("name_song"), 
					genreDAO.getGenreById(rs.getInt("genre")), 
					bandDAO.getBandById(rs.getInt("band")));
			
			stmt.close();
			
			return song;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				songConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Song> getAllSongs() {
		String sql = "SELECT * FROM song;";
		
		List<Song> songs = new ArrayList<Song>();
		
		this.songConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = songConnection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id_song");
				String name = rs.getString("name_song");
				Genre genre = genreDAO.getGenreById(rs.getInt("genre"));
				Band band = bandDAO.getBandById(rs.getInt("band"));
				
				Song song = new Song(id, name, genre, band);
				
				songs.add(song);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				songConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return songs;
	}
}
