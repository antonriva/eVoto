import React from 'react'
import {Link} from 'react-router-dom'

const ProcesoElectoralPag = () => {
    return(
        <div>
            <nav>
                <ul>
                    <li>
                        <Link to ="DefineProcesoElectoralPag"> Definir proceso electoral </Link>
                    </li>
                </ul>
            </nav>
        </div>
    )
}

export default ProcesoElectoralPag