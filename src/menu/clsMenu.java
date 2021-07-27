package menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Window;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menu;

import codigoBase.clsConnection;

public class clsMenu extends GenericForwardComposer{
	private clsConnection conn;
	private Menubar menubar;
	private Window mywindow;
	
	private PreparedStatement ps = null;
    private ResultSet rs = null;
    
   
	
	public void doAfterCompose (Component comp) throws Exception {
		super.doAfterCompose(comp);
		clsMenuOpt opcion;
		ArrayList<clsMenuOpt> milista = new ArrayList<clsMenuOpt>();	
		boolean flag=true; int position;
		int padre;
		Menu menuConOpcs = null;
		Menupopup mp = null;
		
		
		
		////////Session atributo
		Session sess = Sessions.getCurrent();
		String usu = String.valueOf(sess.getAttribute("usuario"));
		//////////
		
		this.conn = new clsConnection();
		//String userlogin = Executions.getCurrent().getParameter("usuario");; 
		String sql="SELECT menu.id, padre, nombre, etiqueta, icono, pagina, habilitado FROM usermenu "
				+ "INNER JOIN menu ON menu.id = idmenu WHERE iduser = "
				+ "(SELECT idtipo FROM usuarios WHERE usuario = ? ) "
				+ "order by orden";
		
		this.ps = this.conn.getConnection().prepareStatement(sql);	
	    // ejecucion
		this.ps.setString(1, usu);
	    this.rs = ps.executeQuery();		    
		while(this.rs.next()) {
		    opcion= new clsMenuOpt(this.rs.getInt("id"), this.rs.getInt("padre"), this.rs.getString("nombre"), this.rs.getString("etiqueta"), this.rs.getString("icono"), this.rs.getString("pagina"), this.rs.getInt("habilitado"));			
		    milista.add(opcion);	    
		}
		menubar = new Menubar();
		padre=-1;
		position=0;
		while (flag){		 	
		 	opcion= milista.get(position);
		 	if (opcion.getPadre()==-1){
		 		// es menu u opcion simple
		 	    // debo ver si cierro el menu desplegable que iba armando
 				if (padre!=-1) {				 		
			 		// debo cerrar menu desplegable
			 		menuConOpcs.appendChild(mp);
			 		menubar.appendChild(menuConOpcs);		 			
			 		padre=-1; // para comenzar todo de nuevo			 		
			 	}
		 		if (opcion.getPagina().contains(".zul") || opcion.getPagina().contains(".ar") || opcion.getPagina().contains(".com") || opcion.getPagina().contains(".pdf")){
	 				// es opcion de mprincipal
		 			Menuitem opcionS= new Menuitem();
		 			opcionS.setId(opcion.getNombre());
		 			opcionS.setLabel(opcion.getEtiqueta());
		 				String pagina = opcion.getPagina(); 
				 		if (opcion.getHabilitado()==0){
					 			if (opcion.getPagina().contains(".zul")){
					 				opcionS.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
					 					public void onEvent(Event event) throws Exception{
					 						/////// AGREGADO PARA ABRIR DEL MEU /////
					 						String zulFilePathName;
					 						Borderlayout bl = (Borderlayout) Path.getComponent("/main/mainlayout");
					 						/* get an instance of the searched CENTER layout area */
					 						Center center = bl.getCenter();
					 						/* clear the center child comps */
					 						center.getChildren().clear();
					 						zulFilePathName = "/"+ event.getTarget().getId() + ".zul";
					 						/* create the page and put it in the center layout area */
					 						Executions.createComponents(zulFilePathName, center, null);
					 						///////7
					 					}
					 				});
						 			}else {
						 				opcionS.setHref(opcion.getPagina());
						 				opcionS.setTarget("uno");
						 			}
						 			if (opcion.getIcono().contains(".png")||opcion.getIcono().contains(".jpg")){		 				
						 				opcionS.setImage("/img/"+ opcion.getIcono());
						 			}		 			
				 		}
				 		menubar.appendChild(opcionS);	
		 			
	 			}
		 	}
		 	else {
		 		// es opcion de menu desplegable		 		
		 		if (padre==-1){
			 		// debo crear popup y menu
		 			padre = opcion.getPadre();
		 			menuConOpcs = new Menu();
		 			clsMenuOpt opcionMenu;
		 			int k=0;
		 			while (k<milista.size()){
		 				opcionMenu = milista.get(k);
		 				if (opcionMenu.getId()==padre){
		 					menuConOpcs.setId(opcionMenu.getNombre());
				 			menuConOpcs.setLabel(opcionMenu.getEtiqueta());
				 			if (opcionMenu.getIcono().contains(".png") || opcionMenu.getIcono().contains(".jpg")){		 				
				 				menuConOpcs.setImage("/img/"+ opcionMenu.getIcono());
				 			}
				 			k=milista.size()+10;
		 				}
		 				k++;
		 			}
		 			// debo crear popup
		 			mp= new Menupopup();
		 			// ahora la opcion de item
		 			Menuitem opcionMD = new Menuitem();
		 			opcionMD.setId(opcion.getNombre());
		 			opcionMD.setLabel(opcion.getEtiqueta());
			 		if (opcion.getHabilitado()==0){
			 			if (opcion.getPagina().contains(".zul")||opcion.getPagina().contains(".pdf")||opcion.getPagina().contains(".ar")){
				 			if(!opcion.getPagina().contains(".zul")) {
				 				opcionMD.setHref(opcion.getPagina());
				 				opcionMD.setTarget("uno");
				 			}else {
				 					opcionMD.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
				 					public void onEvent(Event event) throws Exception{			 												
				 						/////// AGREGADO PARA ABRIR DEL MEU /////
				 						String zulFilePathName;
				 						Borderlayout bl = (Borderlayout) Path.getComponent("/main/mainlayout");
				 						/* get an instance of the searched CENTER layout area */
				 						Center center = bl.getCenter();
				 						/* clear the center child comps */
				 						center.getChildren().clear();
				 						zulFilePathName = "/"+ event.getTarget().getId() + ".zul";
				 						/* create the page and put it in the center layout area */
				 						Executions.createComponents(zulFilePathName, center, null);
				 						///////7
				 					}
				 				});
			 				}
			 			}
			 			if (opcion.getIcono().contains(".png")||opcion.getIcono().contains(".jpg")){		 				
			 				opcionMD.setImage("/img/"+ opcion.getIcono());
			 			}		 			
			 		}
			 		// agrego opcion al popup
			 		mp.appendChild(opcionMD);	 			
			 	} else{
			 		if (padre==opcion.getPadre()){
			 		// ahora la opcion de item
			 			Menuitem opcionMD = new Menuitem();			 			
			 			opcionMD.setId(opcion.getNombre());
			 			opcionMD.setLabel(opcion.getEtiqueta());
			 		
				 		if (opcion.getHabilitado()==0){
				 			if (opcion.getPagina().contains(".zul")||opcion.getPagina().contains(".pdf")||opcion.getPagina().contains(".ar")){
				 				if(!opcion.getPagina().contains(".zul")) {
					 				opcionMD.setHref(opcion.getPagina());
					 				opcionMD.setTarget("uno");
				 				}else {
					 					opcionMD.addEventListener(Events.ON_CLICK, new EventListener<Event>(){
					 					public void onEvent(Event event) throws Exception{
							 				/////// AGREGADO PARA ABRIR DEL MEU /////
					 						String zulFilePathName;
					 						Borderlayout bl = (Borderlayout) Path.getComponent("/main/mainlayout");
					 						/* get an instance of the searched CENTER layout area */
					 						Center center = bl.getCenter();
					 						/* clear the center child comps */
					 						center.getChildren().clear();
					 						zulFilePathName = "/"+ event.getTarget().getId() + ".zul";
					 						/* create the page and put it in the center layout area */
					 						Executions.createComponents(zulFilePathName, center, null);
					 						///////7
					 					}
					 				});
				 				}
				 			}
				 			if (opcion.getIcono().contains(".png")||opcion.getIcono().contains(".jpg")){		 				
				 				opcionMD.setImage("/img/"+opcion.getIcono());
				 			}		 			
				 		}
				 		// agrego opcion al popup
				 		mp.appendChild(opcionMD);			 			
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
	 			menuConOpcs.appendChild(mp);
		 		menubar.appendChild(menuConOpcs);
	 		}	
		}
		
		mywindow.appendChild(menubar);
	}
}