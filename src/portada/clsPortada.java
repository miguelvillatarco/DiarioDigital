package portada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import codigoBase.clsConnection;
import modeloNoticias.vistPrevia;


public class clsPortada {
	private int idportada;
	private int idnoticia;
	private String fecha;
	
	private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql1="";
	private String pagina;
	
	private clsConnection bd; //Recibe el enlace a la conexion principal de la base de datos

	
	
	public clsPortada(int idportada, int idnoticias, String Fecha) {
		this.idportada=idportada;
		this.idnoticia=idnoticias;
		this.fecha=Fecha;
	}
	@Init
	public void inicializar(@ExecutionParam("base") clsConnection base) throws SQLException {
				
				bd=(clsConnection) Sessions.getCurrent().getAttribute("base");
				pagina= Executions.getCurrent().getParameter("port");
	 	    	sql1="SELECT idportada, idnoticia, fecha FROM DiarioDigital.portada where idnoticias = ?";		
		 		this.ps = this.bd.getConnection().prepareStatement(sql1);
				     
				//parametros
			    this.ps.setString(1,pagina);
			    
			    // ejecucion
			    this.rs = ps.executeQuery();		
			    while (this.rs.next()){
			    	
			    	 idportada = rs.getInt(1);
			    	 idnoticia = rs.getInt(2);
			    	 fecha =rs.getString(3);
			    	 
			    }
			    //Messagebox.show("emepzando a guardar");
	}
	
	public int getIdportada() {
		return this.idportada;
	}
	public void setIdportada(int idportada) {
		this.idportada=idportada;
	}
	public	int	getIdnoticia(){
		return	this.idnoticia;
	}
	public void	setIdnoticia(int idnoticia) {
		this.idnoticia=idnoticia ;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	public void setFecha(String fecha) {
		this.fecha=fecha;
	}
	
	
	

}
