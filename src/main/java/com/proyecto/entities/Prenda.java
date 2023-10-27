package com.proyecto.entities;

import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="prendas")
public class Prenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descripcion;
	private String portada;
	private double precio;	
	private int stock;
	
	
	@ManyToOne
    @JoinColumn(name = "subcategoria_id")
    private Subcategoria subcategoria;
	
	@ManyToOne
    @JoinColumn(name = "tienda_id")
    private Usuario usuario;
	/*	
	@ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;
	
	*/
	//Constructor
	public Prenda(Long id, String titulo, String descripcion, String portada, double precio, int stock,
			Subcategoria subcategoria, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.portada = portada;
		this.precio = precio;
		this.stock = stock;
		this.subcategoria = subcategoria;
		this.usuario = usuario;
	}
	public Prenda() {}

	
	
	
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}



	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public boolean sinExistencia() {
        return this.stock <= 0;
    }


	public void restarExistencia(int stock) {
        this.stock -= stock;
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}
/*
	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}
/*
	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}
*/
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	



}
