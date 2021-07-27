package portada;

import java.sql.SQLException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class clsPortadaCrudComposer extends SelectorComposer<Component>{
	

	private static final long serialVersionUID = 1L;
	@Wire("#crud")
    private Window ventana;
  
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
  

@Listen("onClick = #submit, #cancel")
  public void closeThis() {
        ventana.detach();
       	Messagebox.show("empezandoooooo a guardar");	
    }


}
