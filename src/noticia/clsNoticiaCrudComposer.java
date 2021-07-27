package noticia;

import java.sql.SQLException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class clsNoticiaCrudComposer extends SelectorComposer<Component>{

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

