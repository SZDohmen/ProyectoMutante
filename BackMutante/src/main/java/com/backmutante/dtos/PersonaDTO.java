package com.backmutante.dtos;

import java.io.Serializable;

public class PersonaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String apellido;
	private int edad;
	private String [] adn;
	private boolean estado;
	
	//--- Constructors ---
	public PersonaDTO() {}
		
	public PersonaDTO(Long id, String nombre, String apellido, int edad, String[] adn, boolean estado) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.adn = adn;
		this.estado = estado;
	}
	
	//--- Setters & Getters ---
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

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String[] getAdn() {
		return adn;
	}
	public void setAdn(String[] adn) {
		this.adn = adn;
	}

	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
		
	
}
