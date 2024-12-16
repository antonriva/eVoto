package com.antonriva.backendspring.dto;

import java.time.LocalDateTime;

public class InstanciaDeProcesoDetalleDTO {
	
	
	private Long idDeInstanciaDeProceso;
	
    private String descripcionNivel; //ESTA DESCRIPCION SE ENCUENTRA EN LA TABLA DE NIVEL, instancia de proceso tiene un id asociado a esta tabla
    private String descripcionProceso; //ESTA DESCRIPCION SE ENCUENTRA EN LA TABLA DE PROCESO, instancia de proceso tiene un id asociado a esta tabla
	
	
    private String entidadFederativa; // instancia de proceso, que tiene un id a procesolugar, que almacena datos de la entidadfederativa, municipio y localidad
    private String municipio; //  que tiene un id a procesolugar, que almacena datos de la entidadfederativa, municipio y localidad
    private String localidad; // que tiene un id a procesolugar, que almacena datos de la entidadfederativa, municipio y localidad
    
    private Long votoTotal; // en este se debe hacer un count de todos los renglos en voto que tengan como idDeInstancia de proceso el idDeInstancia que tenemos como base
    
    private LocalDateTime fechaHoraDeInicio;
    private LocalDateTime fechaHoraDeFin;
    private Integer ganadoresNum;
    
	public InstanciaDeProcesoDetalleDTO(Long idDeInstanciaDeProceso, 
			String descripcionNivel, 
			String descripcionProceso,
			String entidadFederativa, 
			String municipio, 
			String localidad, 
			Long votoTotal,
			LocalDateTime fechaHoraDeInicio, 
			LocalDateTime fechaHoraDeFin, 
			Integer ganadoresNum) {
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
		this.descripcionNivel = descripcionNivel;
		this.descripcionProceso = descripcionProceso;
		this.entidadFederativa = entidadFederativa;
		this.municipio = municipio;
		this.localidad = localidad;
		this.votoTotal = votoTotal;
		this.fechaHoraDeInicio = fechaHoraDeInicio;
		this.fechaHoraDeFin = fechaHoraDeFin;
		this.ganadoresNum = ganadoresNum;
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

	public Long getVotoTotal() {
		return votoTotal;
	}

	public void setVotoTotal(Long votoTotal) {
		this.votoTotal = votoTotal;
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

	public Integer getGanadoresNum() {
		return ganadoresNum;
	}

	public void setGanadoresNum(Integer ganadoresNum) {
		this.ganadoresNum = ganadoresNum;
	}
	
	
	
    

}
