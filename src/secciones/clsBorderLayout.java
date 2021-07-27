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


public class clsBorderLayout extends GenericForwardComposer{
	private clsConnection miConn;
	private Window mywindow;
	private Div myDiv; private Ul myUl1, myUl3; private Li myLi1,myLi2,myLi3, myLi31, myLi32,myLi4;
	private A myA1, myA2, myA31; private B myB;
	
	
	public void doAfterCompose (Component comp) throws Exception {
		super.doAfterCompose(comp);
		clsMenuOps opcion;
		ArrayList<clsMenuOps> milista = new ArrayList<clsMenuOps>();	
		boolean flag=true; int position;
		int padre;
		   
		myDiv = new Div();
		myDiv.setId("myDiv"); myDiv.setSclass("bs-example");
		
		myUl1 = new Ul();
		myUl1.setId("myUl"); myUl1.setSclass("nav nav-pills");
		position=0;
		while (flag){		 	
			 				
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

			 				
			 			
			 		
			 		myLi1.appendChild(myA1);
			 		myUl1.appendChild(myLi1);		 			
	 			}
	 			
	
	
		
	}
}