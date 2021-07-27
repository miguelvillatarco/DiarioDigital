package usuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import codigoBase.clsMailSender;


public class clsUserVM2 {
	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsUserVM2.class.getName());
	


	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    private clsConeccionBase conn = new clsConeccionBase();
    private String sql1="";
    private String sql2="";
    
    private final List<clsUser> myList = new ArrayList<clsUser>();
    
    static clsMailSender sender = new clsMailSender(); 
    
	    private clsUser userSelected;
	    
	    
	    public clsUser getUserSelected() {
	        return this.userSelected;
	    }
	    
	    public void setUserSelected(clsUser userSelected) {
	        this.userSelected = userSelected;
	    }
	        

	    @Init
	    public void init() {
	    }

	    public List<clsUser> getAllUser() {	    		
	    	 try {	    	 
		 	    	sql1="SELECT id, Apellido, Nombres, direccion, celular, usuario, idtipo, password FROM usuarios";		
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();		    
				    while (this.rs.next()){	
				    	myList.add( new clsUser(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),Integer.parseInt(rs.getString(7)), rs.getString(8) ,new Date(),new Date(), ""));
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

	   @NotifyChange("allUser")
	   @Command
	   public void edit() {
			final HashMap<String, Object> map = new HashMap<String, Object>();
	    	 map.put("pUser", this.userSelected);
	         map.put("recordMode", "EDIT");
	         Executions.createComponents("userCRUD.zul", null, map);
	    }

	    // The following method will be called from CustomerCRUDVM after the save
	    // When we say Notifychange("allcustomers), then ZUL list items will be
	    // updated
	    @GlobalCommand
	    @NotifyChange("allUser")
	    public void updateUserList(@BindingParam("pUser") clsUser user,
	            @BindingParam("recordMode") String recordMode,
	            @BindingParam("tipoU") String tipo) {
	    	
	    	if (recordMode.equals("NEW")) {
	    		try {	 
	    	
	    			conn = new clsConeccionBase();
			    	sql1="SELECT id, descripcion FROM tipoUsuario WHERE descripcion = ?";		
			 		ps = conn.getConnection().prepareStatement(sql1);
			 		if(!tipo.equals("")) {
			 			this.ps.setString(1, tipo);	
			 		}else {
			 			this.ps.setString(1, "Cliente");
			 		}
			
				    // ejecucion
			 		rs = ps.executeQuery();		    
				    while (rs.next()){	
			
				    	user.setTipo(rs.getInt("id"));
				    }      	    
			
				    sql1 = "INSERT INTO usuarios VALUES ( 0 , ? , ? , ? , ? , ? , ? , ? , NULL, NULL, ?)";		
				   	this.ps = this.conn.getConnection().prepareStatement(sql1);
				   	//parametros
				   	this.ps.setString(1, user.getApellido());
					this.ps.setString(2, user.getNombre());
					this.ps.setString(3, user.getDireccion());
					this.ps.setString(4, user.getCelular());
					this.ps.setString(5, user.getEmail());
					this.ps.setInt(6, user.getTipo());
					this.ps.setString(7, user.getPassword());
					this.ps.setDate(8, getCurrentDate());
			
					//ejecucion
				   	ps.execute();	
				   	Messagebox.show("Empezando a guardar la Usuario...");
					Messagebox.show("Los Datos del Usuario se guardaron Correctamente!!!");
				   	sendMail(user.getEmail(), user.getApellido() + "," + user.getNombre());
				   	// update, debe ser en una clase aparte para ABMS
				   	sql2="UPDATE usuarios SET fUltimoAcceso = ? WHERE usuario = ?";
				   	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
				   	this.ps.setDate(1, getCurrentDate());			    
				   	this.ps.setString(2, user.getEmail());			    	    
				   	this.ps.executeUpdate();   			       	
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
	    			conn = new clsConeccionBase();
			    	sql1="SELECT id, descripcion FROM tipoUsuario WHERE descripcion = ?";		
			 		ps = conn.getConnection().prepareStatement(sql1);
			 		this.ps.setString(1, tipo);
				    // ejecucion
			 		rs = ps.executeQuery();		    
				    while (rs.next()){	
				    	user.setTipo(rs.getInt("id"));
				    }      	    
		    		conn = new clsConeccionBase();
	    			sql2="UPDATE usuarios SET "
	    					+ "Apellido = ? ,"
	    					+ "Nombres = ? ,"
	    					+ "direccion = ? ,"
	    					+ "celular = ? ,"
	    					+ "usuario = ? ,"
	    					+ "idtipo = ? ,"
	    					+ "password = ? "
	    					+ "WHERE usuario = ? ";
			    	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
				    this.ps.setString(1, user.getApellido());
				    this.ps.setString(2, user.getNombre());
				    this.ps.setString(3, user.getDireccion());
				    this.ps.setString(4, user.getCelular());
				    this.ps.setString(5, user.getEmail());
				    this.ps.setInt(6, user.getTipo());
				    this.ps.setString(7, user.getPassword());
				    this.ps.setString(8, user.getEmail());
				    this.ps.execute(); 
					Messagebox.show("Empezando a actualizar datos del Usuario...");
					Messagebox.show("Los Datos del Usuario se guardaron Correctamente!!!");
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
	      
	    
   
	    @NotifyChange("allUser")
	    @Command
	    public void delete() {
	    	conn = new clsConeccionBase();
	    	 try {	 
	    	    	sql1 = "DELETE FROM usuarios WHERE usuario = ?";	
			    	this.ps = this.conn.getConnection().prepareStatement(sql1);
			    	this.ps.setString(1, this.userSelected.getEmail());
		   	 		ps.execute();
		   	 	Messagebox.show("Empezando a eliminar datos del Usuario...");
				Messagebox.show("Usuario eliminado Correctamente!!!");
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
	    
	    private static void sendMail(String emailAccount, String usuario) throws AddressException, MessagingException
		{
			String subject="PAW 2018";
		    String body="Hola " + usuario + "tu suscripcion fue correcta ahora podras disfrutar de todas las noticias!";    
		    sender.send(emailAccount, subject, body); // mando email
		}
		
		private static java.sql.Date getCurrentDate() {
		    java.util.Date today = new java.util.Date();
		    return new java.sql.Date(today.getTime());
		}	
}
