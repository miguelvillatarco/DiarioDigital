package modeloClasificados;


import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;

public class clsClasifCrudVM {
	
	private clsClasificados selected;
    private String recordMode;

    public String getRecordMode() {
        return this.recordMode;
    }

    public void setRecordMode(String recordMode) {
        this.recordMode = recordMode;
    }

    public clsClasificados getSelected() {
        return this.selected;
    }

    public void setSelected(clsClasificados selected) {
        this.selected = selected;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pClasif") clsClasificados pClasif,
            @ExecutionArgParam("recordMode") String recordMode) {
       
        setRecordMode(recordMode);
        if (recordMode.equals("NEW")) {
            this.selected = new clsClasificados(0,"","","","");
        }
        else {
        	this.selected = pClasif;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save() {
        Map args = new HashMap();
        args.put("pClasif", this.selected);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "updateClasifList", args);
    }
    
}
