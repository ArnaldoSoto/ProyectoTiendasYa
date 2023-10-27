package com.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.Subcategoria;

@Repository
public interface ISubcategoriaDAO  extends JpaRepository<Subcategoria, Long>{

}
