package br.ufc.quixada.recommendmeasong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.Genre;
import br.ufc.quixada.recommendmeasong.pojo.User;

public class UserHasGenreDAO {
	
	private Connection userhasGenreConnection;
	
	public UserHasGenreDAO(){ }
	
	public boolean addUserHasGenre(User user) {
		String sql = "INSERT INTO user_has_genre(user_c, genre) VALUES (?, ?);";
		this.userhasGenreConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userhasGenreConnection.prepareStatement(sql);
			for(Genre g : user.getFavorite_genres()){
				stmt.setInt(1, user.getId_user());
				stmt.setInt(2, g.getId_genre());
				
				int qtdRowsAffected = stmt.executeUpdate();
				stmt.close();
				
				if(qtdRowsAffected > 0) return true;
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				userhasGenreConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
