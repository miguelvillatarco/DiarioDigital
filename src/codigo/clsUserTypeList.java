package codigo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class clsUserTypeList {
	
	private final Date today = new Date();
	private final List<clsUserType> myList = new ArrayList<clsUserType>();
	

	public clsUserTypeList(){
		myList.add(new clsUserType(1,"Administrador", "Son aquellos que tienen acceso total a la base de datos y a la aplicación desarrollada.", today, null, "/img/delete48.png"));
		myList.add(new clsUserType(2,"Lector", "Se refiere a los usuarios que acceden regularmente a la aplicación para ver noticias de manera personalizada y realizar comentarios de las mismas.", today, null, "/img/new48.png"));
		myList.add(new clsUserType(3,"Administrativo", "Se refiere a los usuarios cuya labor está relacionada directamente con los procesos de abastecimiento, producción y distribución de noticias.", today, null, "/img/save48.png"));
			
		
	}
	
	public List<clsUserType> getAllUserTypes() {
		return this.myList;
	}	
	
	
	public void addUserType(clsUserType userType) {
		this.myList.add(userType);
	}
	
	public void editUserType(clsUserType userType) {
		int pos;
		for(int i=0; i < this.myList.size() ;i ++) {
			if(this.myList.equals(userType)) {
				
			}
		}
		
	}
	
	public void deleteUserType(clsUserType userType) {
		this.myList.remove(userType);
	}
	
	
}

/*

	private int id;
	private String userType, description;
	private Date createDate, deleteDate;
	private String icon;


*/