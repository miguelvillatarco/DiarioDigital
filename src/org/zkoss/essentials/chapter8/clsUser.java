package org.zkoss.essentials.chapter8;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Sessions;

import codigoBase.clsConnection;

import org.zkoss.bind.annotation.*;


public class clsUser {
	private String id="1"; //iniciar en 1
	private clsConnection conn = new clsConnection();
	private String UserName;
	private String LgnOut;
	private String Imagen;
	private String sql1;
	private PreparedStatement ps;
	private ResultSet rs;
	 
	@Init
	public void inicializar() throws SQLException {
		Sessions.getCurrent (). setAttribute ("base",conn);
		String idaux=(String) Sessions.getCurrent (). getAttribute ("usuario");
		if(idaux!=null) {
			id=idaux;
			
		}
		Sessions.getCurrent (). setAttribute ("usuario",id);
		if(id=="1"){
			UserName="Lector";
			LgnOut="Login";
		}
		else{ 
			sql1="SELECT nombre, imagen from usuarios where idusuario = ?";		
	 		this.ps = this.conn.getConnection().prepareStatement(sql1);
			     
			//parametros
		    this.ps.setString(1,id);
		    
		    // ejecucion
		    this.rs = ps.executeQuery();		
		    while (this.rs.next()){
		    	LgnOut="Logout";
		    	UserName=rs.getString(1);
		    	Imagen=rs.getString(2);
		    }	
		}
		//De esta forma los datos persisten luego de un href
		Sessions.getCurrent (). setAttribute ("username",UserName);
		Sessions.getCurrent (). setAttribute ("lgn",LgnOut);
		Sessions.getCurrent (). setAttribute ("img",Imagen);
	}
}