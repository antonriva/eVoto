package com.antonriva.backendspring.dto;

import java.time.LocalDateTime;

public class InstanciaEditarDTO {
	
	///ESTAS SON DE INSTANCIADEPROCESO
	private Long idDeNivel;
	private Long idDeProceso;
	private LocalDateTime fechaHoraDeInicio;
	private LocalDateTime fechaHoraDeFin;
	private Integer ganadoresNum;
	
	/// ESTAS SON DE LA TABLA LUGAR PROCESO
	private Long idDeEntidadFederativa;
	private Long idDeMunicipio;
	private Long idDeLocalidad;
	
	
	public InstanciaEditarDTO(Long idDeNivel, Long idDeProceso, LocalDateTime fechaHoraDeInicio,
			LocalDateTime fechaHoraDeFin, Integer ganadoresNum, Long idDeEntidadFederativa, Long idDeMunicipio,
			Long idDeLocalidad) {
		super();
		this.idDeNivel = idDeNivel;
		this.idDeProceso = idDeProceso;
		this.fechaHoraDeInicio = fechaHoraDeInicio;
		this.fechaHoraDeFin = fechaHoraDeFin;
		this.ganadoresNum = ganadoresNum;
		this.idDeEntidadFederativa = idDeEntidadFederativa;
		this.idDeMunicipio = idDeMunicipio;
		this.idDeLocalidad = idDeLocalidad;
	}
	public Long getIdDeNivel() {
		return idDeNivel;
	}
	public void setIdDeNivel(Long idDeNivel) {
		this.idDeNivel = idDeNivel;
	}
	public Long getIdDeProceso() {
		return idDeProceso;
	}
	public void setIdDeProceso(Long idDeProceso) {
		this.idDeProceso = idDeProceso;
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
	public Long getIdDeEntidadFederativa() {
		return idDeEntidadFederativa;
	}
	public void setIdDeEntidadFederativa(Long idDeEntidadFederativa) {
		this.idDeEntidadFederativa = idDeEntidadFederativa;
	}
	public Long getIdDeMunicipio() {
		return idDeMunicipio;
	}
	public void setIdDeMunicipio(Long idDeMunicipio) {
		this.idDeMunicipio = idDeMunicipio;
	}
	public Long getIdDeLocalidad() {
		return idDeLocalidad;
	}
	public void setIdDeLocalidad(Long idDeLocalidad) {
		this.idDeLocalidad = idDeLocalidad;
	}
	
	
	
	

}
