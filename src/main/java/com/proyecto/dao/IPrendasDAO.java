package com.proyecto.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.Prenda;

@Repository
public interface IPrendasDAO extends JpaRepository<Prenda, Long> {
	Prenda findByTitulo(String titulo);
	//List<Prenda> findByCatid(Long id);
}
