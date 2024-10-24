import React, {useState, useEffect} from 'react'
import DatePicker from 'react-datepicker'
import '../../../node_modules/react-datepicker/src/stylesheets/datepicker.scss'


const DateSelectorForm = () => {
    
    const[date, setDate] = useState(new Date())
    const[startDate, setStartDate] = useState(null)
    const[endDate, setEndDate] = useState(null)


    const handleSubmit = (e) => {
        e.preventDefault()
        console.log('Fecha de inicio: ',{startDate})
        console.log('Fecha de fin:', {endDate})
    }


    const handleChangeStartDate = (fecha) => {
        setStartDate(fecha)
        setEndDate(null)

    }

    const handleChangeEndDate = (fecha) => {
        setEndDate(fecha);
      };

    const filtro = (date) => new Date() < date

    const filtroDinamicoTiempo = (time) => {
        if (startDate === null) {
            // If startDate is null, return true if the time is after the current time
            return time >= new Date()
        } else {
            // If startDate is not null, return true if the time is after or equal to the startDate time
            return time > startDate
        }
    }

    return(
        <form onSubmit={handleSubmit}>
            <div>
                <label> Fecha de inicio: </label>
                <DatePicker 
                selected={startDate}
                onChange={handleChangeStartDate}
                showTimeSelect
                filterDate={filtro}
                filterTime={filtro}
                selectsStart
                startDate={startDate}
                endDate={endDate}
                timeFormat='HH:mm'
                timeIntervals={1}
                placeholderText="Selecciona la fecha de inicio"
                dateFormat="yyyy/MM/dd h:mm aa"
                />
            </div>

            <div>
                <label> Fecha de fin: </label>
                <DatePicker 
                selected={endDate}
                onChange={handleChangeEndDate}
                showTimeSelect
                filterDate={filtro}
                filterTime={filtroDinamicoTiempo}
                selectsEnd
                startDate={startDate}
                endDate={endDate}
                minDate={startDate}
                timeFormat='HH:mm'
                timeIntervals={1}
                placeholderText="Selecciona la fecha de fin"
                dateFormat="yyyy/MM/dd h:mm aa"
                />
            </div>

            <button type="submit"> Enviar </button>

        </form>
    )
}

export default DateSelectorForm