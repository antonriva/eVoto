package com.antonriva.backendspring.dto;

import java.time.LocalDateTime;

public class ProcesosAbiertosDTO {


    private Long idElector; // Agregado
    private Long idDeInstanciaDeProceso;
    private LocalDateTime fechaHoraDeInicio;
    private LocalDateTime fechaHoraDeFin;
    private String descripcionNivel;
    private String descripcionProceso;
    private String entidadFederativa;
    private String municipio;
    private String localidad;

    public ProcesosAbiertosDTO(
            Long idElector, // Agregar este parámetro
            Long idDeInstanciaDeProceso,
            LocalDateTime fechaHoraDeInicio,
            LocalDateTime fechaHoraDeFin,
            String descripcionNivel,
            String descripcionProceso,
            String entidadFederativa,
            String municipio,
            String localidad
    ) {
        this.idElector = idElector; // Asignar aquí
        this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
        this.fechaHoraDeInicio = fechaHoraDeInicio;
        this.fechaHoraDeFin = fechaHoraDeFin;
        this.descripcionNivel = descripcionNivel;
        this.descripcionProceso = descripcionProceso;
        this.entidadFederativa = entidadFederativa;
        this.municipio = municipio;
        this.localidad = localidad;
    }

	public Long getIdElector() {
		return idElector;
	}

	public void setIdElector(Long idElector) {
		this.idElector = idElector;
	}

	public Long getIdDeInstanciaDeProceso() {
		return idDeInstanciaDeProceso;
	}

	public void setIdDeInstanciaDeProceso(Long idDeInstanciaDeProceso) {
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
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

    
    
    
}
