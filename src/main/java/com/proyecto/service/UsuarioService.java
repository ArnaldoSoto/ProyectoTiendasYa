package com.proyecto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.dao.IUsuarioDAO;
import com.proyecto.entities.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private IUsuarioDAO usuarioDao;
	
	public List<Usuario> listadoUsuarios(){
		return usuarioDao.findAll();
	}
	
	public Optional<Usuario> getUsuarioById(Long id){
		return usuarioDao.findById(id);
	}
	
	public Optional<Usuario> getByUsername(String username){
		return usuarioDao.findByUsername(username);
	}
	
	public void guardarUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}
	
	public boolean existsById(Long id) {
		return usuarioDao.existsById(id);
	}
	
	public boolean existsByUsername(String username) {
		return usuarioDao.existsByUsername(username);
	}
	/*------------------*/
	public void eliminarUsuario(Long id) {
		usuarioDao.deleteById(id);		
	}
	public Usuario usuarioPorId(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

}
