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
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellido paterno</th>
                        <th>Apellido materno</th>
                        <th>Fecha de nacimiento</th>
                        <th>Fecha de fin</th>
                    </tr>
                </thead>
                <tbody>
                    {Personas.map(persona =>(
                        <tr key={persona.id}> 
                            <td>{persona.nombre}</td>
                            <td>{persona.apellidoPaterno}</td>
                            <td>{persona.apellidoMaterno}</td>
                            <td>{persona.fechaDeNacimiento}</td> 
                            <td>{persona.fechaDeFin}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default PersonaList