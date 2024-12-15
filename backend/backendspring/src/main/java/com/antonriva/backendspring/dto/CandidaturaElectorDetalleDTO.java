package com.antonriva.backendspring.dto;

import java.time.LocalDateTime;

public class CandidaturaElectorDetalleDTO {
	
    private Long idCandidatura; //Este se obtiene de candidatura
    private Long idDeInstanciaDeProceso; //en candidatura tenemos el idDeInstancia deproceso
    private String descripcionNivel; //ESTA DESCRIPCION SE ENCUENTRA EN LA TABLA DE NIVEL, instancia de proceso tiene un id asociado a esta tabla
    private String descripcionProceso; //ESTA DESCRIPCION SE ENCUENTRA EN LA TABLA DE PROCESO, instancia de proceso tiene un id asociado a esta tabla
    private String denominacionPartido;//ESTA DESCRIPCION SE ENCUENTRA EN LA TABLA DE Partido, candidatura tiene un id asociado a esta tabla
    private Integer votos; //Atributo de candidatura
    private LocalDateTime fechaHoraDeInicio;//Atributo de la tabla de relacion electorcandidadtura (esta tiene embedded id)
    private LocalDateTime fechaHoraDeFin; //Atributo de la tabla de relacion electorcandidadtura (esta tiene embedded id)
    private String entidadFederativa; // se accede a traves de instancia de proceso, que tiene un id a procesolugar, que almacena datos de la entidadfederativa, municipio y localidad
    private String municipio; // se accede a traves de instancia de proceso, que tiene un id a procesolugar, que almacena datos de la entidadfederativa, municipio y localidad
    private String localidad; // se accede a traves de instancia de proceso, que tiene un id a procesolugar, que almacena datos de la entidadfederativa, municipio y localidad
	
    public CandidaturaElectorDetalleDTO(Long idCandidatura, Long idDeInstanciaDeProceso, String descripcionNivel,
			String descripcionProceso, String denominacionPartido, Integer votos, LocalDateTime fechaHoraDeInicio,
			LocalDateTime fechaHoraDeFin, String entidadFederativa, String municipio, String localidad) {
		this.idCandidatura = idCandidatura;
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
		this.descripcionNivel = descripcionNivel;
		this.descripcionProceso = descripcionProceso;
		this.denominacionPartido = denominacionPartido;
		this.votos = votos;
		this.fechaHoraDeInicio = fechaHoraDeInicio;
		this.fechaHoraDeFin = fechaHoraDeFin;
		this.entidadFederativa = entidadFederativa;
		this.municipio = municipio;
		this.localidad = localidad;
	}

	public Long getIdCandidatura() {
		return idCandidatura;
	}

	public void setIdCandidatura(Long idCandidatura) {
		this.idCandidatura = idCandidatura;
	}

	public Long getIdDeInstanciaDeProceso() {
		return idDeInstanciaDeProceso;
	}

	public void setIdDeInstanciaDeProceso(Long idDeInstanciaDeProceso) {
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
	}

	public String getDescripcionNivel() {
		return descripcionNivel;
	}

	public void setDescripcionNivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}

	public String getDescripcionProceso() {
		return descripcionProceso;
	}

	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
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

	public String getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
    
    

    
}
