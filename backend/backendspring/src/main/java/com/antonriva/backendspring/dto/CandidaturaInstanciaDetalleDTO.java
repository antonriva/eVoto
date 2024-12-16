package com.antonriva.backendspring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CandidaturaInstanciaDetalleDTO {
	
    private Long idCandidatura;//Este se obtiene de candidatura
    private String denominacionPartido; //Esta se accede a traves del idDePartido que se tiene en candidatura
    private Integer votos; //Atributo de candidatura
    
    private LocalDateTime fechaHoraDeInicio;//Atributo de la tabla de relacion electorcandidadtura (esta tiene embedded id)
    private LocalDateTime fechaHoraDeFin; //Atributo de la tabla de relacion electorcandidadtura (esta tiene embedded id)
    
    private Long idDePersona; // Una candidatura esta vinculada a elector a traves de una tabla electorCandidatura, y elector conecta con persona
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFin;
    
    //AQUI VAMOS A OBTENER DATOS DEL DOMICILIO DE RESIDENCIA SOLAMENTE
    private String entidadFederativaDescripcion;
    private String municipioDescripcion;
    private String localidadDescripcion;
    
	public CandidaturaInstanciaDetalleDTO(Long idCandidatura, String denominacionPartido, Integer votos,
			LocalDateTime fechaHoraDeInicio, LocalDateTime fechaHoraDeFin, Long idDePersona, String nombre,
			String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeNacimiento, LocalDate fechaDeFin,
			String entidadFederativaDescripcion, String municipioDescripcion, String localidadDescripcion) {
		this.idCandidatura = idCandidatura;
		this.denominacionPartido = denominacionPartido;
		this.votos = votos;
		this.fechaHoraDeInicio = fechaHoraDeInicio;
		this.fechaHoraDeFin = fechaHoraDeFin;
		this.idDePersona = idDePersona;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.fechaDeFin = fechaDeFin;
		this.entidadFederativaDescripcion = entidadFederativaDescripcion;
		this.municipioDescripcion = municipioDescripcion;
		this.localidadDescripcion = localidadDescripcion;
	}

	public Long getIdCandidatura() {
		return idCandidatura;
	}

	public void setIdCandidatura(Long idCandidatura) {
		this.idCandidatura = idCandidatura;
	}

	public String getDenominacionPartido() {
		return denominacionPartido;
	}

	public void setDenominacionPartido(String denominacionPartido) {
		this.denominacionPartido = denominacionPartido;
	}

	public Integer getVotos() {
		return votos;
	}

	public void setVotos(Integer votos) {
		this.votos = votos;
	}

	public LocalDateTime getFechaHoraDeInicio() {
		return fechaHoraDeInicio;
	}

	public void setFechaHoraDeInicio(LocalDateTime fechaHoraDeInicio) {
		this.fechaHoraDeInicio = fechaHoraDeInicio;
	}

	public LocalDateTime getFechaHoraDeFin() {
		return fechaHoraDeFin;
	}

	public void setFechaHoraDeFin(LocalDateTime fechaHoraDeFin) {
		this.fechaHoraDeFin = fechaHoraDeFin;
	}

	public Long getIdDePersona() {
		return idDePersona;
	}

	public void setIdDePersona(Long idDePersona) {
		this.idDePersona = idDePersona;
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

	public String getEntidadFederativaDescripcion() {
		return entidadFederativaDescripcion;
	}

	public void setEntidadFederativaDescripcion(String entidadFederativaDescripcion) {
		this.entidadFederativaDescripcion = entidadFederativaDescripcion;
	}

	public String getMunicipioDescripcion() {
		return municipioDescripcion;
	}

	public void setMunicipioDescripcion(String municipioDescripcion) {
		this.municipioDescripcion = municipioDescripcion;
	}

	public String getLocalidadDescripcion() {
		return localidadDescripcion;
	}

	public void setLocalidadDescripcion(String localidadDescripcion) {
		this.localidadDescripcion = localidadDescripcion;
	}
    
	
    
    
    
}
