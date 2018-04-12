package br.ufc.quixada.recommendmeasong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.Genre;

public class GenreDAO {
	private Connection genreConnection;
	
	public GenreDAO(){}
	
	public boolean addGenre(Genre genre) {
		String sql = "insert into genre (name_genre) values (?)";
		this.genreConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = genreConnection.prepareStatement(sql);
			
			stmt.setString(1, genre.getName_genre()); //coloca cada um dos valores
			
			int qtdAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdAffected > 0)
				return true;
			return false;
			
		}catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				this.genreConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<Genre> getAllGenres() {
		String sql = "SELECT * FROM genre;";
		List<Genre> genres = new ArrayList<Genre>();
		this.genreConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = genreConnection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id_genre");
				String name = rs.getString("name_genre");
				
				Genre genre = new Genre(id, name);
				genres.add(genre);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				this.genreConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return genres;
	}
	
	public Genre getGenreById(int id){
		String sql = "SELECT * FROM genre WHERE id_genre = ?;";
		
		this.genreConnection = new ConnectionFactory().getConnection();
		try {
			// prepared statement para inserção
			PreparedStatement stmt = genreConnection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// executa
			ResultSet rs = stmt.executeQuery();
			rs.next();

			Genre genre = new Genre(id, rs.getString("name_genre"));
			
			stmt.close();
			
			return genre;
		} catch (SQLException e) {
			System.out.println("a");
			System.err.println(e.getMessage());
		} finally {
			try {
				this.genreConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean upGenre(int id, String name) {
		String sql = "UPDATE genre SET name_genre = ? where id_genre = ?";
		
		this.genreConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = genreConnection.prepareStatement(sql);
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
				this.genreConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean rmGenre(int id) {
		String sql = "DELETE FROM genre WHERE id_genre = ?";
		
		this.genreConnection = new ConnectionFactory().getConnection();

		try {
			PreparedStatement stmt = genreConnection.prepareStatement(sql);
			stmt.setInt(1, id);

			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if (qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.genreConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
