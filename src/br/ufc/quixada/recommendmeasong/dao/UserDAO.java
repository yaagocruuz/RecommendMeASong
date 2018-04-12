package br.ufc.quixada.recommendmeasong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.recommendmeasong.jdbc.ConnectionFactory;
import br.ufc.quixada.recommendmeasong.pojo.User;

public class UserDAO {
	private Connection userConnection;
	
	public UserDAO() {}
	
	public boolean addUser(User user) {
		String sql = "INSERT INTO user_c(name_user, email_user, password_user) VALUES (?, ?, ?);";
		
		this.userConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userConnection.prepareStatement(sql);
			stmt.setString(1, user.getName_user());
			stmt.setString(2, user.getEmail_user());
			stmt.setString(3, user.getPassword_user());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				userConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean upUser(User user) {
		String sql = "UPDATE user_c SET name_user = ? and email_user = ? " +
				"and password_user = ? WHERE id_user = ?";
		
		this.userConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userConnection.prepareStatement(sql);
			stmt.setString(1, user.getName_user());
			stmt.setString(2, user.getEmail_user());
			stmt.setString(3, user.getPassword_user());
			stmt.setString(4, user.getPassword_user());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e){
			System.err.println(e.getMessage());
		} finally {
			try {
				userConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean rmUser(int id) {
		String sql = "DELETE FROM user_c where id_user = ?";
		
		this.userConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			
			if(qtdRowsAffected > 0) return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				userConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public int getIdByEmail(String email_user) {
		String sql = "SELECT id_user FROM user_c WHERE email_user = ?;";
		
		this.userConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userConnection.prepareStatement(sql);
			stmt.setString(1, email_user);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id = rs.getInt("id_user");
			return id;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				userConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	public User getUserById(int id) {
		String sql = "SELECT * FROM user_c WHERE id_user = ?";
		
		this.userConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userConnection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			User user = new User(id, rs.getString("name_user"), 
					rs.getString("email.user"), rs.getString("password_user"));
			stmt.close();
			
			return user;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				userConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM user_c";
		
		List<User> users = new ArrayList<User>();
		
		this.userConnection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = userConnection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id_user");
				String name = rs.getString("name_user");
				String email = rs.getString("email_user");
				String password = rs.getString("password_user");
				
				User user = new User(id, name, email, password);
				
				users.add(user);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				userConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}
}
