package pawZK10A;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class clsConnection{
	private static final long serialVersionUID = 5471706011875481102L;
	private Connection conn;
	private String database, userName, password;
	    
	
	public clsConnection(String database, String userName, String password){
		
		this.database=database;
		this.userName=userName;
		this.password=password;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		  } catch (ClassNotFoundException cnfe) {
		    System.out.println("Error. Couldn't find the driver!");
		    cnfe.printStackTrace();
		  }
		  
		  this.conn = null;
		  
		  try {
			String URL = "jdbc:mysql://localhost/"+ this.database;
	    	this.conn = DriverManager.getConnection(URL,this.userName,this.password);

		  } catch (SQLException se) {
			System.out.println("Error. Problems related to database, username or password");
		    se.printStackTrace();
		    
		  }
	}
	
	public Connection getConnection(){
		return this.conn;
	}
	
	
}

