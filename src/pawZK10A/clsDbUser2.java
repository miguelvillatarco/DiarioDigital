package pawZK10A;


import java.io.IOException;
import java.text.SimpleDateFormat;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import jasperreport.CustomDataSource;
import jasperreport.ReportConfig;
import jasperreport.ReportType;

import org.zkoss.zk.ui.Executions;


/// IMPORTANTE: las librerias common que estan en LIB deben ser sacadas de Ireport!!!!




public class clsDbUser2{
	private clsConnection miConn;
	private String folder;
	
	@Init
	public void init(){
		getUsersInfo();
	}
	
	private void getUsersInfo(){
	    //
	}
	    

	@Command
	public void showReport() throws JRException, IOException {    
	    
	    	this.miConn = new clsConnection("DiarioDigital", "miguel", "36486098");
			this.folder= Sessions.getCurrent().getWebApp().getRealPath("/");
						
			JasperReport report = JasperCompileManager.compileReport(this.folder+"/reportes/actUsuario.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), this.miConn.getConnection());
	        JasperExportManager.exportReportToPdfFile(print,this.folder + "actUsuario.pdf");
	        JasperViewer.viewReport(print, false);        
	      
			
			//Clients.alert("Felicitaciones, el reporte fue generado exitosamente");  
			
			Executions.getCurrent().sendRedirect("actUsuario.pdf");
		
	}
	
	@Command
	public void ReportFecha(@BindingParam("fecha_ini") Date fecha, @BindingParam("fecha_fin") Date fecha1) throws JRException, IOException {    
	    
	    	this.miConn = new clsConnection("DiarioDigital", "miguel", "36486098");
			this.folder= Sessions.getCurrent().getWebApp().getRealPath("/");
			Map<String, Object> parametros = new HashMap<String, Object>();
			SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formateador1 = new SimpleDateFormat("yyyy-MM-dd");
            String fecha_ini= formateador.format(fecha);
			parametros.put("fecha inicio",fecha_ini );
			 String fecha_fin= formateador1.format(fecha1);
			parametros.put("fecha fin",fecha_fin);
			
			JasperReport reportf = JasperCompileManager.compileReport(this.folder+"/reportes/reportFecha.jrxml");
//paso profe			JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), this.miConn.getConnection());
			JasperPrint printf = JasperFillManager.fillReport(reportf, parametros, this.miConn.getConnection());
	        
			JasperExportManager.exportReportToPdfFile(printf,this.folder + "reportFecha.pdf");
	        JasperViewer.viewReport(printf, false);        
	      
			
			//Clients.alert("Felicitaciones, el reporte fue generado exitosamente");  
			
			Executions.getCurrent().sendRedirect("reportFecha.pdf");
		
	}
	

	
	
	
}

