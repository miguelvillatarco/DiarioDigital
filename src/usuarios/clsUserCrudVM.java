package usuarios;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import demo.combobox.simple_combobox.Datos;
import demo.combobox.simple_combobox.ShirtData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class clsUserCrudVM {
	private String shirtColor;
    
	@Wire
	private Combobox cmbColor;
	
	private clsUser selected;
    private String recordMode;
    
    public List<String> getColors() {
        return ShirtData.getColors();
    }
    
    public String getRecordMode() {
        return this.recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public clsUser getSelected() {
        return this.selected;
    }

    public void setSelected(clsUser selected) {

        this.selected = selected;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pUser") clsUser pUser,
            @ExecutionArgParam("recordMode") String recordMode) {
    	
    	setRecordMode(recordMode);
        if (recordMode.equals("NEW")) {
        	
        	setShirtColor(new Datos().getDatoActual(2));	
            this.selected = new clsUser(0,"","","","","",2,"",null,null,"");
        }
        else {
        	
        	setShirtColor(new Datos().getDatoActual(pUser.getTipo()));	
        	this.selected = pUser;
        }
            
    }

    
    public String getShirtColor() {
    	
        return shirtColor;
    }
     
    public void setShirtColor(String shirtColor) {
    	
        this.shirtColor = shirtColor;
    }
    
    @DependsOn({"shirtColor"})
    public String getShirtImage() {
    	
        if(shirtColor==null){
            return String.format("unknow");
        }
        return String.format(shirtColor);
    }

    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
    	Map args = new HashMap();
    	
        args.put("tipoU", this.shirtColor);
        args.put("pUser", this.selected);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "updateUserList", args);
    }     
   
}
