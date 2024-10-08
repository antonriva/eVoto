package com.antonriva.backendspring.model;


import java.time.LocalDate;

//Antes estaba en javax.persistence pero ahora se encuentra en jakarta, otra dependencia

import jakarta.persistence.*;

@Entity
@Table(name= "Persona")
public class Persona {
	
	//Aqui tengo que revisar la privacidad de los atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="NOMBRE")
	String nombre;
	@Column(name="APELLIDOPATERNO")
	String apellidoPaterno;
	@Column(name="APELLIDOMATERNO")
	String apellidoMaterno;
	@Column(name="FECHADENACIMIENTO")
	LocalDate fechaDeNacimiento;
	@Column(name="FECHADEFIN")
	LocalDate fechaDeFin;
	
	public Persona() {
		
	}
	
	public Persona(int id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeNacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	
	public Persona(int id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeNacimiento, LocalDate fechaDeFin) {
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.fechaDeFin = fechaDeFin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public LocalDate getFechaDeFin() {
		return fechaDeFin;
	}

	public void setFechaDeFin(LocalDate fechaDeFin) {
		this.fechaDeFin = fechaDeFin;
	}
	
	@Override
	public String toString() {
		return "Persona{" +
				"id =" + id +
				", nombre=" + nombre + '\'' +
				", apellidoPaterno=" + apellidoPaterno + '\'' +
				", apellidoMaterno=" + apellidoMaterno + '\'' +
				", fechaDeNacimiento=" + fechaDeNacimiento + '\'' + 
				"}";
	}

}
