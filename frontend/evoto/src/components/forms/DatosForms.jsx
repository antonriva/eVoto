import React, {useState} from 'react'

{/* Cabecera. Inicia el campo para recibir el nombre y manejo de eventos, previene refresh y manda el log al web*/}
function DatosForms({label, type, validation, transform}){
    const [input, setInput] = useState('')

    //Esto es lo que nos pone esa interfaz de debbugging???
    const handleSubmit = (e) =>{
        e.preventDefault()
        console.log("Formulario enviado:", {input})
    }

    const handleInputChange = (e) => {
        let value = e.target.value

        // Apply transformation (e.g., to uppercase)
        if (transform === 'uppercase') {
            value = value.toUpperCase()
        }

        // Apply validation
        if (validation === 'text-only') {
            value = value.replace(/[0-9]/g, '') // Remove numbers
        } else if (validation === 'number-only') {
            value = value.replace(/[^0-9]/g, '') // Remove everything except numbers
        }

        setInput(value)
    }

    {/* Funcion. Campo desplegado visualmente y funcionalidad */}  
    return(
        <form onSubmit={handleSubmit}>
            <div>
                <label>{label}: </label>
                    <input
                        type = {type}
                        value = {input}
                        onChange = {handleInputChange}
                    />
            </div>
            <button type="submit"> Enviar </button>
        </form>
    )

}

export default DatosForms