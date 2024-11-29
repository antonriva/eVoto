package com.antonriva.backendspring.specification;

import org.springframework.data.jpa.domain.Specification;
import com.antonriva.backendspring.model.Persona;

public class PersonaSpecifications {

    public static Specification<Persona> conId(Integer id) {
        return (root, query, criteriaBuilder) -> 
            id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Persona> conNombre(String nombre) {
        return (root, query, criteriaBuilder) -> 
            nombre == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
    }

    public static Specification<Persona> conApellido(String apellido) {
        return (root, query, criteriaBuilder) -> 
            apellido == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidoPaterno")), "%" + apellido.toLowerCase() + "%");
    }

    public static Specification<Persona> conFechaDeNacimiento(String fechaDeNacimiento) {
        return (root, query, criteriaBuilder) -> 
            fechaDeNacimiento == null ? null : criteriaBuilder.equal(root.get("fechaDeNacimiento"), fechaDeNacimiento);
    }
    
    public static Specification<Persona> conFechaDeFin(String fechaDeFin) {
        return (root, query, criteriaBuilder) -> 
            fechaDeFin == null ? null : criteriaBuilder.equal(root.get("fechaDeFin"), fechaDeFin);
    }
}