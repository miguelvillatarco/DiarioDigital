package portada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import codigoBase.clsMailSender;
import noticia.clsNoticia;



public class clsPortadaVM2 {

	

	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsPortadaVM2.class.getName());
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    private clsConnection conn = new clsConnection();
    private String sql1="";
    private String sql2="";
    
    private final List<clsPortada> myList = new ArrayList<clsPortada>();
    
    static clsMailSender sender = new clsMailSender(); 
    
	    private clsPortada portadaSelected;
	    
	    
	    public clsPortada getPortadaSelected() {
	        return this.portadaSelected;
	    }
	    
	    public void setPortadaSelected(clsPortada portadaSelected) {
	        this.portadaSelected = portadaSelected;
	    }
	        

	    @Init
	    public void init() {
	    }
	    
	    
	    public List<clsPortada> getAllPortada() {	    		
	    	 try {	    	 
		 	    	sql1="select idportada,idnoticias,fecha  FROM DiarioDigital.portada";	
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();		    
				    while (this.rs.next()){	
				    	myList.add(new clsPortada(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),rs.getString(3)));
				    }
				    return myList;
			    
		      }catch (Exception e){	    	
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
			             } catch (SQLException ex) {
			                  log.severe(ex.toString()); //log and ignore
			            }
		          }
		     }
	    	return null; 
	   }
	    
	    @NotifyChange("allPortada")
		   @Command
		   public void edit() {
				final HashMap<String, Object> map = new HashMap<String, Object>();
		    	 map.put("pPortada", this.portadaSelected);
		         map.put("recordMode", "EDIT");
		         Executions.createComponents("portadaCRUD.zul", null, map);
		    }

		    // The following method will be called from CustomerCRUDVM after the save
		    // When we say Notifychange("allcustomers), then ZUL list items will be
		    // updated

	    
	    
	    @GlobalCommand
	    @NotifyChange("allPortada")
	    public void updatePortadaList(@BindingParam("pPortada") clsPortada portada,
	            @BindingParam("recordMode") String recordMode,
	            @BindingParam("tipoP") String tipo) {
	    	
	    	if (recordMode.equals("NEW")) {
	    		try {	 
	    	
	    			conn = new clsConnection();
			    	sql1="select idportada,idnoticias,fecha from DiarioDigital.portada  WHERE fecha = ?";		
			 		ps = conn.getConnection().prepareStatement(sql1);
			 		if(!tipo.equals("")) {
			 			this.ps.setString(1, tipo);	
			 		}else {
			 			this.ps.setString(1, "portada");
			 		}
			
				    // ejecucion
			 		rs = ps.executeQuery();	
			 		while (rs.next()){	
						
				    	portada.setIdnoticia(rs.getInt("id"));
				    } 
				    sql1 = "INSERT INTO `DiarioDigital`.`portada` VALUES ( 0, ?, ?)";		
				   	this.ps = this.conn.getConnection().prepareStatement(sql1);
				   	//parametros
				   	this.ps.setInt(1, portada.getIdportada());
					this.ps.setString(2, portada.getFecha());	
			
					//ejecucion
				   	ps.execute();	
				   	Messagebox.show("empezandoooooo a guardar");	       	
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
			   	            	 this.conn.CerrarConeccion();
			   	             } catch (SQLException ex) {
			   	                  log.severe(ex.toString()); //log and ignore
			   	            }
			   	          }
			   	     }
	    	}else {
	    		try {	
	    			conn = new clsConnection();
	    			sql1="SELECT id, descripcion FROM tipoUsuario WHERE descripcion = ?";			
			 		ps = conn.getConnection().prepareStatement(sql1);
			 		this.ps.setString(1, tipo);
				    // ejecucion
			 		rs = ps.executeQuery();		    
				    while (rs.next()){	
				    	portada.setIdnoticia(rs.getInt("id"));
				    }     	    
		    		conn = new clsConnection();
	    			sql2="UPDATE `DiarioDigital`.`portada` SET `idnoticias`='?', `fecha`='?' WHERE `idnoticias`='?'";
	    					
			    	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
				  
				    this.ps.setInt(1, portada.getIdnoticia());
				    this.ps.setString(2, portada.getFecha());
				   
				  
				    this.ps.execute(); 
					Messagebox.show("cambiandodoooooo a datos");
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
		   	            	 this.conn.CerrarConeccion();
		   	             } catch (SQLException ex) {
		   	                  log.severe(ex.toString()); //log and ignore
		   	            }
		   	          }
		   	     }
    		}
	    }
	    
	    @NotifyChange("allPortada")
	    @Command
	    public void delete() {
	    	conn = new clsConnection();
	    	 try {	 
	    	    	//sql1 = "DELETE FROM DiarioDigital.noticias WHERE titulo = ?";
	    	    	sql1 = "DELETE FROM `DiarioDigital`.`portada` WHERE `idnoticias` = ?";
			    	this.ps = this.conn.getConnection().prepareStatement(sql1);
			    	this.ps.setInt(1, this.portadaSelected.getIdnoticia());
		   	 		ps.execute();
		   	 	Messagebox.show("empezandoooooo a eliminarrr");	
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
	   	            	 this.conn.CerrarConeccion();
	   	             } catch (SQLException ex) {
	   	                  log.severe(ex.toString()); //log and ignore
	   	            }
	   	          }
	   	     }  
	    }
}
