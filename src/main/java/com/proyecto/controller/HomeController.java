package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.entities.Prenda;
import com.proyecto.service.IPrendasService;


@Controller
public class HomeController {
	
	@Autowired
	private IPrendasService prendasService;
	
	@GetMapping("/")   
	public String home(Model model, Authentication auth) {
		
		if(auth != null) {
			String username = auth.getName();
			model.addAttribute("username", username);
		}				
		
		model.addAttribute("prendas",prendasService.listadoPrendas());
		model.addAttribute("prenda", new Prenda());

		return "home";
	}
	
	
	@GetMapping("/Prueva")   
	public String prueva(Model model) {			

		return "prueva";
	}
	
	@GetMapping("/hombres")   
	public String hombres(Model model) {			

		return "hombres";
	}
	@GetMapping("/mujeres")   
	public String mujeres(Model model) {			

		return "mujeres";
	}
	@GetMapping("/ninos")   
	public String ninos(Model model) {			

		return "ninos";
	}
	
	@GetMapping("/buscar")
	public String buscarArticulo(@RequestParam String titulo, @ModelAttribute("articulo")Prenda prenda,
			Model model) {
		
		Prenda pren = prendasService.prendaPorTitulo(titulo);
		
		if(titulo != null) {
		    
		    model.addAttribute("arti", pren);  
		}
		
		if(pren == null) {
			model.addAttribute("prenNoEncontrada", "Sin resultados...");  
			
		}
		
		return "prenBuscador";
	}
	
}
