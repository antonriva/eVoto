package com.antonriva.backendspring.dto;

import java.time.LocalDateTime;

public class CandidaturaDetalleDTO {
	
    private Long idCandidatura;
    private Long idDeInstanciaDeProceso;
    private String descripcionNivel;
    private String descripcionProceso;
    private String denominacionPartido;
    private Integer votos;
    private LocalDateTime fechaHoraDeInicio;
    private LocalDateTime fechaHoraDeFin;
    private ProcesoLugarDTO lugar;

    public CandidaturaDetalleDTO(Long idCandidatura, Long idDeInstanciaDeProceso, String descripcionNivel,
                                 String descripcionProceso, String denominacionPartido, Integer votos,
                                 LocalDateTime fechaHoraDeInicio, LocalDateTime fechaHoraDeFin,
                                 ProcesoLugarDTO lugar) {
        this.idCandidatura = idCandidatura;
        this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
        this.descripcionNivel = descripcionNivel;
        this.descripcionProceso = descripcionProceso;
        this.denominacionPartido = denominacionPartido;
        this.votos = votos;
        this.fechaHoraDeInicio = fechaHoraDeInicio;
        this.fechaHoraDeFin = fechaHoraDeFin;
        this.lugar = lugar;
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

	public ProcesoLugarDTO getLugar() {
		return lugar;
	}

	public void setLugar(ProcesoLugarDTO lugar) {
		this.lugar = lugar;
	}
    
    
    

}
