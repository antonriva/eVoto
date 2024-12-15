package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class PartidoBuscarDetalleDTO {
	
	   private Long id;
	    private String denominacion;
	    private String siglas;
	    private LocalDate fechaDeInicio;
	    private LocalDate fechaDeFin;
	    private String visualUrl;

	    public PartidoBuscarDetalleDTO(Long id, String denominacion, String siglas, LocalDate fechaDeInicio, LocalDate fechaDeFin, String visualUrl) {
	        this.id = id;
	        this.denominacion = denominacion;
	        this.siglas = siglas;
	        this.fechaDeInicio = fechaDeInicio;
	        this.fechaDeFin = fechaDeFin;
	        this.visualUrl = visualUrl;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDenominacion() {
			return denominacion;
		}

		public void setDenominacion(String denominacion) {
			this.denominacion = denominacion;
		}

		public String getSiglas() {
			return siglas;
		}

		public void setSiglas(String siglas) {
			this.siglas = siglas;
		}

		public LocalDate getFechaDeInicio() {
			return fechaDeInicio;
		}

		public void setFechaDeInicio(LocalDate fechaDeInicio) {
			this.fechaDeInicio = fechaDeInicio;
		}

		public LocalDate getFechaDeFin() {
			return fechaDeFin;
		}

		public void setFechaDeFin(LocalDate fechaDeFin) {
			this.fechaDeFin = fechaDeFin;
		}

		public String getVisualUrl() {
			return visualUrl;
		}

		public void setVisualUrl(String visualUrl) {
			this.visualUrl = visualUrl;
		}
	    
	    

}
