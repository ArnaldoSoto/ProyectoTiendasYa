package com.proyecto.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyecto.service.UsuarioService;
import com.proyecto.entities.Prenda;
import com.proyecto.dao.ICategoriasDAO;
import com.proyecto.dao.ISubcategoriaDAO;
import com.proyecto.entities.Categoria;
import com.proyecto.entities.Subcategoria;
import com.proyecto.entities.Usuario;
import com.proyecto.service.IPrendasService;

@Controller
@RequestMapping("/tienda")
public class TiendaController {
	
	@Autowired
	private IPrendasService prendaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	ISubcategoriaDAO subcategoriaRepository;
	
	/*
	private Usuario obtenerUsuarioActual() {
	    // Obtiene el objeto UserDetails que representa al usuario autenticado
	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    // Suponiendo que el nombre de usuario se almacena en el campo "username"
	    String username = userDetails.getUsername();

	    // Luego, puedes buscar al usuario en la base de datos por su nombre de usuario
	    return usuarioService.getByUsername(username).orElse(null);
	}
	*/
	@GetMapping("/mi-panel")   
	public String mipanel() {			

		return "tienda/mipanel";
	}/*
	
	@GetMapping("/editar-usuario")
	public String editarUsuarioForm(Model model) {
	    // Obtén el usuario actual desde el servicio o sesión
	    Usuario usuario = obtenerUsuarioActual(); // Implementa este método para obtener el usuario actual
	    model.addAttribute("usuario", usuario);
	    return "tienda/mi-panel"; // Devuelve la página de edición
	}


	@PutMapping("/editar-usuario")
	public String editarUsuario(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirect) {
	    try {
	        // Actualiza el usuario en la base de datos
	        usuarioService.guardarUsuario(usuario);
	        redirect.addFlashAttribute("userEditada", "Usuario editado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Maneja cualquier error que pueda ocurrir durante la actualización
	        redirect.addFlashAttribute("userEditada", "Error al editar el usuario");
	    }
	    return "redirect:/tienda/mi-panel";
	}  */
	
	@GetMapping("/pren-form")
	public String prenForm(Model model) {
		List<Subcategoria> listaSubcategorias = subcategoriaRepository.findAll();
		model.addAttribute("listaSubcategoria", listaSubcategorias);
		model.addAttribute("prenda", new Prenda());
		return "tienda/prenForm";
	}
	// guarda articulos redirigi a actores formulario
	/*
	@PostMapping("/save-pren")
	public String savePren(@RequestParam(name="file", required = false)MultipartFile portada, Prenda prenda, 
			RedirectAttributes redirect) {
		
		if(!portada.isEmpty()) {
			String ruta = "C://Temp//uploads";
			String nombreUnico = UUID.randomUUID()+" "+ portada.getOriginalFilename();
			
			
			try {
				byte[] bytes = portada.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
				Files.write(rutaAbsoluta, bytes);
				prenda.setPortada(nombreUnico);
				
				prendaService.save(prenda);
				redirect.addFlashAttribute("prenGuardada", "Prenda guardada");
				//redirect.addFlashAttribute("artiParaActor", articulo);
				
			}catch (Exception e) {
				e.getCause().getMessage();
				
			}
			
		}
		
		return "redirect:/tienda/pren-form";
	}
	*/
	   @PostMapping("/save-pren")
	    public String savePren(@RequestParam(name="file", required = false) MultipartFile portada, Prenda prenda, 
	            RedirectAttributes redirect) {

	        if (!portada.isEmpty()) {
	            String ruta = "C://Temp//uploads";
	            String nombreUnico = UUID.randomUUID() + " " + portada.getOriginalFilename();

	            try {
	                byte[] bytes = portada.getBytes();
	                Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
	                Files.write(rutaAbsoluta, bytes);

	                // Obtén el usuario actualmente autenticado
	                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	                String username = authentication.getName(); // Suponiendo que el nombre de usuario se usa para la autenticación

	                // Busca al usuario por su nombre de usuario
	                Usuario usuario = usuarioService.getByUsername(username).orElse(null);

	                if (usuario != null) {
	                    // Asigna el usuario a la prenda
	                    prenda.setUsuario(usuario);
	                    
	                    // Asigna la portada
	                    prenda.setPortada(nombreUnico);

	                    // Guarda la prenda
	                    prendaService.save(prenda);

	                    redirect.addFlashAttribute("prenGuardada", "Prenda guardada");
	                } else {
	                    redirect.addFlashAttribute("error", "No se pudo encontrar el usuario autenticado");
	                }
	            } catch (Exception e) {
	                e.getCause().getMessage();
	            }
	        }

	        return "redirect:/tienda/pren-form";
	    }
	
	//GESTION DE Prendas
	@GetMapping("/gestion-prendas")
	public String listadoPrenda(Model model) {
		model.addAttribute("prendas",prendaService.listadoPrendas());
		return "tienda/gestionPrendas";
	}

	@GetMapping("/eliminar-prenda/{id}")
	public String eliminarPrenda(@PathVariable Long id, RedirectAttributes redirect) {
		prendaService.eliminarPrenda(id);
		redirect.addFlashAttribute("prenEliminada", "Prenda eliminada");
		return "redirect:/tienda/gestion-tienda";
	}

	//EDITAR prenda
	@GetMapping("/editar-form/{id}")
	public String editarFormulario(@PathVariable Long id, Model model) {
		List<Subcategoria> listaSubcategorias = subcategoriaRepository.findAll();
		model.addAttribute("listaSubcategorias", listaSubcategorias);
		Prenda prenda = null;
		
		if(id > 0) {
			prenda = prendaService.prendaPorId(id);
			
			model.addAttribute("articulo", prenda);
		}
		
		return "tienda/editarPrenda";
	}
	

	//EDITAR Prendas
	@PostMapping("/editar-prenda")
	public String editarPrenda(@RequestParam(name="file") MultipartFile imagenPortada, Prenda pren, 
			RedirectAttributes redirect, @ModelAttribute("prenda") Prenda prenda, Model model ) {
		
		if(!imagenPortada.isEmpty()) {
			String ruta = "C://Temp//uploads";
			String nombreUnico = UUID.randomUUID()+" "+ imagenPortada.getOriginalFilename();
			
			
			try {
				byte[] bytes = imagenPortada.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
				Files.write(rutaAbsoluta, bytes);
				prenda.setPortada(nombreUnico);
				
				prendaService.save(prenda);
				redirect.addFlashAttribute("prenEditada", "Prenda editada");
				
				
			}catch (Exception e) {
				e.getCause().getMessage();
				
			}	
			
		}
		
		
		return "redirect:/tienda/gestion-prendas";
	}

}
