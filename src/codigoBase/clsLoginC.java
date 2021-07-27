package codigoBase;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import java.sql.ResultSet;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import org.zkoss.zk.ui.Executions;


public class clsLoginC extends SelectorComposer<Window>{
	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsLoginC.class.getName());
	
	
	@Wire
	private Textbox usuario;
	@Wire
	private Textbox password;
	@Wire
	private Label mensaje;
		
	private PreparedStatement ps = null;
    private ResultSet rs = null;
    private clsConnection conn = new clsConnection();
    private String sql1="";
    private String sql2="";
    
    static clsMailSender sender = new clsMailSender(); 
	
	@Listen("onClick = button")
	public void submit() {
		boolean flag=false;
	    
    	 try {	    	 
	 		
 	    	sql1="SELECT Apellido, Nombres, idtipo FROM usuarios where usuario = ? and password = ?";		
	 		this.ps = this.conn.getConnection().prepareStatement(sql1);
			
			//parametros
	 		
		    this.ps.setString(1, usuario.getValue());
		    this.ps.setString(2, password.getValue());
		    // ejecucion
		    this.rs = ps.executeQuery();		    
		    
		    
		    while (this.rs.next()){	
		    	flag=true;
		    	sendMail(usuario.getValue(), rs.getString(1) + "," + rs.getString(2));
		    	
		    	
		    	Executions.getCurrent().sendRedirect("menu.zul?usuario=" + this.usuario.getValue() + "&apellido="+ rs.getString(1)+ "&tipoid="+ rs.getInt("idtipo"));	
		    	
		    	// update, debe ser en una clase aparte para ABMS
		    	sql2="UPDATE usuarios SET fUltimoAcceso = ? WHERE usuario = ?";
		    	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
			    
			    this.ps.setDate(1, getCurrentDate());			    
			    this.ps.setString(2, usuario.getValue());			    	    
			    this.ps.executeUpdate();   			    
			    //...........
		    }
		    
		    
		    if (!flag){			    
		    	this.mensaje.setValue("Error. Usuario o contraseña no coinciden. Reintente");		    	
		    	this.usuario.setValue("");
		    	this.password.setValue(""); 			    		    	
		    }    
		    
       	    
		    
	     } catch (Exception e){	    	
	    	 e.printStackTrace(); 
	    	 System.err.println("ERROR in SQL: " + e.getMessage() + " Cause " + e.getCause()); 	
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
	    
	}
	
	private static void sendMail(String emailAccount, String usuario) throws AddressException, MessagingException
	{
		String subject="PAW 2017";
	    String body="Hola " + usuario + "!";    
	    sender.send(emailAccount, subject, body); // mando email
	}
	
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}	
	
}