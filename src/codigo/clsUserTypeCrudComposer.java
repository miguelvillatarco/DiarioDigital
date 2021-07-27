package codigo;

import org.zkoss.zk.ui.*;
import org.zkoss.zul.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.Listen;

public class clsUserTypeCrudComposer extends SelectorComposer<Component> {
    
	private static final long serialVersionUID = 1L;
	@Wire("#crud")
    private Window myWin;
  
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
  

@Listen("onClick = #submit, #cancel")
  public void closeThis() {
        myWin.detach();
    }
  
}
