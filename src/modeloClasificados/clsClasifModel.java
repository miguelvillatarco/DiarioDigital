package modeloClasificados;

import java.util.List;

import modeloClasificados.clsClasificados;
import modeloClasificados.clsClasifList;

public class clsClasifModel {

private clsClasifList myData = new clsClasifList();
	
	
	
	public List<clsClasificados> getAllClasificados() {
		return myData.getAllClasif();
	}
}
