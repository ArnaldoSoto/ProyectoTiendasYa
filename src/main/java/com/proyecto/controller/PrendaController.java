package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.entities.Prenda;
import com.proyecto.service.IPrendasService;
@Controller
@RequestMapping("/prendas")
public class PrendaController {
	@Autowired
	private IPrendasService prendasService;
	
	@GetMapping("/ver-prenda/{id}")
	public String pren(@PathVariable Long id, Model model, Authentication auth) {
		
		if(auth != null) {
			String username = auth.getName();
			model.addAttribute("username",username);
			//System.out.println(username);
		}
		
		
		
		Prenda prenda = prendasService.prendaPorId(id);
		//Categoria categoria = categoriassService.categoriaPorId(catid);
		model.addAttribute("prenda", prenda);
		//model.addAttribute("catgoria", categoria);
		
		return "ventas/verPrenda";
	}
	

}
