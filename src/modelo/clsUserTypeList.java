package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class clsUserTypeList {
	
	private final Date today = new Date();
	private final List<clsUserType> myList = new ArrayList<clsUserType>();
	

	public clsUserTypeList(){
		myList.add(new clsUserType(1,"Cliente", "Este usuario puede reservar cancha, anotarse a torneos y escuelas", today, null, "/img/delete48.png"));
		myList.add(new clsUserType(2,"Operario", "Este usuario puede dar de alta nuevos torneos, escuelas. Puede cancelar reservas", today, null, "/img/new48.png"));
		myList.add(new clsUserType(3,"Administrador", "Este usuario tiene acceso completo a todas las opciones de la aplicacion", today, null, "/img/save48.png"));
			
		
	}
	
	public int getTamanio() {
		return this.myList.size();
	}
	
	public List<clsUserType> getAllUserTypes() {
		return this.myList;
	}	
	
	public clsUserType getUseri(int i) {
		return this.myList.get(i);
	}
	
	public void addUserType(clsUserType userType) {
		this.myList.add(userType);
	}
	
	public void deleteUser(int i) {
		this.myList.remove(i);
	}
	
}

/*

	private int id;
	private String userType, description;
	private Date createDate, deleteDate;
	private String icon;


*/