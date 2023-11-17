package com.azineb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.azineb.model.Usuario;
import com.azineb.repository.IUsuarioRepository;

@Controller
public class SesionController {
	
	@Autowired
	private IUsuarioRepository repoUsu;
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login")
	public String ingresar(@ModelAttribute Usuario usuario, Model model) {
		System.out.println(usuario);
		Usuario u = repoUsu.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
		if (u!=null) {
			model.addAttribute("mensaje",""+u.getNombre_usuario()+" "+u.getApellido_usuario());
			model.addAttribute("clase","alert alert-succes");
			return "home";
		} else {
			model.addAttribute("mensaje","Usuario o Contraseña incorrecta");
			model.addAttribute("clase","alert alert-danger");
			return "login";
		}
				
	}
}
