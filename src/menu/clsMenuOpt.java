package menu;

public class clsMenuOpt {
	private int id;
	private int padre;
	private String nombre;
	private String etiqueta; // nombre en menu
	private String icono;  // url
	private String pagina; // zul
	private int habilitado; // 0 si, 1 no
	
	
	public clsMenuOpt(int id, int padre, String nombre, String etiqueta, String icono, String pagina, int habilitado){
		this.id=id;
		this.padre=padre;
		this.nombre=nombre;
		this.etiqueta=etiqueta;
		this.icono=icono;
		this.pagina=pagina;
		this.habilitado=habilitado;
	}
	
	public int getId(){return this.id;}
	public int getPadre(){return this.padre;}
	public String getNombre(){return this.nombre;}
	public String getEtiqueta(){return this.etiqueta;}
	public String getIcono(){return this.icono;}
	public String getPagina(){return this.pagina;}
	public int getHabilitado(){return this.habilitado;}
}