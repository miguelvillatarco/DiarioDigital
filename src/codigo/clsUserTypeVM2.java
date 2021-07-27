package codigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;


public class clsUserTypeVM2 {
    
    private clsUserTypeList myData = new clsUserTypeList();
    private clsUserType userTypeSelected;
    
    
    public clsUserType getUserTypeSelected() {
        return this.userTypeSelected;
    }
    
    public void setUserTypeSelected(clsUserType userTypeSelected) {
        this.userTypeSelected = userTypeSelected;
    }
        

    @Init
    public void init() {
        this.myData = new clsUserTypeList();
    }

    public List<clsUserType> getAllUserTypes() {
        return myData.getAllUserTypes();
    }

    
    @NotifyChange("allUserTypes")
    @Command
    public void edit() {
    	
    	 final HashMap<String, Object> map = new HashMap<String, Object>();
         map.put("pUserType", this.userTypeSelected);
         map.put("recordMode", "EDIT");
         Executions.createComponents("userTypeCRUD.zul", null, map);
    	
        //Messagebox.show("Code here to editing!");
    }

    // The following method will be called from CustomerCRUDVM after the save
    // When we say Notifychange("allcustomers), then ZUL list items will be
    // updated
    @GlobalCommand
    @NotifyChange("allUserTypes")
    public void updateUserTypeList(@BindingParam("pUserType") clsUserType userType,
            @BindingParam("recordMode") String recordMode) {
        if (recordMode.equals("NEW")) {            
            this.myData.addUserType(userType);
        }
    }

    
    @NotifyChange("allUserTypes")
    @Command
    public void delete() {
        
    	this.myData.deleteUserType(userTypeSelected);
        
        //Messagebox.show("Code here to deleting!");
    }

}



