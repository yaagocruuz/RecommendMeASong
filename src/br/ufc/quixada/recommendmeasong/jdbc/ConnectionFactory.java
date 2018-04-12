package br.ufc.quixada.recommendmeasong.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final String ip = "localhost"; 
	private final Integer port = 5432;
	private final String user = "drigo"; //usuario do postgres
	private final String password = "1234"; //senha do servidor
	private final String database = "RecommendMeASong"; //
    
	public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+database, user, password); 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }	
}