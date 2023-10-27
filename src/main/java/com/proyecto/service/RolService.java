package com.proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.IRolDAO;
import com.proyecto.entities.Rol;
import com.proyecto.enums.RolNombre;

@Service
public class RolService {
	
	@Autowired
	private IRolDAO rolDao;
	
	public void guardarRol(Rol rol) {
		rolDao.save(rol);
	}
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolDao.findByRolNombre(rolNombre);
	}
	
	public boolean existsByRolNombre(RolNombre rolNombre) {
		return rolDao.existsByRolNombre(rolNombre);
	}
	
	

}
