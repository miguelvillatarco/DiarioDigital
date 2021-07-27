package noticia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.Date;
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
	private clsNoticia selected;
    private String recordMode;
    
    public String getRecordMode() {
        return this.recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public clsNoticia getSelected() {
        return this.selected;
    }

    public void setSelected(clsNoticia selected) {

        this.selected = selected;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pNoticia") clsNoticia pNoticia,
            @ExecutionArgParam("recordMode") String recordMode) {
    	
    		setRecordMode(recordMode);
            this.selected = new clsNoticia(0,"","","","","",null,"");
            
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
	    	try {	 
				Messagebox.show("empezando a guardar");
				//funciona INSERT INTO DiarioDigital.noticias VALUES ( 72 , 'asas' , 'sasa' , 'sasa' , 'sasa' , '?', '2016-08-10'  , '?','fsaf');
			    sql1 = "INSERT INTO DiarioDigital.noticias VALUES ( 0 , ? , ? , ? , ? , ? , ? , ? , NULL)";		
			   	this.ps = this.conn.getConnection().prepareStatement(sql1);
			   	//parametros
			   		this.ps.setString(1, this.selected.getTitulo());
					this.ps.setString(2, this.selected.getCopete());
					this.ps.setString(3, this.selected.getCuerpo());
					this.ps.setString(4, this.selected.getGenero());
					this.ps.setString(5, this.selected.getImagen());
				
					this.ps.setDate(6, getCurrentDate());
					this.ps.setString(7, this.selected.getEpigrafe());
					//this.ps.setString(8, this.selected.getvideo());
		
				Messagebox.show(ps.toString());
				//ejecucion
			   	ps.execute();			    	    
			   	//this.ps.executeUpdate();  
			   	Messagebox.show("NOTICIA GUARDADA!!!!");
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

	private java.sql.Date getCurrentDate() {
		// TODO Auto-generated method stub
		return null;
	}
	/*  */
	
}





