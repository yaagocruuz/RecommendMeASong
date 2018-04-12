package br.ufc.quixada.recommendmeasong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.Artist;
import br.ufc.quixada.recommendmeasong.pojo.User;

public class UserHasArtistDAO {

	private Connection userHasArtistConnection;
	
	public UserHasArtistDAO(){ }
	
	public boolean addUserHasArtist(User user) {
		String sql = "INSERT INTO user_has_artist(user_c, artist) VALUES (?, ?);";
		this.userHasArtistConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userHasArtistConnection.prepareStatement(sql);
			for(Artist a : user.getFavorite_artists()){
				stmt.setInt(1, user.getId_user());
				stmt.setInt(2, a.getId_artist());
				
				int qtdRowsAffected = stmt.executeUpdate();
				stmt.close();
				
				if(qtdRowsAffected > 0) return true;
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				userHasArtistConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
