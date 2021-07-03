import React, {Component, createRef} from 'react';

import {Dialog} from 'primereact/dialog';

import Logout from '../../Navigation/Logout';
import Help from '../../Navigation/Help';
import { getUsuarioLogueado } from '../../Utils/gestionSessionStorage';


/**
 * Definir las propiedades que recibe el componente.
 */
interface Props {
    onToggleMenu: (event: any) => void;
}

/**
 * Definir el estado del componente.
 */
interface State {
    pdfUrl: any,
}   

/**
 * @class
 * Componente que permite crear el menú que se ubica en la parte
 * superior del sistema.
 */
class AppTopBar extends Component<Props, State> {
    /**
     * Inicializar las propiedades del componente.
     */
    static defaultProps = {
        onToggleMenu: null
    }

    /**
     * @constructor
     * @param {any} props Son las propiedades del componente-
     */
    constructor(props:any) {
        super(props);
        this.state ={
            pdfUrl: undefined,
        }
    }

    /**
     * Acción para salir del sistema.
     * @param {React.MouseEvent<HTMLButtonElement>} event Evento del botón HTML.
     */
    handleLogout = async (event: React.MouseEvent<HTMLButtonElement>) => {
        Logout();
    };

     /**
     * Acción para salir del sistema.
     * @param {React.MouseEvent<HTMLButtonElement>} event Evento del botón HTML.
     */
    handleHelp = async (event: React.MouseEvent<HTMLButtonElement>) => {
        var currentLocation = window.location.pathname;
        console.log('---> ruta actual: ', currentLocation);
        this.setState({
            pdfUrl: await Help(currentLocation)
        });
        console.log('---> url: ', this.state.pdfUrl);
        window.open(this.state.pdfUrl) 
    };

    /**
     * @function
     * Función principal para renderizar el contenido del componente.
     */
    render() {
        return (
            <div className="layout-topbar clearfix">
                <button className="p-link layout-menu-button" onClick={this.props.onToggleMenu}>
                    <span className="pi pi-bars"/>
                </button>
                <div className="layout-topbar-icons">
                    <span>
                        <strong>
                            {getUsuarioLogueado()?.nombreCompleto} - {getUsuarioLogueado()?.rol} - {getUsuarioLogueado()?.codigoEmpresa}
                        </strong>
                    </span>
                    <button className="p-link" onClick={event => this.handleHelp(event)}>
                        <span className="layout-topbar-icon pi pi-question-circle"/>
                        <span className="layout-topbar-item-text">Ayuda</span>
                    </button>
                    <button className="p-link" onClick={event => this.handleLogout(event)}>
                        <span className="layout-topbar-icon pi pi-sign-out"/>
                        <span className="layout-topbar-item-text">Salir</span>
                    </button>
                </div>
            </div>
            
        );
    }
}

export default AppTopBar;
