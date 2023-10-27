package com.proyecto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.ISubcategoriaDAO;
import com.proyecto.entities.Subcategoria;
import com.proyecto.service.ISubcategoriaService;
@Service
public class SubcategoriaServiceImpl implements ISubcategoriaService{
	@Autowired
	private ISubcategoriaDAO categoriaDao;
/*	@Override
	public List<Subcategoria> listadoSubcategoria() {
		return null;
	}
*/
	@Override
	public Subcategoria subcategoriaPorId(Long id) {
		return categoriaDao.findById(id).orElse(null) ;
	}



}
