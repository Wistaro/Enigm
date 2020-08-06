package fr.modofuzeiii.enigm.database;

public class DBCredentials {
	
	private String host;
	private String user;
	private String pass;
	private String DBName;
	private int port;
	
	public DBCredentials (String host, String user, String pass, String DBName, int port) {
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.DBName = DBName;
		this.port = port;
		
	}
	
	public String toURI() {
		
		final StringBuilder sb = new StringBuilder();
		
		sb.append("jdbc:mysql://")
		.append(host)
		.append(":")
		.append(port)
		.append("/")
		.append(DBName);
		
		return sb.toString();
		
		
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPass() {
		return pass;
	}

}
