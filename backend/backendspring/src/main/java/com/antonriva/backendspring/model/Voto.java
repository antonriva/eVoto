package com.antonriva.backendspring.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name= "voto")
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddecandidatura", nullable = false)
    private Candidatura candidatura;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddeinstanciadeproceso", nullable = false)
    private InstanciaDeProceso instanciaDeProceso;
    
    

}
