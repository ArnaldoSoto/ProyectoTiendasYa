package com.proyecto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.IPrendasDAO;
import com.proyecto.entities.Prenda;
import com.proyecto.service.IPrendasService;

@Service
public class PrendaServiceImpl implements IPrendasService {
	
	@Autowired
	private IPrendasDAO prendasDao;

	@Override
	public void save(Prenda prenda) {
		prendasDao.save(prenda);	
	}

	@Override
	public List<Prenda> listadoPrendas() {
		return prendasDao.findAll();
	}

	@Override
	public Prenda prendaPorId(Long id) {
		return prendasDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarPrenda(Long id) {
		prendasDao.deleteById(id);
		
	}
	@Override
	public Prenda prendaPorTitulo(String titulo) {
		return prendasDao.findByTitulo(titulo);
	}

}
