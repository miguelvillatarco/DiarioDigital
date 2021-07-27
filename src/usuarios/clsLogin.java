package usuarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;


public class clsLogin extends SelectorComposer<Component>{
	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsLogin.class.getName());
	//wire components
	@Wire
	Textbox userName;
	@Wire
	Textbox password;
	
	@Wire
	private Label mensaje;
		
	private PreparedStatement ps = null;
    private ResultSet rs = null;
    private clsConeccionBase conn = new clsConeccionBase();
    private String sql1="";
    private String sql2="";
    

	public clsLogin() {
	    
	}
		
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);	
		refresh();
	}
	
	
	@Listen("onClick=#btnOK")
		public void processingOK(){
		boolean flag=false;    
	   	 try {	    	 
		    	sql1="SELECT Apellido, Nombres, idtipo FROM usuarios where usuario = ? and password = ?";		
		 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				//parametros
			    this.ps.setString(1, userName.getValue());
			    this.ps.setString(2, password.getValue());
			    // ejecucion
			    this.rs = ps.executeQuery();		    
			    while (this.rs.next()){	
			    	flag=true;
			    	Executions.getCurrent().sendRedirect("Mostrar.zul?usuario=" + this.userName.getValue() + "&apellido="+ rs.getString(1));	
			    	// update, debe ser en una clase aparte para ABMS
			    	sql2="UPDATE usuarios SET fUltimoAcceso = ? WHERE usuario = ?";
			    	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
				    this.ps.setDate(1, getCurrentDate());			    
				    this.ps.setString(2, userName.getValue());			    	    
				    this.ps.executeUpdate();   			    
				    //...........
			    }
			    if (!flag){			    
			    	this.mensaje.setValue("Error. Usuario o contraseña no coinciden. Reintente");		    	
			    	this.userName.setValue("");
			    	this.password.setValue(""); 			    		    	
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
	
	
	@Listen("onClick=#btnCancelar")
	public void processingCancel(){
		refresh();
	}

	private void refresh() {	
		userName.setValue(" ");
		password.setValue(" ");
	}
	
		
}