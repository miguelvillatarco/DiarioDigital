package portada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Messagebox;

import codigoBase.clsMailSender;
import noticia.clsConeccionBase;
import noticia.clsNoticia;


public class clsRegistrar {
	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsRegistrar.class.getName());
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    private clsConeccionBase conn = new clsConeccionBase();
    private String sql1="";
    private String sql2="";
    static clsMailSender sender = new clsMailSender(); 
	private clsPortada selected;
    private String recordMode;
    
    public String getRecordMode() {
        return this.recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public clsPortada getSelected() {
        return this.selected;
    }
    

    public void setSelected(clsPortada selected) {

        this.selected = selected;
    }
    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pPortada") clsPortada pPortada,
            @ExecutionArgParam("recordMode") String recordMode) {
    	
    		setRecordMode(recordMode);
            this.selected = new clsPortada(0,1,"");
            
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
	    	try {	 
				Messagebox.show("empezando a guardar");
				//funciona INSERT INTO DiarioDigital.noticias VALUES ( 72 , 'asas' , 'sasa' , 'sasa' , 'sasa' , '?', '2016-08-10'  , '?','fsaf');
			    sql1 = "INSERT INTO DiarioDigital.portada VALUES ( 0 , ? , ? )";		
			   	this.ps = this.conn.getConnection().prepareStatement(sql1);
			   	//parametros
			 
					this.ps.setInt(1, this.selected.getIdportada());
					this.ps.setString(2,this.selected.getFecha());
				
				
		
				Messagebox.show(ps.toString());
				//ejecucion
			   	ps.execute();			    	    
			   	//this.ps.executeUpdate();   			       	
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
