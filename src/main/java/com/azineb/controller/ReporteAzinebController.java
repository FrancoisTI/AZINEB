package com.azineb.controller;

import java.io.OutputStream;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.azineb.model.Producto;
import com.azineb.repository.ICategoriaRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReporteAzinebController {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private ICategoriaRepository repoCat;
	
	@GetMapping("/listado/AzinebProductos/pdf")
	public void llamarReporte(HttpServletResponse response) {
		// descargar reporte
		// response.setHeader("Content-Disposition", "attachment;
		// filename=\"reporte.pdf\";");
		response.setHeader("Content-Disposition", "inline;");
		// tipo de contenido
		response.setContentType("application/pdf");

		try {
			String ru = resourceLoader.getResource("classpath:reports/azinebProductos.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/reportes")
	public String abrirPagProd(Model model) {
		model.addAttribute("lstCategoria", repoCat.findAll());
		model.addAttribute("producto", new Producto());
		model.addAttribute("boton", "Reporte por filtro");
		return "reports";
	}
	
	@PostMapping("/reporte/filtro")
	public void llamarReporteFiltro(@ModelAttribute Producto producto, HttpServletResponse response) {

		response.setHeader("Content-Disposition", "inline;");

		response.setContentType("application/pdf");

		try {
			String ru = resourceLoader.getResource("classpath:reports/azinebProductosFiltro.jasper").getURI().getPath();
			HashMap parametros = new HashMap();
			parametros.put("categoria", producto.getId_categoria());
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@GetMapping("/reporte/graficos")
	public void llamarGraficos(HttpServletResponse response) {

		response.setHeader("Content-Disposition", "inline;");

		response.setContentType("application/pdf");

		try {
			String ru = resourceLoader.getResource("classpath:reports/azinebProductosStockXCategoriaGrafico.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
