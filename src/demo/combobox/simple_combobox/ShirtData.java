package demo.combobox.simple_combobox;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

 
public class ShirtData {
     
    private static List<String> colors = new ArrayList<String>();

    
   
    
    
    static{

    }
     
    public static final List<String> getColors() {
    	Datos base = new Datos();
    	colors = base.getColor();
        
        return new ArrayList<String>(colors);
    }
 
    
}