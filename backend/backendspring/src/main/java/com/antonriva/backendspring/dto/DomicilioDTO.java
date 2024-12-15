package com.antonriva.backendspring.dto;

import javax.validation.constraints.Pattern;

public class DomicilioDTO {
	
    private Long entidadFederativaId;
    private Long municipioId;
    private Long localidadId;
    private Long coloniaId;
    private Long codigoPostalId;
    @Pattern(regexp = "^[A-Z]+( [A-Z]+)*$", message = "La calle debe contener solo letras mayúsculas y espacios, sin números ni caracteres especiales.")
    private String calle;
    private Integer numeroExterior;
    private Integer numeroInterior;


}
