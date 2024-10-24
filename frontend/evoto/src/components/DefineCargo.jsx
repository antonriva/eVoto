import React from 'react'
import DateSelectorForm from './forms/DateSelectorForm'
import DynamicForm from './forms/DynamicForm'

const DefineCargo = () =>{
    return(
        <div>
            <DateSelectorForm/>
            <DynamicForm
            tipo = 'candidatos'
            />
        </div>
    )
}

export default DefineCargo