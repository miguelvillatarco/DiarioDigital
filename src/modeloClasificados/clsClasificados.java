package modeloClasificados;

public class clsClasificados {
	private int id;
	private String tipoClasificado,descripcion;
	private String fecha;
	private String icon;
 

	public clsClasificados(int id, String tipo,String desc,String fecha,String icon) {
		this.id=id;
		this.tipoClasificado=tipo;
		this.descripcion=desc;
		this.fecha=fecha;
		this.icon=icon;
		
	}
	public clsClasificados() {
		
	}
	
	public int getId() {
		return this.id; }

	public void setId(int id) {
		this.id = id; 
		}
	public String getTipoClasificado() {
		return this.tipoClasificado;
	}
	public void setTipoClasificado(String tipo) {
		this.tipoClasificado=tipo; 
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String desc) {
		this.descripcion=desc; 
	}
	
	public String getFecha() {
		return this.fecha;
	}
	public void setFecha(String  fecha) {
		this.fecha=fecha; 
	}
	public String getIcon() {
		return this.icon; 	}

	public void setIcon(String icon) {
		this.icon = icon; 	}

}
