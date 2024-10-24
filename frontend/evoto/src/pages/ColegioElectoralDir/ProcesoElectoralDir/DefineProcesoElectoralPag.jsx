import React,{useState} from 'react'
import DefineProcesoElectoral from '../../../components/DefineProcesoElectoral'
import DefineCargo from '../../../components/DefineCargo';


const DefineProcesoElectoralPag = () => {
    const [isSaved, setIsSaved] = useState(false);  // Track if "Save Changes" has been clicked

    // Function to handle save button click
    const handleSave = () => {
        setIsSaved(true);  // Update state to indicate the form has been saved
    };

    return(

        <div>
            <DefineProcesoElectoral/>
            <div>
                <button type="button" onClick={handleSave}>Next</button>
            </div>
            {isSaved && (
                <div>
                    <h2>Cargo a elegirse</h2>
                    <DefineCargo/> {/* The next component you want to show */}
                </div>
            )}
        </div>
    )
}

export default DefineProcesoElectoralPag