package modeloClasificados;


import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

public class clsClasifVM2 {

	private clsClasifList myData = new clsClasifList();
    private clsClasificados clasifSelected;
    
    
    public clsClasificados getClasifSelected() {
        return this.clasifSelected;
    }
    
    public void setClasifSelected(clsClasificados clasifSelected) {
        this.clasifSelected = clasifSelected;
    }
        

    @Init
    public void init() {
        this.myData = new clsClasifList();
    }

    public List<clsClasificados> getAllClasificados() {
        return myData.getAllClasif();
    }

    
    @NotifyChange("allClasificados")
    @Command
    public void edit() {
    	
    	 final HashMap<String, Object> map = new HashMap<String, Object>();
         map.put("pClasif", this.clasifSelected);
         map.put("recordMode", "EDIT");
         Executions.createComponents("clasifCRUD.zul", null, map);
    	
        //Messagebox.show("Code here to editing!");
    }

    // The following method will be called from CustomerCRUDVM after the save
    // When we say Notifychange("allcustomers), then ZUL list items will be
    // updated
    @GlobalCommand
    @NotifyChange("allClasificados")
    public void updateCanchaList(@BindingParam("pClasif") clsClasificados clasificados,
            @BindingParam("recordMode") String recordMode) {
        if (recordMode.equals("NEW")) {            
            this.myData.addClasificados(clasificados);
        }
    }

    
    @NotifyChange("allClasificados")
    @Command
    public void delete() {
        
    	this.myData.deleteClasificados(clasifSelected);
        
        //Messagebox.show("Code here to deleting!");
    }
    
}
