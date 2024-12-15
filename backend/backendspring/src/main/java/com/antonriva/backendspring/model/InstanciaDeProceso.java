package com.antonriva.backendspring.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "instanciaDeProceso")
public class InstanciaDeProceso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddeproceso", nullable = false)
	@JsonIgnore
    private Proceso proceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddenivel", nullable = false)
	@JsonIgnore
    private Nivel nivel;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddeprocesolugar", nullable = false)
	@JsonIgnore
    private ProcesoLugar procesoLugar;

    @Column(name = "ganadoresnum", nullable = false)
    private Integer ganadoresNum;
    
    //Columna normal
    @Column(name = "fechahoradeinicio", nullable = false)
    private LocalDateTime fechaHoraDeInicio;

    @Column(name = "fechahoradefin", nullable = false)
    private LocalDateTime fechaHoraDeFin;
    
    public InstanciaDeProceso() {
    	
    }

	public InstanciaDeProceso(Long id, Proceso proceso, Nivel nivel, Integer ganadoresNum,
			LocalDateTime fechaHoraDeInicio, LocalDateTime fechaHoraDeFin, ProcesoLugar procesoLugar) {
		this.id = id;
		this.proceso = proceso;
		this.nivel = nivel;
		this.ganadoresNum = ganadoresNum;
		this.fechaHoraDeInicio = fechaHoraDeInicio;
		this.fechaHoraDeFin = fechaHoraDeFin;
		this.procesoLugar = procesoLugar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Integer getGanadoresNum() {
		return ganadoresNum;
	}

	public void setGanadoresNum(Integer ganadoresNum) {
		this.ganadoresNum = ganadoresNum;
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
	
	

	public ProcesoLugar getProcesoLugar() {
		return procesoLugar;
	}

	public void setProcesoLugar(ProcesoLugar procesoLugar) {
		this.procesoLugar = procesoLugar;
	}

	@Override
	public String toString() {
		return "InstanciaDeProceso [id=" + id + ", ganadoresNum=" + ganadoresNum + ", fechaHoraDeInicio="
				+ fechaHoraDeInicio + ", fechaHoraDeFin=" + fechaHoraDeFin + "]";
	}
	 

}
