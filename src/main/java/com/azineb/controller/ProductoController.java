package com.azineb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azineb.model.Producto;
import com.azineb.repository.ICategoriaRepository;
import com.azineb.repository.IEstadoRepository;
import com.azineb.repository.IProductoRepository;
import com.azineb.repository.IProveedorRepository;

@Controller
public class ProductoController {
	
	@Autowired
	private ICategoriaRepository repoCat;
	
	@Autowired 
	private IProveedorRepository repoProv;
	
	@Autowired 
	private IProductoRepository repoProd;
	
	@Autowired 
	private IEstadoRepository repoEst;
	
	@GetMapping("/home")
	public String abrirPagPrincipal() {
		return "home";
	}
	
	@GetMapping("/listado")
	public String muestraListado(Model model) {
		model.addAttribute("lstProducto", repoProd.findAll());
		model.addAttribute("lstCategoria", repoCat.findAll());
		model.addAttribute("lstProveedor", repoProv.findAll());
		model.addAttribute("lstEstado", repoEst.findAll());
		model.addAttribute("producto", new Producto());

		return "listProducto";
	}
	
	@GetMapping("/cargar")
	public String abrirPagProd(Model model) {
		model.addAttribute("lstCategoria", repoCat.findAll());
		model.addAttribute("lstProveedor", repoProv.findAll());
		model.addAttribute("lstEstado", repoEst.findAll());
		model.addAttribute("producto", new Producto());
		model.addAttribute("boton", "Registrar");
		return "crudProducto";
	}
	
	@PostMapping("producto/registrar")
	public String grabarProducto(@ModelAttribute Producto producto, Model model) {
		try {
			repoProd.save(producto);	
			model.addAttribute("mensaje", "Registro OK");
			model.addAttribute("clase", "alert alert-success");
			model.addAttribute("boton", "Registrar");
		} catch (Exception e) {
			model.addAttribute("mensaje", "Error al Registrar");
			model.addAttribute("clase", "alert alert-danger");
			model.addAttribute("boton", "Registrar");
		}
		return "crudProducto";
	}
	
	@PostMapping("/buscar")
	public String buscarProducto(@RequestParam(name = "id_producto") Integer id_producto, Model model) {
		System.out.println(id_producto);
		model.addAttribute("producto", repoProd.findById(id_producto));
		model.addAttribute("lstCategoria", repoCat.findAll());
		model.addAttribute("lstProveedor", repoProv.findAll());
		model.addAttribute("lstEstado", repoEst.findAll());
		model.addAttribute("boton", "Actualizar");
		return "crudProducto";
	}
	
	@PostMapping("/eliminar")
	public String eliminarProducto(@RequestParam(name = "id_producto") Integer id_producto, Model model) {
		repoProd.deleteById(id_producto);
		return muestraListado(model);
	}
	

}
