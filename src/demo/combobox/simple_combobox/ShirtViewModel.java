package demo.combobox.simple_combobox;
 
import java.util.List;
 
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.Init;
 
public class ShirtViewModel {
    private String shirtColor;

     
    public List<String> getColors() {
        return ShirtData.getColors();
    }
          
    @Init
    public void init() {
    	
        setShirtColor(new Datos().getDatoActual(1));
        
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
     
 }