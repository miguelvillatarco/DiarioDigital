package org.zkoss.essentials.chapter8;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.zkoss.essentials.entity.User;

import codigoBase.clsConnection;


public class clsVerificarDatos {
	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsVerificarDatos.class.getName());
	private PreparedStatement ps = null;
    private ResultSet rs = null;
    private clsConnection conn;
    private String sql1="";
    private String sql2="";
	
    public clsVerificarDatos() {
    	conn = new clsConnection();
    }
		public User getUsuario(String u,String p) {
			User usuario = null;
		   	 try {	  
		   		 
		   		 
			 		
			    	sql1="SELECT Apellido, Nombres, idtipo, password, usuario FROM usuarios where usuario = ? and password = ?";		
			    	
			    	this.ps = this.conn.getConnection().prepareStatement(sql1);
			    	
					//parametros
			 		
				    this.ps.setString(1, u);
				    this.ps.setString(2, p);
				    
				    // ejecucion
				    this.rs = ps.executeQuery();		    
				    
				    
				    while (this.rs.next()){	
				    	
				    	usuario = new User(this.rs.getString("usuario"),
				    			this.rs.getString("password"),
				    			this.rs.getString("Apellido"),
				    			this.rs.getString("usuario"));
				    	
				    	// update, debe ser en una clase aparte para ABMS
				    	sql2="UPDATE usuarios SET fUltimoAcceso = ? WHERE usuario = ?";
				    	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
					    
					    this.ps.setDate(1, getCurrentDate());			    
					    this.ps.setString(2, u);			    	    
					    this.ps.executeUpdate();   			    
					    //...........
				    }
				    
			     } catch (Exception e){	    	
			    	 e.printStackTrace(); 
			    	 this.log.severe("ERROR in SQL: " + e.toString());
			     }finally { //cleanup
			          if (this.ps!= null) {
			             try {
			            	 this.ps.close();
			             } catch (SQLException ex) {
			                  this.log.severe(ex.toString()); //log and ignore
			             }
			          }
			          if (this.conn!= null) {
			             try {
			            	 this.conn.getConnection().close();
			                  System.out.println("Database connection terminated");
			             } catch (SQLException ex) {
			                  log.severe(ex.toString()); //log and ignore
			            }
			          }
			     }
		   	 return usuario;
	}
		
		private static java.sql.Date getCurrentDate() {
		    java.util.Date today = new java.util.Date();
		    return new java.sql.Date(today.getTime());
		}	
		
		
}
