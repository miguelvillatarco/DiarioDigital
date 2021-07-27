package modeloClasificados;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class clsClasifList {
	
	private final List<clsClasificados> myList = new ArrayList<clsClasificados>();
	

	public clsClasifList (){
		myList.add(new clsClasificados(28,"asdasdas", "asdsasa","asdasdas", "asdasdsa"));
		myList.add(new clsClasificados(29,"asffsdasdas", "asfsfdsasa","asdasfdas", "asdfasdsa"));
		myList.add(new clsClasificados(30,"asdasdahhs", "asdsahsa","asdashdas", "asdahsdsa"));
		
	}
	
	public List<clsClasificados> getAllClasif() {
		return this.myList;
	}	
	
	
	public void addClasificados(clsClasificados clasificados) {
		this.myList.add(clasificados);
	}
	
	public void editClasificados(clsClasificados clasificados) {
		int pos;
		for(int i=0; i < this.myList.size() ;i ++) {
			if(this.myList.equals(clasificados)) {
				
			}
		}
		
	}
	
	public void deleteClasificados(clsClasificados clasificados) {
		this.myList.remove(clasificados);
	}
}






