package modeloNoticias;

 
	import org.zkoss.web.servlet.dsp.action.Include;
	import org.zkoss.zk.ui.Component;
	import org.zkoss.zk.ui.Executions;
	import org.zkoss.zk.ui.Path;
	import org.zkoss.zk.ui.event.MouseEvent;
	import org.zkoss.zk.ui.select.SelectorComposer;
	import org.zkoss.zk.ui.select.annotation.Listen;
	import org.zkoss.zk.ui.select.annotation.Wire;
	import org.zkoss.zk.ui.util.Clients;
	import org.zkoss.zul.Borderlayout;
	import org.zkoss.zul.Center;
	import org.zkoss.zul.impl.LabelElement;



	public class noticia extends SelectorComposer<Component>  {

		private static final long serialVersionUID = 1L;
		
		
		@Listen("onClick = mostrarnoticia")
	    public void mostrarnoticia9(MouseEvent event){
			
			String zulFilePathName;
			Borderlayout bl = (Borderlayout) Path.getComponent("/main/mainlayout");
			/* get an instance of the searched CENTER layout area */
			Center center = bl.getCenter();
				

			/* clear the center child comps */
			center.getChildren().clear();

			//Messagebox.show("inside"  + event.getTarget().getId());
			// /torneoGrid.zul
			zulFilePathName = "/"+ event.getTarget().getId() + ".zul";
			/* create the page and put it in the center layout area */
			Executions.createComponents(zulFilePathName, center, null);

			
	    }

}
