import React, {useState} from 'react'

{/* Cabecera. Inicia el campo para recibir el nombre y manejo de eventos, previene refresh y manda el log al web*/}
function DatosForms(){
    const [nombre, setNombre] = useState('')
    //Esto es lo que nos pone esa interfaz de debbugging???
    const handleSubmit = (e) =>{
        e.preventDefault()
        console.log("Formulario enviado:", {nombre})
    }

    {/* Funcion. Campo desplegado visualmente y funcionalidad */}  
    return(
        <form onSubmit={handleSubmit}>
            <div>
                <label>Nombre:             </label>
                    <input type="text" value={nombre} onChange={(e)=> setNombre(e.target.value)} />
            </div>
            <button type="submit"> Enviar </button>
        </form>
    )

}

export default DatosForms