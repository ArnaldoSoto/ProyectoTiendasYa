package com.proyecto.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.entities.Usuario;
import com.proyecto.service.UsuarioService;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
	
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.getByUsername(username)
				.orElseThrow( () -> new UsernameNotFoundException(username));
		
		return UsuarioPrincipal.build(usuario);
		
	
	}

}
