package actividadUsuario;

import java.util.Date;

import org.zkoss.bind.annotation.Init;

@Init
public class clsActUsuario {
	private int id;
	private String usuario;
	private String genero;
	private String titulo;
	private Date fecha;
	
	
	public clsActUsuario(int id,  String usuario,  String genero,  String titulo, Date fecha) {
		
		this.id=id;
		this.usuario=usuario;
		this.genero=genero;
		this.titulo=titulo;
		this.fecha=fecha;
	}
	public clsActUsuario () {}
	
	public int getId() {
		return this.id; }

	public void setId(int id) {
		this.id = id; 
		}
	
	public Date getFecha() {
		return this.fecha;
	}
	public void setFecha(Date  fecha) {
		this.fecha=fecha; 
	}
	 
	public String getUsuario() {
		return this.usuario; 	}
	
	public String getGenero() {
		return this.genero; 	}
	
	public String getTitulo() {
		return this.titulo; 	}
}
