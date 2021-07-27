package actividadUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.zkoss.bind.annotation.Init;



public class clsActUsuarioVM {
	private clsActUsuario myData = new clsActUsuario();
    private clsActUsuario actUsuarioSelected;
    
    private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsActUsuarioVM.class.getName());
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    private clsConeccionBase conn = new clsConeccionBase();
    private String sql1="";
    private String sql2="";

    private final List<clsActUsuario> myList = new ArrayList<clsActUsuario>();
   
    
    
public clsActUsuario getactUsuarioSelected() {
    return this.actUsuarioSelected;
}

public void setClasifSelected(clsActUsuario clasifSelected) {
    this.actUsuarioSelected= clasifSelected;
}
    

@Init
public void init() {
    this.myData = new clsActUsuario();
}


public List<clsActUsuario> getAllActUsuario() {	    		
	 try {	    	 
	    	//sql1="SELECT idactusuario , U.usuario, N.genero, N.titulo, A.fecha FROM DiarioDigital.actusuario A inner join DiarioDigital.usuarios U  using (id) inner join DiarioDigital.noticias N using (idnoticias)";	
	    	sql1="SELECT idactusuario, usuario, genero, titulo, fecha FROM DiarioDigital.actusuario order by  idactusuario desc";
	 		this.ps = this.conn.getConnection().prepareStatement(sql1);
		    // ejecucion
		    this.rs = ps.executeQuery();		    
		    while (this.rs.next()){	
		    	myList.add(new clsActUsuario(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), new Date()));
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



}



