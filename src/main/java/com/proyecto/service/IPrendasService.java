package com.proyecto.service;

import java.util.List;

import com.proyecto.entities.Prenda;

public interface IPrendasService {
	void save(Prenda articulo);
	List<Prenda> listadoPrendas();
	Prenda prendaPorId(Long id);
	void eliminarPrenda(Long id);
	Prenda prendaPorTitulo(String titulo);	
}
