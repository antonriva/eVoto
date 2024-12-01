package com.antonriva.backendspring.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "INSTANCIADEPROCESO")
public class InstanciaDeProceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FECHADEINICIO", nullable = false)
    private LocalDate fechaDeInicio;

    @Column(name = "FECHADEFIN", nullable = false)
    private LocalDate fechaDeFin;

    @Column(name = "GANADORESNUM", nullable = false)
    private Integer ganadoresNum;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "IDDENIVEL", referencedColumnName = "IDDENIVEL"),
        @JoinColumn(name = "IDDEPROCESO", referencedColumnName = "IDDEPROCESO")
    })
    private NivelProceso nivelProceso;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getGanadoresNum() {
        return ganadoresNum;
    }

    public void setGanadoresNum(Integer ganadoresNum) {
        this.ganadoresNum = ganadoresNum;
    }
    
    @JsonBackReference
    public NivelProceso getNivelProceso() {
        return nivelProceso;
    }

    public void setNivelProceso(NivelProceso nivelProceso) {
        this.nivelProceso = nivelProceso;
    }
}
