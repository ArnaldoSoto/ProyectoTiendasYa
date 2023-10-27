package com.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.Categoria;
@Repository
public interface ICategoriasDAO extends JpaRepository<Categoria, Long> {

}
