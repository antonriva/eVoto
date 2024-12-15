package com.antonriva.backendspring.dto;

import java.time.LocalDate;

import javax.validation.constraints.Pattern;

public class ElectorEditarDTO {
	
    private Long idElector;
    private Long idPersona;

    private Long entidadFederativaId;
    private Long municipioId;
    private Long localidadId;
    private Long coloniaId;
    private Long codigoPostalId;

    @Pattern(regexp = "^[A-Z]+( [A-Z]+)*$", message = "La calle debe contener solo letras mayúsculas y espacios, sin números ni caracteres especiales.")
    private String calle;
    
    private Integer numeroExterior;
    private Integer numeroInterior;
    private LocalDate fechaDeInicio;

    private LocalDate fechaDeInicioElector;

    public ElectorEditarDTO(Long idElector, Long idPersona, Long entidadFederativaId, Long municipioId,
                            Long localidadId, Long coloniaId, Long codigoPostalId, String calle,
                            Integer numeroExterior, Integer numeroInterior, LocalDate fechaDeInicio, LocalDate fechaDeInicioElector) {
        this.idElector = idElector;
        this.idPersona = idPersona;
        this.entidadFederativaId = entidadFederativaId;
        this.municipioId = municipioId;
        this.localidadId = localidadId;
        this.coloniaId = coloniaId;
        this.codigoPostalId = codigoPostalId;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeInicioElector = fechaDeInicio;
    }

	public Long getIdElector() {
		return idElector;
	}

	public void setIdElector(Long idElector) {
		this.idElector = idElector;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Long getEntidadFederativaId() {
		return entidadFederativaId;
	}

	public void setEntidadFederativaId(Long entidadFederativaId) {
		this.entidadFederativaId = entidadFederativaId;
	}

	public Long getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(Long municipioId) {
		this.municipioId = municipioId;
	}

	public Long getLocalidadId() {
		return localidadId;
	}

	public void setLocalidadId(Long localidadId) {
		this.localidadId = localidadId;
	}

	public Long getColoniaId() {
		return coloniaId;
	}

	public void setColoniaId(Long coloniaId) {
		this.coloniaId = coloniaId;
	}

	public Long getCodigoPostalId() {
		return codigoPostalId;
	}

	public void setCodigoPostalId(Long codigoPostalId) {
		this.codigoPostalId = codigoPostalId;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
        this.calle = normalizeName(calle);
	}

	public Integer getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(Integer numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	public Integer getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(Integer numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public LocalDate getFechaDeInicioElector() {
		return fechaDeInicioElector;
	}

	public void setFechaDeInicioElector(LocalDate fechaDeInicioElector) {
		this.fechaDeInicioElector = fechaDeInicioElector;
	}
    
	
    // Métodos auxiliares para normalizar los campos
    private String normalizeName(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().replaceAll("\\s{2,}", " "); // Elimina espacios extra entre palabras
    }

	
    private String normalizeSingleWord(String value) {
        if (value == null) {
            return null;
        }
        return value.trim(); // Solo elimina espacios al inicio y final
    }
    

}
