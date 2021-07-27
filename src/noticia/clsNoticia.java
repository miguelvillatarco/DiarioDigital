package noticia;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;

@Init
public class clsNoticia {
	
	private	int	idnoticia;
	private String titulo;
	private String copete;
	private String cuerpo;
	private String genero;
	private String imagen;
	private Date fecha;
	private String epigrafe;
	private String video;
	private boolean editingStatus;
	
	
	
	public clsNoticia() {
		
	}
	public clsNoticia(int idnoticia,String titulo, String copete, String cuerpo, String genero, String imagen, Date fecha, String epigrafe) {
	
		this.idnoticia=idnoticia;
		this.titulo = titulo;
		this.imagen = imagen;
		this.epigrafe = epigrafe;
		this.genero = genero;
		this.copete = copete;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
	}
	
	public	int	getIdnoticia(){
		return	idnoticia;
	}
	public void	setIdnoticia(int idnoticia) {
		this.idnoticia=idnoticia ;
	}
	
	public String getTitulo() {
		return titulo ;
	}
	
	public void	setTitulo(String titulo) {
		this.titulo=titulo ;
	}

	public String getImagen() {
		return imagen ;
	}
	public void	setImagen(String imagen) {
		this.imagen=imagen ;
	}
	
	public String getEpigrafe() {
		return epigrafe ;
	}
	
	public void	setEpigrafe(String epigrafe) {
		this.epigrafe=epigrafe ;
	}
	
	public String getGenero() {
		return genero;
	}
	public void	setGenero(String genero) {
		this.genero=genero ;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void	setFecha(Date fecha) {
		this.fecha=fecha ;
	}
	public String getCopete() {
		return copete;
	}
	
	public void	setCopete(String copete) {
		this.copete=copete ;
	}
	
	public String getCuerpo() {
		return cuerpo;
	}
	
	public void	setCuerpo(String cuerpo) {
		this.cuerpo=cuerpo ;
	}
	public String getvideo() {
		return video;
	}
	public boolean getEditingStatus() {
	    return this.editingStatus;
	}

	public void setEditingStatus(boolean editingStatus) {
		this.editingStatus = editingStatus;
	}
	


	
}
