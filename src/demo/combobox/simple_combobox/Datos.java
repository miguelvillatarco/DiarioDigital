package demo.combobox.simple_combobox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import codigoBase.clsConnection;

public class Datos {

	private List<String> colors = new ArrayList<String>();
	
	
    private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(Datos.class.getName());
	
	private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static clsConnection conn = new clsConnection();
    private static String sql1="";
       
    
	public void Datoos() {
		
	}
	
	public List<String>  getColor() {
			try {	 
						conn = new clsConnection();
				    	sql1="SELECT id, descripcion FROM tipoUsuario";		
				 		ps = conn.getConnection().prepareStatement(sql1);
						
				
					    // ejecucion
				 		rs = ps.executeQuery();		    
					    
					    
					    while (rs.next()){	
					    	colors.add(rs.getString("descripcion"));
					    }      	    
					    return colors;
				     } catch (Exception e){	    	
				    	 e.printStackTrace(); 
				    	 System.err.println("ERROR in SQL: " + e.getMessage() + " Cause " + e.getCause()); 	
				    	 log.severe("ERROR in SQL: " + e.toString());
				     }finally { //cleanup
				          if (ps!= null) {
				             try {
				            	 ps.close();
				             } catch (SQLException ex) {
				                  log.severe(ex.toString()); //log and ignore
				             }
				          }
				          if (conn!= null) {
				             try {
				            	 conn.getConnection().close();
				                  System.out.println("Database connection terminated");
				             } catch (SQLException ex) {
				                  log.severe(ex.toString()); //log and ignore
				            }
				          }
				     }  
			return colors;
				    
	}
	
	public String getDatoActual(int tipo) {
						conn = new clsConnection();
						String valorA="xxx";
						try {	    	 
					    	sql1="SELECT id, descripcion FROM tipoUsuario WHERE id = ?";		
					 		ps = conn.getConnection().prepareStatement(sql1);
							ps.setInt(1, tipo);
					
						    // ejecucion
					 		rs = ps.executeQuery();		    
						    
						    while (rs.next()){	
						    	valorA = rs.getString("descripcion");
						    }      	    
						    
					     } catch (Exception e){	    	
					    	 e.printStackTrace(); 
					    	 System.err.println("ERROR in SQL: " + e.getMessage() + " Cause " + e.getCause()); 	
					    	 log.severe("ERROR in SQL: " + e.toString());
					     }finally { //cleanup
					          if (ps!= null) {
					             try {
					            	 ps.close();
					             } catch (SQLException ex) {
					                  log.severe(ex.toString()); //log and ignore
					             }
					          }
					          if (conn!= null) {
					             try {
					            	 conn.getConnection().close();
					                  System.out.println("Database connection terminated");
					             } catch (SQLException ex) {
					                  log.severe(ex.toString()); //log and ignore
					            }
					          }
					     } 
		return valorA;
						
	}
	
	
}
