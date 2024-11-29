package com.antonriva.backendspring.model;


import java.time.LocalDate;
import java.util.List;

//Antes estaba en javax.persistence pero ahora se encuentra en jakarta, otra dependencia

import jakarta.persistence.*;

@Entity
@Table(name= "Persona")
public class Persona {
	
	//Aqui tengo que revisar la privacidad de los atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PersonaDomicilio> personaDomicilios;
	
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="APELLIDOPATERNO")
	private String apellidoPaterno;
	@Column(name="APELLIDOMATERNO")
	private String apellidoMaterno;
	@Column(name="FECHADENACIMIENTO")
	private LocalDate fechaDeNacimiento;
	@Column(name="FECHADEFIN")
	private LocalDate fechaDeFin;
	
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Usa IDENTITY para delegar la generación a la base de datos
	public int getId() {
		return id;
	}
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<PersonaDomicilio> personaDomicilio;

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
	
    public List<PersonaDomicilio> getPersonaDomicilios() {
        return personaDomicilios;
    }

    public void setPersonaDomicilios(List<PersonaDomicilio> personaDomicilios) {
        this.personaDomicilios = personaDomicilios;
    }
	
	@Override
	public String toString() {
		return "Persona{" +
				"id =" + id +
				", nombre=" + nombre + '\'' +
				", apellidoPaterno=" + apellidoPaterno + '\'' +
				", apellidoMaterno=" + apellidoMaterno + '\'' +
				", fechaDeNacimiento=" + fechaDeNacimiento + '\'' + 
				", fechaDeFin=" + fechaDeFin + '\'' +
				"}";
	}
	
	

}
