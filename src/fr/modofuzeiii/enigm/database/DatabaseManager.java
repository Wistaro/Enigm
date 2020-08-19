package fr.modofuzeiii.enigm.database;

import java.sql.SQLException;

public class DatabaseManager {
	
	private DBConnection enigmConnectionDb;
	
	
	public DatabaseManager() {
		this.enigmConnectionDb = new DBConnection(new DBCredentials("db4free.net", "fiouze", "recrutements", "enigm_bdd", 3306));
	}
	
	public void close() {
		try {
			this.enigmConnectionDb.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DBConnection getEnigmConnection() {
		return enigmConnectionDb;
	}
	
}
