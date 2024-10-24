import React, { useState } from 'react';

function DynamicForm({tipo}) {

    const candidatosOpciones = [
        'Cuota de género mínima (default = 0%)',
        'Edad mínima (default = 18 años)',
        'Reelecciones (default = no son permitidas)',
        'Domicilio (default = cualquier lugar de la república)',
        'Límite de candidatos por partido o coalición (default = 1)',
        'Coalición (default = no)'
    ]

    const electoresOpciones = [
        'Adios',
        'Restriction 2',
        'Restriction 3',
        'Restriction 4',
        'Restriction 5',
        'Restriction 6',
        'Restriction 7'
    ]

    const territorioOpciones = [
        'Adios',
        'Restriction 2',
        'Restriction 3',
        'Restriction 4',
        'Restriction 5',
        'Restriction 6',
        'Restriction 7'
    ]

    const options = tipo === 'candidatos' 
    ? candidatosOpciones 
    : tipo === 'electores'  
    ? electoresOpciones 
    : territorioOpciones

    const [fields, setFields] = useState([null]);  // Start with one empty field

    // Function to handle the change of a select input
    const handleFieldChange = (index, value) => {
        const updatedFields = [...fields];
        updatedFields[index] = value;
        setFields(updatedFields);
    };

    // Function to add a new field
    const addField = () => {
        setFields([...fields, null]);  // Add another empty field
    };

    // Function to remove a field
    const removeField = (index) => {
        const updatedFields = fields.filter((_, i) => i !== index);
        setFields(updatedFields);
    };

    // Get the list of selected restrictions
    const selectedRestrictions = fields.filter(field => field !== null);

    // Filter options so that already selected options are not available
    const getAvailableOptions = (currentValue) => {
        return options.filter(option => !selectedRestrictions.includes(option) || option === currentValue);
    };

    // Function to handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        const selectedFields = fields.filter(field => field !== null);  // Remove empty selections
        if (selectedFields.length === 0) {
            alert('You must select at least one restriction.');
        } else {
            console.log('Selected Restrictions:', selectedFields);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h3>Seleccionar Restricciones ({tipo === 'candidatos' 
            ? 'Candidatos' 
            : tipo === 'electores' 
            ?'Electores'
            :'Territorios'}):</h3>

            {fields.map((field, index) => (
                <div key={index}>
                    <select
                        value={field || ''}  // Si el campo está vacío, lo deja vacío
                        onChange={(e) => handleFieldChange(index, e.target.value)}
                    >
                        <option value="" disabled>Seleccione una restricción</option>
                        {getAvailableOptions(field).map((option, i) => (
                            <option key={i} value={option}>{option}</option>
                        ))}
                    </select>

                    {/* Muestra el botón de eliminar si hay más de un campo */}
                    {fields.length > 1 && (
                        <button type="button" onClick={() => removeField(index)}>
                            Eliminar
                        </button>
                    )}
                </div>
            ))}

            <div>
                <button type="button" onClick={addField}>Add Another Restriction</button>
            </div>

            <div>
                <button type="submit">Submit</button>
            </div>
        </form>
    );
}

export default DynamicForm;