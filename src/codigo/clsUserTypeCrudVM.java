package codigo;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;


public class clsUserTypeCrudVM{
    
    private clsUserType selected;
    private String recordMode;

    public String getRecordMode() {
        return this.recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public clsUserType getSelected() {
        return this.selected;
    }

    public void setSelected(clsUserType selected) {
        this.selected = selected;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pUserType") clsUserType pUserType,
            @ExecutionArgParam("recordMode") String recordMode) {
       
        setRecordMode(recordMode);
        if (recordMode.equals("NEW")) {
            this.selected = new clsUserType(0,"","",null,null,"");
        }
        else {
        	this.selected = pUserType;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
        Map args = new HashMap();
        args.put("pUserType", this.selected);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "updateUserTypeList", args);
    }

}




