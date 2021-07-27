package actividadUsuario;

import java.util.ArrayList;
import java.util.List;

import modeloClasificados.clsClasificados;



public class clsActUsuarioList {
private final List<clsActUsuario> myList = new ArrayList<clsActUsuario>();
	

	public clsActUsuarioList (){
		myList.add(new clsActUsuario());
	

	}
	
	
	public List<clsActUsuario> getAllActUsu() {
		return this.myList;
	}
	
	public void addActUsu(clsActUsuario clasificados) {
		this.myList.add(clasificados);
	}
	
	public void editActUsu(clsActUsuario clasificados) {
		int pos;
		for(int i=0; i < this.myList.size() ;i ++) {
			if(this.myList.equals(clasificados)) {
				
			}
		}
		
	}
	public void deleteActUsu(clsActUsuario clasificados) {
		this.myList.remove(clasificados);
	}
	

	
	
}

