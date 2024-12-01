package com.antonriva.backendspring.model;

import java.io.Serializable;
import java.util.Objects;

public class NivelProcesoId implements Serializable {
    private Integer idDeNivel;
    private Integer idDeProceso;

    // Constructor vacío
    public NivelProcesoId() {}

    // Constructor con parámetros
    public NivelProcesoId(Integer idDeNivel, Integer idDeProceso) {
        this.idDeNivel = idDeNivel;
        this.idDeProceso = idDeProceso;
    }

    // Getters y Setters
    public Integer getIdDeNivel() {
        return idDeNivel;
    }

    public void setIdDeNivel(Integer idDeNivel) {
        this.idDeNivel = idDeNivel;
    }

    public Integer getIdDeProceso() {
        return idDeProceso;
    }

    public void setIdDeProceso(Integer idDeProceso) {
        this.idDeProceso = idDeProceso;
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NivelProcesoId that = (NivelProcesoId) o;
        return Objects.equals(idDeNivel, that.idDeNivel) && Objects.equals(idDeProceso, that.idDeProceso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDeNivel, idDeProceso);
    }
}
