package noticia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class clsConeccionBase {
	
	private Connection conn = null;
	private static Logger log = Logger.getLogger(clsConeccionBase.class.getName());
	private String user="miguel";
	private String password="36486098";
	private String bdName="DiarioDigital";
	
	public clsConeccionBase(){
	try {
        //load driver and get a database connection
	   	 String URL = "jdbc:mysql://localhost/"+ this.bdName;
	   	 Class.forName("com.mysql.jdbc.Driver");
	   	 conn=DriverManager.getConnection(URL,this.user,this.password);
	} catch(Exception e){		    	
    	e.printStackTrace(); 
    	System.err.println("ERROR in Connection: " + e.getMessage() + " Cause " + e.getCause()); 	
         this.log.severe("ERROR in Connection: " + e.toString());   
    	}
	}

	public Connection getConnection() {
		return this.conn;
	}
	
	public void CerrarConeccion() throws SQLException {
		this.conn.close();
	}
	

}
