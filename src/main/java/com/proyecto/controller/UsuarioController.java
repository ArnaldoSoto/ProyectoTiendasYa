package com.proyecto.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.entities.Rol;
import com.proyecto.entities.Usuario;
import com.proyecto.enums.RolNombre;
import com.proyecto.service.RolService;
import com.proyecto.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/perfil")
    public String verPerfil(Model model) {
        // Obtiene el usuario autenticado actual a través de Spring Security
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        
        // Busca al usuario por su nombre de usuario
        Usuario usuario = usuarioService.getByUsername(username).orElse(null);
        
        model.addAttribute("usuario", usuario);
        return "perfil";
    }
	
	
	private Usuario obtenerUsuarioActual() {
	    // Obtiene el objeto UserDetails que representa al usuario autenticado
	    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    // Suponiendo que el nombre de usuario se almacena en el campo "username"
	    String username = userDetails.getUsername();

	    // Luego, puedes buscar al usuario en la base de datos por su nombre de usuario
	    return usuarioService.getByUsername(username).orElse(null);
	}
	
	@GetMapping("/registro")
	public String registrar() {
		return "registro";
	}

	
	@PostMapping("/save")
	public String saveUser(String username, String password,String nombre,
			String email, String apellido,String tienda, String telefono,
			String banner, String descripcion,String direccion, String ciudad,
			RedirectAttributes redirect, Model model) {
		
		if(usuarioService.existsByUsername(username)) {
			model.addAttribute("usuarioRepetido", "El usuario ya existe");
			return "registro";
		}
		
		Usuario usuario = new Usuario();
		usuario.getUsername();
		usuario.setEmail(email);
		usuario.setPassword(passwordEncoder.encode(password));
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setTienda(tienda);
		usuario.setTelefono(telefono);
		usuario.setBanner(banner);
		usuario.setTienda(descripcion);
		usuario.setDireccion(direccion);
		usuario.setDireccion(ciudad);
		
		Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
		//Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
		
		Set<Rol> roles = new HashSet<Rol>();
		roles.add(rolUser);
		//roles.add(rolAdmin);
		
		usuario.setRoles(roles);
		
		
		usuarioService.guardarUsuario(usuario);
		
		redirect.addFlashAttribute("usuarioRegistrado", "Registro completado, inicie sesión");
		
		
		return "redirect:/login";
		
	}/*	
	@GetMapping("/editar-perfil")
	public String editarPerfil(Model model) {
	    // Obtén el usuario actual, puedes usar la sesión o la autenticación actual
	    // para determinar cuál es el usuario que está editando su perfil.
	    // Supongamos que lo guardas en una variable llamada usuarioActual.
	    Usuario usuarioActual =obtenerUsuarioActual(); // Obtiene el usuario actual 

	    // Agrega el usuario actual al modelo para que se muestren sus datos en el formulario.
	    model.addAttribute("usuario", usuarioActual);

	    return "editar-perfil"; // Nombre de la página de edición de perfil
	}
*/
	@PostMapping("/editar-perfil")
    public String editarPerfil(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirect) {
        try {
            // Actualiza los datos del usuario en la base de datos
            usuarioService.guardarUsuario(usuario);
            redirect.addFlashAttribute("perfilEditado", "Perfil editado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            redirect.addFlashAttribute("error", "Hubo un error al editar el perfil");
        }
        return "redirect:/usuario/perfil";
    }	
	
	/*
	@PostMapping("/editar-perfil")
	public String guardarPerfil(String username, String password,String nombre,
			String email, String apellido,String tienda, String telefono,
			String banner, String descripcion,String direccion, String ciudad, @ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirect, Model model) {
	    try {
	        // Validaciones (puedes usar anotaciones de validación en la entidad Usuario)
	        // Guarda los cambios en el perfil del usuario
	        usuarioService.guardarUsuario(usuario);
	        usuario.setUsername(username);
			usuario.setEmail(email);
			usuario.setPassword(passwordEncoder.encode(password));
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setTienda(tienda);
			usuario.setTelefono(telefono);
			usuario.setBanner(banner);
			usuario.setTienda(descripcion);
			usuario.setDireccion(direccion);
			usuario.setDireccion(ciudad);
	        redirect.addFlashAttribute("perfilEditado", "Perfil editado exitosamente");
	    } catch (Exception e) {
	        e.printStackTrace(); // Manejo de errores
	        redirect.addFlashAttribute("error", "Hubo un error al editar el perfil");
	    }
	    
	    return "redirect:/usuario/editar-perfil";
	}
*/
	/*
	@GetMapping("/mi-panel")
	public String mipanel(Model model) {
		//List<Categoria> listaCategorias = categoriaRepository.findAll();
		//model.addAttribute("listaCategorias", listaCategorias);
		model.addAttribute("usuario", new Usuario());
		return "tienda/mipanel";
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
