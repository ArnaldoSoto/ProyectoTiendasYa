package com.proyecto.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.Rol;
import com.proyecto.enums.RolNombre;

@Repository
public interface IRolDAO extends JpaRepository<Rol, Integer> {
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
	boolean existsByRolNombre(RolNombre rolNombre);

}
