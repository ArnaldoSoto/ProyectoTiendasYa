package com.proyecto.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);

}
