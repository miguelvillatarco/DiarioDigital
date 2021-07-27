package portada;

import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class clsPortadaListComposer extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 1L;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}  
  
 @Listen("onClick = #add")
    public void add() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sPortada", null);
        map.put("recordMode", "NEW");
        Executions.createComponents("portadaCRUD.zul", null, map);
    }
 
 @Listen("onClick = #register")
 public void register() {
     final HashMap<String, Object> map = new HashMap<String, Object>();
     map.put("sPortada", null);
     map.put("recordMode", "NEW");
     Executions.createComponents("registerPortCrud.zul", null, map);
 }
 

}
