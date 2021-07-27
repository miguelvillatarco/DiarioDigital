package usuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Messagebox;

import codigoBase.clsMailSender;

public class clsRegistrar {
	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsRegistrar.class.getName());
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    private clsConeccionBase conn = new clsConeccionBase();
    private String sql1="";
    private String sql2="";
    static clsMailSender sender = new clsMailSender(); 
	private clsUser selected;
    private String recordMode;
    
    public String getRecordMode() {
        return this.recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public clsUser getSelected() {
        return this.selected;
    }

    public void setSelected(clsUser selected) {

        this.selected = selected;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pUser") clsUser pUser,
            @ExecutionArgParam("recordMode") String recordMode) {
    	
    		setRecordMode(recordMode);
            this.selected = new clsUser(0,"","","","","",2,"",null,null,"");
            
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
	    	try {	 
				Messagebox.show("Guardando Datos");
			    sql1 = "INSERT INTO usuarios VALUES ( 0 , ? , ? , ? , ? , ? , ? , ? , NULL, NULL, ?)";		
			   	this.ps = this.conn.getConnection().prepareStatement(sql1);
			   	//parametros
			   	this.ps.setString(1, this.selected.getApellido());
				this.ps.setString(2, this.selected.getNombre());
				this.ps.setString(3, this.selected.getDireccion());
				this.ps.setString(4, this.selected.getCelular());
				this.ps.setString(5, this.selected.getEmail());
				this.ps.setInt(6, 2);
				this.ps.setString(7, this.selected.getPassword());
				this.ps.setDate(8, getCurrentDate());
				Messagebox.show("Su Suscripción fue Exitosa Ingrese su E-mail y Contraseña, GRACIAS!!!");
				//ejecucion
			   	ps.execute();	
			   	sendMail(this.selected.getEmail(), this.selected.getApellido() + "," + this.selected.getNombre());
			   	// update, debe ser en una clase aparte para ABMS
			   	sql2="UPDATE usuarios SET fUltimoAcceso = ? WHERE usuario = ?";
			   	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
			   	this.ps.setDate(1, getCurrentDate());			    
			   	this.ps.setString(2, this.selected.getEmail());			    	    
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
    }
	
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}	
	
	  private static void sendMail(String emailAccount, String usuario) throws AddressException, MessagingException
			{
				String subject="Suscripcion Exitosa";
			    String body="Hola " + usuario + "  Muchas Gracias por Confiar en Nosotros, Ahora podra Disfrutar de toda la información que brindamos!!!";    
			    sender.send(emailAccount, subject, body); // mando email
			}
}
