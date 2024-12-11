package com.antonriva.backendspring.model;


import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; //Antes estaba en javax.persistence pero ahora se encuentra en jakarta, otra dependencia

@Entity
@Table(name= "persona")
public class Persona {
	
	//Aqui tengo que revisar la privacidad de los atributos
	
	//Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//OneToMany
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore // Ignorar esta relación al serializar
	private List<PersonaDomicilio> personaDomicilio;
	
	//Columnas normales
	@Column(name="nombre", nullable = false)
	private String nombre;

	@Column(name="apellidopaterno", nullable = false)
	private String apellidoPaterno;

	@Column(name="apellidomaterno", nullable = false)
	private String apellidoMaterno;

	@Column(name="fechadenacimiento", nullable = false)
	private LocalDate fechaDeNacimiento;

	@Column(name="fechadefin", nullable = true)
	private LocalDate fechaDeFin;

	
	public Persona() {
		
	}
	
	public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeNacimiento) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PersonaDomicilio> getPersonaDomicilio() {
		return personaDomicilio;
	}

	public void setPersonaDomicilio(List<PersonaDomicilio> personaDomicilio) {
		this.personaDomicilio = personaDomicilio;
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
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", fechaDeNacimiento=" + fechaDeNacimiento + ", fechaDeFin="
				+ fechaDeFin + "]";
	}
	
	

}
