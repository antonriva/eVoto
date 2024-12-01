package com.antonriva.backendspring.model;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@IdClass(NivelProcesoId.class)
@Table(name = "NIVELPROCESO")
public class NivelProceso {
    @Id
    @Column(name = "IDDENIVEL", nullable = false)
    private Integer idDeNivel;

    @Id
    @Column(name = "IDDEPROCESO", nullable = false)
    private Integer idDeProceso;

    @ManyToOne
    @JoinColumn(name = "IDDENIVEL", insertable = false, updatable = false)
    @JsonManagedReference // Gestiona la relación inversa
    private Nivel nivel;
    
    @OneToMany(mappedBy = "nivelProceso", fetch = FetchType.LAZY)
    @JsonManagedReference  // Referencia inversa (no se serializa)
    private List<InstanciaDeProceso> instanciaDeProcesos;

    @ManyToOne
    @JoinColumn(name = "IDDEPROCESO", insertable = false, updatable = false)
    @JsonManagedReference // Gestiona la relación inversa
    private Proceso proceso;

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

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }
}
