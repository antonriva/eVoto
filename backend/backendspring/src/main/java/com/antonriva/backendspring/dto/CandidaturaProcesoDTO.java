package com.antonriva.backendspring.dto;

import java.time.LocalDateTime;

public class CandidaturaProcesoDTO {

	private Long idDeElector;// ESTE ES EL ID QUE SE DA AL INICIO, DEL ELECTOR QUE ESTA INICIANDO EL PROCESO
    private Long idDeInstanciaDeProceso; // ESTE ES EL ID QUE SE DIO AL INICIO, DEL PROCESO EN EL QUE SE ESTA VOTANDO
    
    private Long idCandidatura; //ESTOS SERAN LOS ID DE LAS CANDIDATURAS ASOCIADAS A UN DETERMINADO PROCESO ELEGIDO
    //DATOS DE LA TABLA PARTIDO, PODEMOS ACCEDER A TRAVES DE CANDIDATURAS DE INSTANCIA PROCESO
    private String denominacionPartido;//ESTA DESCRIPCION SE ENCUENTRA EN LA TABLA DE Partido, candidatura tiene un id asociado a esta tabla
	private String siglas;// SE ENCUENTRA EN LA TABLA DE Partido, candidatura tiene un id asociado a esta tabla
	
	//TABLA VISUAL	
    private String visualUrl;// SE ENCUENTRA EN LA TABLA VISUAL, QUE DEBE TENER ASOCIADOS EL ID DE PARTIDO, VAMOS A ELEGIR EL ATRIBUTO CONTENIDO DEL VISUAL QUE COINCIDA CON EL ID DE PARTIDO Y QUE TENGA IDDERECURSOVIGENTE IGUAL A 1 Y IDDETIPODEVISUAL IGUAL A 1
    								
    
    private String nombre; //SE ENCUENTRA EN PERSONA, QUE ESTA ASOCIADO AL CANDIDATO A TRAVES DE ELECTOR
    private String apellidoPaterno;//SE ENCUENTRA EN PERSONA, QUE ESTA ASOCIADO AL CANDIDATO A TRAVES DE ELECTOR
	public CandidaturaProcesoDTO(Long idDeElector, Long idDeInstanciaDeProceso, Long idCandidatura,
			String denominacionPartido, String siglas, String visualUrl, String nombre, String apellidoPaterno) {
		super();
		this.idDeElector = idDeElector;
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
		this.idCandidatura = idCandidatura;
		this.denominacionPartido = denominacionPartido;
		this.siglas = siglas;
		this.visualUrl = visualUrl;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
	}
	public Long getIdDeElector() {
		return idDeElector;
	}
	public void setIdDeElector(Long idDeElector) {
		this.idDeElector = idDeElector;
	}
	public Long getIdDeInstanciaDeProceso() {
		return idDeInstanciaDeProceso;
	}
	public void setIdDeInstanciaDeProceso(Long idDeInstanciaDeProceso) {
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
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
	public String getSiglas() {
		return siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public String getVisualUrl() {
		return visualUrl;
	}
	public void setVisualUrl(String visualUrl) {
		this.visualUrl = visualUrl;
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
    
    


}
