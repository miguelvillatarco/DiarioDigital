package modeloNoticias;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

import codigoBase.clsConnection;
import usuarios.clsUser;
import org.zkoss.essentials.chapter8.*;
import org.zkoss.essentials.entity.User;
import org.zkoss.essentials.services.UserCredential;

import java.sql.Date;


import org.zkoss.essentials.chapter5.AuthenticationServiceChapter5Impl;
import org.zkoss.essentials.services.AuthenticationService;


public class presNoticias {

	private PreparedStatement ps = null;
	private PreparedStatement ps1 = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private clsConnection conn = new clsConnection();
    private String sql1="";
	private String numprev;
	private int id;
	private String titulo;
	private String imagen;
	private String epigrafe;
	private String seccion;
	private String copete;
	private String cuerpo;
	private String fecha;
	private String video;
	
    private String sql2="";
    private String sql3="";
   private  clsUser user= new clsUser(); 
  
   @Wire
	Textbox account;
	
	@Init
	public void inicializar() throws SQLException {
		
		        Sessions.getCurrent (). setAttribute ("base",conn); 
		        clsUser user= new clsUser(); 
				imagen="../Imagenes/";
				numprev= Executions.getCurrent (). getParameter("p");
	 	    	sql1="select titulo, imagen, epigrafe, genero, fecha, copete, cuerpo, video from DiarioDigital.noticias where idnoticias = ?";		
		 		this.ps = this.conn.getConnection().prepareStatement(sql1);
		 	   
		 		
		 		long  millis = System.currentTimeMillis ();  
		 	    java.sql.Date date = new  java.sql.Date (millis);   
				//parametros
			    this.ps.setString(1,numprev);
			    
			    // ejecucion
			    this.rs = ps.executeQuery();		
			    while (this.rs.next()){
			    	titulo=rs.getString(1);
			    	imagen+=rs.getString(2);
			    	epigrafe=" "+rs.getString(3);
			    	seccion=" "+rs.getString(4);
			    	fecha=rs.getString(5);
			    	copete=rs.getString(6);
			    	cuerpo=rs.getString(7);
			    	video+=rs.getString(8);
			    	
			    	 
			    }
		
	           sql1 =" INSERT INTO `DiarioDigital`.`actusuario` (`idactusuario`, `usuario`, `genero`, `titulo` , `fecha`) VALUES ( 0 , ? , ? , ? , ?)";
	  		   this.ps = this.conn.getConnection().prepareStatement(sql1);
	  		
	  		//services
	  		AuthenticationService authService = new AuthenticationServiceChapter8Impl();
	  
	  		UserCredential cre= authService.getUserCredential();
	  		
			 this.ps.setString(1, cre.getAccount());
	  		  this.ps.setString(2, getSeccion());
	  		  this.ps.setString(3, getTitulo());
	  		  this.ps.setDate(4, date);
	  		//ejecucion
	  		   ps.execute();
	}
	public int getId() {
		return this.id;
	}
	public String getTitulo() {
		return this.titulo ;
	}
	void setTitulo(String titulo) {
		this.titulo=titulo;
	}
	
	
	public String getImagen() {
		return imagen ;
	}
	void setImagen(String imagen) {
		this.imagen=imagen;
	}
	
	public String getEpigrafe() {
		return epigrafe ;
	}
	void setEpigrafe(String epigrafe) {
		this.epigrafe=epigrafe;
	}
	
	public String getSeccion() {
		return seccion;
	}
	void setSeccion(String seccion) {
		this.seccion=seccion;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	void setFecha(String fecha) {
		this.fecha=fecha;
	}
	
	public String getcopete() {
		return copete;
	}
	void setCopete(String Copete) {
		this.copete=copete;
	}
	
	public String getCuerpo() {
		return cuerpo;
	}
	void setCuerpo(String cuerpo) {
		this.cuerpo=cuerpo;
	}
	
	public String getVideo() {
		return video;
	}
	void setVideo(String video) {
		this.video=video;
	}
}