package fr.modofuzeiii.enigm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sun.istack.internal.logging.Logger;

public class DBConnection {
	
	private DBCredentials dBcredentials;
	private Connection connection;
	
	public DBConnection(DBCredentials dbCredentials) {
		
		this.dBcredentials = dbCredentials;
		this.connect();
				
				
	}
	
	private void connect() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			this.connection = DriverManager.getConnection(this.dBcredentials.toURI(), this.dBcredentials.getUser(), this.dBcredentials.getPass());
			
			System.out.println("Connection à la base de donnée effectuée!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() throws SQLException {
		if(this.connection != null) {
			if(!this.connection.isClosed()) {
				this.connection.close();
			}
		}
	}
	
	public Connection getConnection() throws SQLException {
		
		if(this.connection != null) {
			
			if(!this.connection.isClosed()) {
				
				return this.connection;
			}
		}
		
		connect();
		return this.connection;
	}
}
