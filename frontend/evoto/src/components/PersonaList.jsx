import React, {useEffect, useState} from "react"



function PersonaList()  {
    //Listado de personas en un all-time render app
    const [Personas, setPersonas] = useState([])

    //Maneja comunicacion HTTP con fetching
    useEffect(()=>{
        fetch('http://localhost:8080/api/persona')
        .then(response => response.json())
        .then(data => setPersonas.json())
        .catch(error => console.error('Error fetching users:', error));
    }, [])

    return(
        <div>
            <h2>Personas prueba HTTP</h2>
            <ul>
                {Personas.map(persona =>(
                    <li key={persona.id}>{persona.nombre}</li>
                ))}
            </ul>

        </div>
    )
}

export default PersonaList