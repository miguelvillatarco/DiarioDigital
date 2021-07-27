package secciones;


import java.sql.ResultSet;
import java.util.ArrayList;

import org.zkoss.zhtml.B;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.A;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import com.mysql.jdbc.PreparedStatement;


public class clsMenu2 extends GenericForwardComposer{
	private clsConnection miConn;
	private Window mywindow;
	private Div myDiv; private Ul myUl1, myUl3; private Li myLi1,myLi2,myLi3, myLi31, myLi32,myLi4;
	private A myA1, myA2, myA31; private B myB;
	
	private static String user="miguel";
	private static String password="36486098"; // "Abcz.1234"
	private static String connection="DiarioDigital";
	
	
	public void doAfterCompose (Component comp) throws Exception {
		super.doAfterCompose(comp);
		clsMenuOps opcion;
		ArrayList<clsMenuOps> milista = new ArrayList<clsMenuOps>();	
		boolean flag=true; int position;
		int padre;
		
		this.miConn = new clsConnection();
		
		String sql="SELECT id, padre, nombre, etiqueta, icono, pagina, habilitado FROM menu order by orden";		
		PreparedStatement Statement = (PreparedStatement)this.miConn.getConnection().prepareStatement(sql);
		ResultSet result = Statement.executeQuery();
		
		while(result.next()) {
		    opcion= new clsMenuOps(result.getInt("id"), result.getInt("padre"), result.getString("nombre"), result.getString("etiqueta"), result.getString("icono"), result.getString("pagina"), result.getInt("habilitado"));			
		    milista.add(opcion);	    
		}
		
		myDiv = new Div();
		myDiv.setId("myDiv"); myDiv.setSclass("bs-example");
		
		myUl1 = new Ul();
		myUl1.setId("myUl"); myUl1.setSclass("nav nav-pills");
	
		
		padre=-1;
		position=0;
		while (flag){		 	
		 	opcion= milista.get(position);
		 	if (opcion.getPadre()==-1){
		 		// es menu u opcion simple
		 		
		 	    // debo ver si cierro el menu desplegable que iba armando
 				if (padre!=-1) {				 		
			 		// debo cerrar menu desplegable			 		
			 		myLi3.appendChild(myUl3);
			 		myUl1.appendChild(myLi3);	
			 		
			 		padre=-1; // para comenzar todo de nuevo			 		
			 	}
		 		
		 		
		 		if (opcion.getPagina().contains(".zul")){
	 				// es opcion de mprincipal
		 					 			
		 			myLi1=new Li();
		 			myLi1.setId(opcion.getNombre());
		 			
		 			if (opcion.getEtiqueta().contains("Inicio")){
		 				myLi1.setSclass("active");
		 			}
		 			
		 			myA1= new A();
		 			myA1.setId(opcion.getNombre());
		 			myA1.setLabel(opcion.getEtiqueta());
		 			 		
			 		if (opcion.getHabilitado()==0){
			 			if (opcion.getPagina().contains(".zul")){
			 				//myA1.setHref(opcion.getPagina());		
			 				
			 				myA1.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
			 					public void onEvent(Event event) throws Exception{
			 						
			 						String zulFilePathName;
			 						Borderlayout bl = (Borderlayout) Path.getComponent("/main/mainlayout");
			 						/* get an instance of the searched CENTER layout area */
			 						Center center = bl.getCenter();

			 						/* clear the center child comps */
			 						center.getChildren().clear();

			 						//Messagebox.show("inside"  + event.getTarget().getId());
			 						zulFilePathName = "/"+ event.getTarget().getId() + ".zul";
			 						/* create the page and put it in the center layout area */
			 						Executions.createComponents(zulFilePathName, center, null);
			 									 						
			 					}
			 				});

			 				
			 				
			 			}			 					 			
			 		}
			 		
			 		myLi1.appendChild(myA1);
			 		myUl1.appendChild(myLi1);		 			
	 			}
	 			
		 		
		 	}
		 	else {
		 		// es opcion de menu desplegable		 		
		 		if (padre==-1){
			 		// debo crear popup y menu
		 			padre = opcion.getPadre();
		 			
		 			clsMenuOps opcionMenu;
	
		 			myLi3=new Li();
					myLi3.setId("menu" + opcion.getId()); myLi3.setSclass("dropdown");
			 			
		 			int k=0;
		 			while (k<milista.size()){
		 				opcionMenu = milista.get(k);
		 				if (opcionMenu.getId()==padre){
		 			 			
							myA2=new A();
							myA2.setId(opcionMenu.getNombre()); 
							myA2.setHref("#"); myA2.setLabel(opcionMenu.getEtiqueta()); myA2.setSclass("dropdown-toggle");
					
							myB = new B(); myB.setSclass("caret");
							myA2.appendChild(myB);
							
							myLi3.appendChild(myA2);
							
				 			k=milista.size()+10;
		 				}
		 				k++;
		 			}
		 				 			
		 	
					
					myUl3=new Ul();
					myUl3.setId("myUl3"); myUl3.setSclass("dropdown-menu");
				
	
					myLi31=new Li();
					myLi31.setId(opcion.getNombre());
			
					myA31=new A();
					myA31.setId(opcion.getNombre()); 
					myA31.setLabel(opcion.getEtiqueta()); 
						
			 		
			 		if (opcion.getHabilitado()==0){
			 			if (opcion.getPagina().contains(".zul")){
			 				//myA31.setHref(opcion.getPagina());
			 				myA31.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
			 					public void onEvent(Event event) throws Exception{
			 						
			 						String zulFilePathName;
			 						Borderlayout bl = (Borderlayout) Path.getComponent("/main/mainlayout");
			 						/* get an instance of the searched CENTER layout area */
			 						Center center = bl.getCenter();

			 						/* clear the center child comps */
			 						center.getChildren().clear();

			 						//Messagebox.show("inside"  + event.getTarget().getId());
			 						zulFilePathName = "/"+ event.getTarget().getId() + ".zul";
			 						/* create the page and put it in the center layout area */
			 						Executions.createComponents(zulFilePathName, center, null);
			 									 						
			 					}
			 				});

			 			}
			 			
			 		}
			 		
					
					myLi31.appendChild(myA31);
					myUl3.appendChild(myLi31);
					
		 			
			 	} else{
			 		if (padre==opcion.getPadre()){
			 		// ahora la opcion de item
			 						 			
			 			myLi31=new Li();
						myLi31.setId(opcion.getNombre());
				
						myA31=new A();
						myA31.setId(opcion.getNombre()); 
						myA31.setLabel(opcion.getEtiqueta()); 
							
			 			
				 		if (opcion.getHabilitado()==0){
				 			if (opcion.getPagina().contains(".zul")){
				 				//myA31.setHref(opcion.getPagina());	
				 				
				 				myA31.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
				 					public void onEvent(Event event) throws Exception{
				 						
				 						String zulFilePathName;
				 						Borderlayout bl = (Borderlayout) Path.getComponent("/main/mainlayout");
				 						/* get an instance of the searched CENTER layout area */
				 						Center center = bl.getCenter();

				 						/* clear the center child comps */
				 						center.getChildren().clear();

				 						//Messagebox.show("inside"  + event.getTarget().getId());
				 						zulFilePathName = "/"+ event.getTarget().getId() + ".zul";
				 						/* create the page and put it in the center layout area */
				 						Executions.createComponents(zulFilePathName, center, null);
				 									 						
				 					}
				 				});

							 				
				 			}
				 					 			
				 		}
				 		
				 		// agrego opcion al popup
				 		myLi31.appendChild(myA31);
						myUl3.appendChild(myLi31);								 			
			 			
			 		}
			 	}
			 		
		 	}
		 	
		 	
		 	position++;		 	
		 	if (position>=milista.size()){
		 		flag=false;
		 	}
		 	
		}			
		
		if (padre!=-1){ 
			if (padre==milista.get(milista.size()-1).getPadre()){
	 			// falta cerrar el ultimo mdesplegable
				myLi3.appendChild(myUl3);
				myUl1.appendChild(myLi3);	
	 		}	

		}
		
			
		myDiv.appendChild(myUl1);
		//mywindow.appendChild(myDiv);
		
	}
	
	
		
	
}
