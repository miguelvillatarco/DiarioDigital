package usuarios;

import java.text.SimpleDateFormat;
import java.util.Date;


public class clsUser {
	
	private final Date today = new Date();
	
	private int id,tipo;
	private String apellido, nombre, direccion, email, password,celular;
	private Date createDate, deleteDate;
	private String icon;

	public clsUser() {
		
	}
	
	public clsUser(int id, String apellido, String nombre, String direccion, String celular, String email, int tipo, String password  ,Date createDate, Date deleteDate, String icon) {
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.email = email;
		this.direccion = direccion;
		this.password = password;
		this.celular = celular;
		this.tipo = tipo;
		this.createDate = createDate;
		this.deleteDate = deleteDate;
		this.icon=icon;
	}

	public int getId() {
		return this.id; 
		}

	public void setId(int id) {
		this.id = id; 
		}

	public String getApellido() {
		return this.apellido;
		}

	public void setApellido(String apellido) {
		this.apellido = apellido; 	
		}

	public String getNombre() {
		return this.nombre;
		}

	public void setNombre(String nombre) {
		this.nombre = nombre; 	
		}
	
	public String getDireccion() {
		return this.direccion;
		}

	public void setDireccion(String direccion) {
		this.direccion = direccion; 	
		}

	public String getEmail() {
		return this.email;
		}

	public void setEmail(String email) {
		this.email = email; 	
		}
	
	public String getCelular() {
		return this.celular;
		}

	public void setCelular(String celular) {
		this.celular = celular; 	
		}

	public Date getCreateDate() {
		return this.createDate; 	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate; 	}

	public Date getDeleteDate() {
		return this.deleteDate; 	}

	public void setDeleDate(Date deleteDate) {
		this.deleteDate = deleteDate; 	}
	
	public String getIcon() {
		return this.icon; 	}

	public void setIcon(String icon) {
		this.icon = icon; 	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public int getTipo() {
		return this.tipo;
	}

}
