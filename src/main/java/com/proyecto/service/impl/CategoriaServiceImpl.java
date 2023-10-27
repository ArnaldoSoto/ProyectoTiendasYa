package com.proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.ICategoriasDAO;
import com.proyecto.entities.Categoria;
import com.proyecto.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	@Autowired
	private ICategoriasDAO categoriassDao;

	@Override
	public Categoria categoriaPorId(Long catid) {
		return categoriassDao.findById(catid).orElse(null) ;
	}

}
