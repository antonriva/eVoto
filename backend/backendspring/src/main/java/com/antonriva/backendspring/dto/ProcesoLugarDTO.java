package com.antonriva.backendspring.dto;

public class ProcesoLugarDTO {
	
    private String descripcionMunicipio;
    private String descripcionEntidad;
    private String descripcionLocalidad;

    public ProcesoLugarDTO(String descripcionMunicipio, String descripcionEntidad, String descripcionLocalidad) {
        this.descripcionMunicipio = descripcionMunicipio;
        this.descripcionEntidad = descripcionEntidad;
        this.descripcionLocalidad = descripcionLocalidad;
    }

	public String getDescripcionMunicipio() {
		return descripcionMunicipio;
	}

	public void setDescripcionMunicipio(String descripcionMunicipio) {
		this.descripcionMunicipio = descripcionMunicipio;
	}

	public String getDescripcionEntidad() {
		return descripcionEntidad;
	}

	public void setDescripcionEntidad(String descripcionEntidad) {
		this.descripcionEntidad = descripcionEntidad;
	}

	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}

	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}
    
    

}
