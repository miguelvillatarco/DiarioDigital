package portada;

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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;

import demo.combobox.simple_combobox.Datos;
import demo.combobox.simple_combobox.ShirtData;


public class clsPortadaCrudVM {
	
	private String shirtColor;
    
	@Wire
	private Combobox cmbColor;
	
	private clsPortada selected;
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

    public clsPortada getSelected() {
        return this.selected;
    }

    public void setSelected(clsPortada selected) {

        this.selected = selected;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pPortada") clsPortada pPortada,
            @ExecutionArgParam("recordMode") String recordMode) {
    	
    	setRecordMode(recordMode);
        if (recordMode.equals("NEW")) {
        	
        	setShirtColor(new Datos().getDatoActual(2));	
        	this.selected = new clsPortada(0,0,"");
        }
        
        else {
        	
        	setShirtColor(new Datos().getDatoActual(pPortada.getIdnoticia()));	
        	this.selected = pPortada;
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
    public void save(){
    	Map args = new HashMap();
        args.put("tipoN", this.shirtColor);
        args.put("pPortada", this.selected);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "updatePortadaList", args);
       
    } 

}
