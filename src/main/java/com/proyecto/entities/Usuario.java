package com.proyecto.entities;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	private String email;
	@NotEmpty(message = "El campo nombre contraseña no puede estar vacío")
	private String password;
	private String nombre;
	private String apellido;
	private String tienda;
	private String telefono;
	//private int dni;
	//private int edad;	
	
	private String banner;
	private String descripcion;
	private String direccion;
	private String ciudad;
	 
	@OneToMany(mappedBy = "usuario")
    private List<Prenda> prendas;
	
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_rol", joinColumns = @JoinColumn(name="usuario_id"), 
	inverseJoinColumns =  @JoinColumn(name="rol_id"))
	private Set<Rol> roles = new HashSet<Rol>();

	public Usuario(Long id, String username, String email,
			@NotEmpty(message = "El campo nombre contraseña no puede estar vacío") String password, String nombre,
			String apellido, String tienda, String telefono, String banner, String descripcion, String direccion,
			String ciudad, Set<Rol> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tienda = tienda;
		this.telefono = telefono;
		this.banner = banner;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.roles = roles;
	}
	//Constructor vacio
	public Usuario() {}
	//get and set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTienda() {
		return tienda;
	}
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	


}
