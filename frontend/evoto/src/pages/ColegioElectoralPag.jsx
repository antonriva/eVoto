import React from 'react'
import {Link} from 'react-router-dom'


const ColegioElectoralPag = () => {
    return(
            <div>
                <nav>
                    <ul>
                        <li>
                            <Link to ="ProcesoElectoralPag"> Nuevo proceso electoral </Link>
                        </li>
                    </ul>
                </nav>
            </div>
    )
}
export default ColegioElectoralPag