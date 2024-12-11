package com.antonriva.backendspring.dto;

public class PersonaIdPadresDTO {
    private Long idPadre;
    private Long idMadre;

    // Constructor
    public PersonaIdPadresDTO(Long idPadre, Long idMadre) {
        this.idPadre = idPadre;
        this.idMadre = idMadre;
    }

    // Getters y Setters
    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public Long getIdMadre() {
        return idMadre;
    }

    public void setIdMadre(Long idMadre) {
        this.idMadre = idMadre;
    }
}

