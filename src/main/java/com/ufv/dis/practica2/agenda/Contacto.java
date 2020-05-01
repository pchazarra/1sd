package com.ufv.dis.practica2.agenda;

public class Contacto {
	
	private String nombre;
	private String apellidos;
	private String empresa;
	private String telefono;
	private String email;
	private String direccion;
	private String id;
	
	public Contacto(String nombre, String apellidos, String empresa, String telefono, String email, String direccion, String id) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.empresa = empresa;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + "| apellidos=" + apellidos + "| empresa=" + empresa + "| telefono="
				+ telefono + "| email=" + email + "| direccion=" + direccion + "| id=" + id + "]";
	}
	
}
