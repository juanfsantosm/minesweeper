import React, { Component } from 'react';
import {VERSION_APP, DEV_NAME_APP, DEV_CORP_APP,DEV_LINK_APP} from '../../Utils/constants';

/**
 * @class
 * Componente que permite crear el pie de página del sistema.
 */
class AppFooter extends Component {
    /**
     * @function
     * Función principal para renderizar el contenido del componente.
     */
    render() {
        return  (
            <div className="layout-footer" style={{textAlign:"center"}}>
                <span className="footer-text" style={{'marginRight': '5px'}}>{DEV_CORP_APP}</span>
                <span className="footer-text" style={{'marginRight': '5px'}}>-</span>
                <span className="footer-text" style={{'marginRight': '5px'}}>
                    <a href={DEV_LINK_APP} target="_blank" rel="noopener noreferrer" >
                        {DEV_NAME_APP}
                    </a>
                </span>
                <br />
                <span className="footer-text" style={{'marginRight': '5px'}}>{VERSION_APP}</span>
            </div>
        );
    }
}

export default AppFooter;
