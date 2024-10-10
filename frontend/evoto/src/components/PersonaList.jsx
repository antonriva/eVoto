import React, {useEffect, useState} from "react"



function PersonaList()  {
    //Listado de personas en un all-time render app
    const [Personas, setPersonas] = useState([])

    //Maneja comunicacion HTTP con fetching
    useEffect(()=>{
        fetch('http://localhost:8080/api/persona')
        .then(response => response.json())
        .then(data => setPersonas(data))
        .catch(error => console.error('Error fetching users:', error));
    }, [])

    return(
        <div>
            <h2>Personas prueba HTTP</h2>
            <ul>
                {Personas.map(persona =>(
                    <li key={persona.id}> Nombre:{persona.nombre}, Apellido paterno:{persona.apellidoPaterno}, Apellido materno:{persona.apellidoMaterno}, Fecha de nacimiento:{persona.fechaDeNacimiento}, Fecha de fin:{persona.fechaDeFin}</li>
                ))}
            </ul>

        </div>
    )
}

export default PersonaList