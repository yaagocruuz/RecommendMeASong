package br.ufc.quixada.recommendmeasong.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.recommendmeasong.dao.SongDAO;
import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.Song;
import br.ufc.quixada.recommendmeasong.pojo.User;

public class LoginController {
	private Connection loginConnection;
	private SongDAO songDAO = new SongDAO();
	
	public User login(String email, String password){
		String sql = "SELECT * FROM user_c WHERE email_user = ? and password_user = ?;";
		
		this.loginConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = loginConnection.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			User user = new User(rs.getInt("id_user"), rs.getString("name_user"), 
					rs.getString("email_user"), rs.getString("password_user"));
			
			return user;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				loginConnection.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Song> recomendations(int id_user){
		String sql = "((select s.id_song from user_c u, song s, user_has_genre usg, " +
				"user_has_artist uha where ? = usg.user_c " +
				"and ? = uha.user_c and (usg.genre = s.genre or " +
				"uha.artist in (select a.id_artist from artist a, band b " +
				"where a.band = b.id_band and s.band = b.id_band))) intersect ((select s.id_song from song s) " +
				"except (select s.id_song from song s, already_heard ah " +
				"where s.id_song = ah.song)));";
		
		List<Song> recomendations = new ArrayList<Song>();
		
		this.loginConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = loginConnection.prepareStatement(sql);
			stmt.setInt(1, id_user);
			stmt.setInt(2, id_user);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int id =  rs.getInt("id_song");
				
				Song song = songDAO.getSongById(id);
				recomendations.add(song);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				loginConnection.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return recomendations;
	}
	
	public boolean addAlreadyHeard(int id_user, int song){
		String sql = "INSERT INTO already_heard(user_c, song) VALUES (?, ?);";
		
		this.loginConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = loginConnection.prepareStatement(sql);
			stmt.setInt(1, id_user);
			stmt.setInt(2, song);
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				loginConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public List<Song> listAlreadyHeard(int id_user){
		String sql = "select song from already_heard where user_c = ?;";
		
		List<Song> alreadyHeard = new ArrayList<Song>();
		
		this.loginConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = loginConnection.prepareStatement(sql);
			stmt.setInt(1, id_user);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int id =  rs.getInt("song");
				
				Song song = songDAO.getSongById(id);
				alreadyHeard.add(song);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				loginConnection.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return alreadyHeard;
	}

}
