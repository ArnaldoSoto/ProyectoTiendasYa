package com.proyecto.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subcategorias")
public class Subcategoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;


    
    
	public Subcategoria(Long id, String nombre, Categoria categoria) {

		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Subcategoria [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + "]";
	}


	public Subcategoria() {
	
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	
	
	
}