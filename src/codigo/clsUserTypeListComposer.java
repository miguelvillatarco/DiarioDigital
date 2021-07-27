package codigo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import java.util.HashMap;

public class clsUserTypeListComposer extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 1L;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}  
  
 @Listen("onClick = #add")
    public void add() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sUserType", null);
        map.put("recordMode", "NEW");
        Executions.createComponents("userTypeCRUD.zul", null, map);
    }
 
  
}
