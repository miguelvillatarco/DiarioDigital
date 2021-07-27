package modeloNoticias;

import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;
import org.zkoss.bind.annotation.*;


import codigoBase.clsConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vistPrevia {
	
	private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql1="";
	private String numprev;
	private String titulo;
	private String imagen;
	private String seccion;
	private	String	copete;
	private clsConnection bd; //Recibe el enlace a la conexion principal de la base de datos

	
	@Init
	public void inicializar(@ExecutionParam("base") clsConnection base) throws SQLException {
				int cont=0;
				bd=(clsConnection) Sessions.getCurrent().getAttribute("base");
				imagen="Imagenes/";
				numprev= Executions.getCurrent (). getParameter ("p");
	 	    	sql1="SELECT titulo, imagen, genero, copete FROM DiarioDigital.noticias where idnoticias = ?";		
		 		this.ps = this.bd.getConnection().prepareStatement(sql1);
				     
				//parametros
			    this.ps.setString(1,numprev);
			    
			    // ejecucion
			    this.rs = ps.executeQuery();		
			    while (this.rs.next()){
			    	titulo=rs.getString(1);
			    	imagen+=rs.getString(2);
			    	seccion=" "+rs.getString(3);
			    	copete=" "+rs.getString(4);
			    	cont++;
			    }
			    //Messagebox.show("com"+cont);
	}
   
	public String gettitulo() {
		return titulo ;
	}
	
	public String getimagen() {
		return imagen ;
	}
	
	public String getseccion() {
		return seccion;
	}
	public String getcopete() {
		return copete;
	}
}