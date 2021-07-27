package noticia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;

import codigoBase.clsConnection;
import codigoBase.clsMailSender;
import usuarios.clsConeccionBase;
import usuarios.clsUser;


public class clsNoticiaVM2 {
	private static final long serialVersionUID = 5471706011875481102L;	
	private static Logger log = Logger.getLogger(clsNoticiaVM2.class.getName());
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
    private clsConeccionBase conn = new clsConeccionBase();
    private String sql1="";
    private String sql2="";
   
    private final List<clsNoticia> myList = new ArrayList<clsNoticia>();

    
    static clsMailSender sender = new clsMailSender(); 
    
	    private clsNoticia noticiaSelected;
	    
	    
	    public clsNoticia getNoticiaSelected() {
	        return this.noticiaSelected;
	    }
	    
	    public void setNoticiaSelected(clsNoticia noticiaSelected) {
	        this.noticiaSelected = noticiaSelected;
	    }
	

	    @Init
	    public void init() {
	    }

	    public List<clsNoticia> getAllNoticia() {	    		
	    	 try {	    	 
		 	    	sql1="SELECT idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe FROM DiarioDigital.noticias order by idnoticias desc";	
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();		    
				    while (this.rs.next()){	
				    	myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getString(8)));
				    }
				    return myList;
			    
		      }catch (Exception e){	    	
			    	 e.printStackTrace(); 
			    	 this.log.severe("ERROR in SQL: " + e.toString());
		     }finally { //cleanup
		          if (this.ps!= null) {
			             try {
			            	 this.ps.close();
			             } catch (SQLException ex) {
			                  this.log.severe(ex.toString()); //log and ignore
			             }
		          }
		          if (this.conn!= null) {
			             try {
			            	 this.conn.getConnection().close();
			             } catch (SQLException ex) {
			                  log.severe(ex.toString()); //log and ignore
			            }
		          }
		     }
	    	return null; 
	   }
	    
	    public List<clsNoticia> getpolitica() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe "
		 	    			+ "from DiarioDigital.noticias"
		 	    			+ " where DiarioDigital.noticias.genero like \"%politica%\" order by fecha desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
	
	    public List<clsNoticia> getdeportes() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe from DiarioDigital.noticias where DiarioDigital.noticias.genero like \"%DEPORTES%\" order by idnoticias desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
	    public List<clsNoticia> gettecnologia() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe from DiarioDigital.noticias where DiarioDigital.noticias.genero like \"%tecnologia%\" order by fecha desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
	    public List<clsNoticia> getmundo() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe "
		 	    			+ "from DiarioDigital.noticias "
		 	    			+ "where DiarioDigital.noticias.genero like \"%mundo%\" order by fecha desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
	    public List<clsNoticia> getsociedad() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe "
		 	    			+ "from DiarioDigital.noticias "
		 	    			+ "where DiarioDigital.noticias.genero like \"%sociedad%\" order by fecha desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
	    
	    public List<clsNoticia> geteconomia() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe "
		 	    			+ "from DiarioDigital.noticias "
		 	    			+ "where DiarioDigital.noticias.genero like \"%economia%\" order by fecha desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
	    public List<clsNoticia> getultimasnoticias() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe "
		 	    			+ "from DiarioDigital.noticias "
		 	    			+ " order by idnoticias desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
	
	    public List<clsNoticia> getpoliciales() {	    		
	    	 try {	
	    			
		 	    	sql1="select idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe "
		 	    			+ "from DiarioDigital.noticias "
		 	    			+ "where DiarioDigital.noticias.genero like \"%policiales%\" order by fecha desc";
		 	    			
		 	    	
			 		this.ps = this.conn.getConnection().prepareStatement(sql1);
				    // ejecucion
				    this.rs = ps.executeQuery();
				    String imagen = "Imagenes/";
				    while (this.rs.next()){
				    
				    	//imagen+=rs.getString(6);
				    	 myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),  "Imagenes/"+rs.getString(6), rs.getDate(7),rs.getString(8)));
				    	 
				    	}
				    
				    return myList;
			    
	    	 }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	          }
	    	 return null; 
	  }
  
	    
	public int contVistas() {
	    	int cont=0;
	    	try {	    	 
	 	    	sql1="SELECT idnoticias ,titulo, copete, cuerpo, genero, imagen, fecha, epigrafe FROM DiarioDigital.noticias";	
	 	    	
		 		this.ps = this.conn.getConnection().prepareStatement(sql1);
			    // ejecucion
			    this.rs = ps.executeQuery();		    
			    while (this.rs.next()){	
			    	myList.add(new clsNoticia(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7),rs.getString(8)));
			    	cont++;
			    }
			    return cont;
		    
	      }catch (Exception e){	    	
		    	 e.printStackTrace(); 
		    	 this.log.severe("ERROR in SQL: " + e.toString());
	     }finally { //cleanup
	          if (this.ps!= null) {
		             try {
		            	 this.ps.close();
		             } catch (SQLException ex) {
		                  this.log.severe(ex.toString()); //log and ignore
		             }
	          }
	          if (this.conn!= null) {
		             try {
		            	 this.conn.getConnection().close();
		             } catch (SQLException ex) {
		                  log.severe(ex.toString()); //log and ignore
		            }
	          }
	     }
    	return cont; 
   }
	    	
	    

	   @NotifyChange("allNoticia")
	   @Command
	   public void edit() {
			final HashMap<String, Object> map = new HashMap<String, Object>();
	    	 map.put("pNoticia", this.noticiaSelected);
	         map.put("recordMode", "EDIT");
	         Executions.createComponents("noticiaCRUD.zul", null, map);
	    }

	    // The following method will be called from CustomerCRUDVM after the save
	    // When we say Notifychange("allcustomers), then ZUL list items will be
	    // updated
	    @GlobalCommand
	    @NotifyChange("allNoticia")
	    public void updateNoticiaList(@BindingParam("pNoticia") clsNoticia noticia,
	            @BindingParam("recordMode") String recordMode,
	            @BindingParam("tipoN") String tipo) {
	    	
	    	if (recordMode.equals("NEW")) {
	    		try {	 
	    	
	    			conn = new clsConeccionBase();
			    	sql1="select titulo, copete, cuerpo, genero, imagen, fecha, epigrafe from DiarioDigital.noticias  WHERE genero = ?";		
			 		ps = conn.getConnection().prepareStatement(sql1);
			 		if(!tipo.equals("")) {
			 			this.ps.setString(1, tipo);	
			 		}else {
			 			this.ps.setString(1, "noticia");
			 		}
			
				    // ejecucion
			 		rs = ps.executeQuery();	
			 		while (rs.next()){	
						
				    	noticia.setIdnoticia(rs.getInt("id"));
				    } 
				    sql1 = "INSERT INTO DiarioDigital.noticias VALUES ( 0 , ? , ? , ? , ? , ? , ? , ? , NULL)";		
				   	this.ps = this.conn.getConnection().prepareStatement(sql1);
				   	//parametros
				   	this.ps.setString(1, noticia.getTitulo());
					this.ps.setString(2, noticia.getCopete());
					this.ps.setString(3, noticia.getCuerpo());
					this.ps.setString(4, noticia.getGenero());
					this.ps.setString(5, noticia.getImagen());
					this.ps.setDate(6, getCurrentDate());
					this.ps.setString(7, noticia.getEpigrafe());
				
					//ejecucion
				   	ps.execute();	
				   	Messagebox.show("Empezando a guardar la Usuario...");
					Messagebox.show("Los Datos del Usuario se guardaron Correctamente!!!");
					
	    		} catch (Exception e){	    	
			   	    	 e.printStackTrace(); 
			   	    	 this.log.severe("ERROR in SQL: " + e.toString());
			   	}finally { //cleanup
			   	          if (this.ps!= null) {
			   	             try {
			   	            	 this.ps.close();
			   	             } catch (SQLException ex) {
			   	                  this.log.severe(ex.toString()); //log and ignore
			   	             }
			   	          }
			   	          if (this.conn!= null) {
			   	             try {
			   	            	 this.conn.CerrarConeccion();
			   	             } catch (SQLException ex) {
			   	                  log.severe(ex.toString()); //log and ignore
			   	            }
			   	          }
			   	     }
	    	}else {
	    		try {	
	    			conn = new clsConeccionBase();
	    			sql1="SELECT id, descripcion FROM tipoUsuario WHERE descripcion = ?";			
			 		ps = conn.getConnection().prepareStatement(sql1);
			 		this.ps.setString(1, tipo);
				    // ejecucion
			 		rs = ps.executeQuery();		    
				    while (rs.next()){	
				    	noticia.setIdnoticia(rs.getInt("id"));
				    }     	    
		    		conn = new clsConeccionBase();
	    			sql2="UPDATE DiarioDigital.noticias SET "
	    					+ "titulo = ? ,"
	    					+ "copete = ? ,"
	    					+ "cuerpo = ? ,"
	    					+ "genero = ? ,"
	    					+ "imagen = ? ,"
	    					+ "fecha = ? ,"
	    					+ "epigrafe = ? ,"
	    			+ "WHERE DiarioDigital.noticias.idnoticias = ? ";
			    	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
				    this.ps.setString(1, noticia.getTitulo());
				    this.ps.setString(2, noticia.getCopete());
				    this.ps.setString(3, noticia.getCuerpo());
				    this.ps.setString(4, noticia.getGenero());
				    this.ps.setString(5, noticia.getImagen());
				    this.ps.setDate(6, getCurrentDate());
				    this.ps.setString(7, noticia.getEpigrafe());
				    this.ps.setInt(8, noticia.getIdnoticia());
				    this.ps.execute();  
				    Messagebox.show("Editando Noticia...");	
				   	Messagebox.show("NOTICIA Modificada");
			     } catch (Exception e){	    	
		   	    	 e.printStackTrace(); 
		   	    	 this.log.severe("ERROR in SQL: " + e.toString());
		   	     }finally { //cleanup
		   	          if (this.ps!= null) {
		   	             try {
		   	            	 this.ps.close();
		   	             } catch (SQLException ex) {
		   	                  this.log.severe(ex.toString()); //log and ignore
		   	             }
		   	          }
		   	          if (this.conn!= null) {
		   	             try {
		   	            	 this.conn.CerrarConeccion();
		   	             } catch (SQLException ex) {
		   	                  log.severe(ex.toString()); //log and ignore
		   	            }
		   	          }
		   	     }
    		}
	    }
	      
	    
	    // The following method will be called from CustomerCRUDVM after the save
	    // When we say Notifychange("allcustomers), then ZUL list items will be
	    // updated
	    @GlobalCommand
	    @NotifyChange("allNoticia")
	    public void editNoticiaList(@BindingParam("pNoticia") clsNoticia noticia,
	            @BindingParam("recordMode") String recordMode,
	            @BindingParam("tipoN") String tipo) {
	    	
	    	if (recordMode.equals("Edit")) {
	    		try {
	    			conn = new clsConeccionBase();
	    			sql1="SELECT id, descripcion FROM tipoUsuario WHERE descripcion = ?";			
			 		ps = conn.getConnection().prepareStatement(sql1);
			 		this.ps.setString(1, tipo);
				    // ejecucion
			 		rs = ps.executeQuery();		    
				    while (rs.next()){	
				    	noticia.setIdnoticia(rs.getInt("id"));
				    }     	    
		    		conn = new clsConeccionBase();
	    			sql2="UPDATE DiarioDigital.noticias SET "
	    					+ "titulo = ? ,"
	    					+ "copete = ? ,"
	    					+ "cuerpo = ? ,"
	    					+ "genero = ? ,"
	    					+ "imagen = ? ,"
	    					+ "fecha = ? ,"
	    					+ "epigrafe = ? "
	    					+ "WHERE usuario = ? ";
			    	this.ps = this.conn.getConnection().prepareStatement(sql2);				    
				    this.ps.setString(1, noticia.getTitulo());
				    this.ps.setString(2, noticia.getCopete());
				    this.ps.setString(3, noticia.getCuerpo());
				    this.ps.setString(4, noticia.getGenero());
				    this.ps.setString(5, noticia.getImagen());
				    this.ps.setDate(6, getCurrentDate());
				    this.ps.setString(7, noticia.getEpigrafe());
				  //  this.ps.setString(8, noticia.getvideo());
				    this.ps.execute();  
				    Messagebox.show("Editando Noticia...");	
				   	Messagebox.show("NOTICIA Modificada");
			     } catch (Exception e){	    	
		   	    	 e.printStackTrace(); 
		   	    	 this.log.severe("ERROR in SQL: " + e.toString());
		   	     }finally { //cleanup
		   	          if (this.ps!= null) {
		   	             try {
		   	            	 this.ps.close();
		   	             } catch (SQLException ex) {
		   	                  this.log.severe(ex.toString()); //log and ignore
		   	             }
		   	          }
		   	          if (this.conn!= null) {
		   	             try {
		   	            	 this.conn.CerrarConeccion();
		   	             } catch (SQLException ex) {
		   	                  log.severe(ex.toString()); //log and ignore
		   	            }
		   	          }}}
	    		}
   
   
	    @NotifyChange("allNoticia")
	    @Command
	    public void delete() {
	    	conn = new clsConeccionBase();
	    	 try {	 
	    	    	//sql1 = "DELETE FROM DiarioDigital.noticias WHERE titulo = ?";
	    	    	sql1 = "DELETE FROM `DiarioDigital`.`noticias` WHERE `idnoticias` = ?";
			    	this.ps = this.conn.getConnection().prepareStatement(sql1);
			    	this.ps.setInt(1, this.noticiaSelected.getIdnoticia());
			    
		   	 		ps.execute();
		   	 	Messagebox.show("Empezando a eliminar...");	
		   	
	    	 } catch (Exception e){	    	
	   	    	 e.printStackTrace(); 
	   	    	 this.log.severe("ERROR in SQL: " + e.toString());
	   	     }finally { //cleanup
	   	          if (this.ps!= null) {
	   	             try {
	   	            	 this.ps.close();
	   	             } catch (SQLException ex) {
	   	                  this.log.severe(ex.toString()); //log and ignore
	   	             }
	   	          }
	   	          if (this.conn!= null) {
	   	             try {
	   	            	 this.conn.CerrarConeccion();
	   	             } catch (SQLException ex) {
	   	                  log.severe(ex.toString()); //log and ignore
	   	            }
	   	          }
	   	     }
	    	 Messagebox.show("NOTICIA ELIMINADA");	
	    }
	    
	    
	    
	    private static void sendMail(String emailAccount, String usuario) throws AddressException, MessagingException
		{
			String subject="PAW 2017";
		    String body="Hola " + usuario + "!";    
		    sender.send(emailAccount, subject, body); // mando email
		}
		
		private static java.sql.Date getCurrentDate() {
		    java.util.Date today = new java.util.Date();
		    return new java.sql.Date(today.getTime());
		}	
}