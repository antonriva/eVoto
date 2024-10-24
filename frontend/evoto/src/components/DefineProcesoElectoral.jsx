import React from 'react'
import DatosForms from './forms/DatosForms'
import DateSelectorForm from './forms/DateSelectorForm'
import DynamicForm from './forms/DynamicForm'


const DefineProcesoElectoral = () => {
    return(

        <div>
            <p>Nuevo proceso electoral</p>
            {/* Input para recibir nombre: Solo texto, no números, y todo en mayus */}
            <DatosForms
                label="Ingrese nombre del proceso"
                type="text"
                validation="text-only"
                transform="uppercase"
            />
            <DatosForms
                label="Numero de puestos"
                type="number"
                validation="number-only"
            />
            <DateSelectorForm/>
            <DynamicForm/>
        </div>
    )
}

export default DefineProcesoElectoral