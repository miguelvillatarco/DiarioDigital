package noticia;

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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import demo.combobox.simple_combobox.Datos;
import demo.combobox.simple_combobox.ShirtData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class clsNoticiaCrudVM  {
	private String shirtColor;
    
	@Wire
	private Combobox cmbColor;
	
	private clsNoticia selected;
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

    public clsNoticia getSelected() {
        return this.selected;
    }

    public void setSelected(clsNoticia selected) {

        this.selected = selected;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view,
            @ExecutionArgParam("pNoticia") clsNoticia pNoticia,
            @ExecutionArgParam("recordMode") String recordMode) {
    	
    	setRecordMode(recordMode);
        if (recordMode.equals("NEW")) {
        	
        	setShirtColor(new Datos().getDatoActual(2));	
        	this.selected = new clsNoticia(0,"","","","","",null,"");
        }
       // this.selected = new clsNoticia(0,"","","","","","","");
        
        else {
        	
        	setShirtColor(new Datos().getDatoActual(pNoticia.getIdnoticia()));	
        	this.selected = pNoticia;
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
        args.put("pNoticia", this.selected);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "updateNoticiaList", args);
        
       
    }  
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    public void save1(){
    	Map args = new HashMap();
        args.put("tipoN", this.shirtColor);
        args.put("pNoticia", this.selected);
        args.put("recordMode", this.recordMode);
        BindUtils.postGlobalCommand(null, null, "editNoticiaList", args);
        
       
    }   
   
}