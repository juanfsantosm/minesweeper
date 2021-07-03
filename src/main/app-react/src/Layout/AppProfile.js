import React from 'react';
import classNames from 'classnames';

/**
 * Definir el tipo de propiedades que recibe
 * el componente.
 */
type Props = {}

/**
 * Definir el estado del componente.
 */
type State = {
    expanded: boolean
}

/**
 * @class
 * Componente que permite el menú Perfil para el usuario.
 */
class AppProfile extends React.Component<Props,State> {
    constructor(props: any) {
        super(props);
        this.state = {
            expanded: false
        };
        this.onClick = this.onClick.bind(this);
    }

    /**
     * Acción cuando se pulsa el click en el botón.
     * @param {any} event Evento del botón.
     */
    onClick(event: any) {
        this.setState({expanded: !this.state.expanded});
        event.preventDefault();
    }

    /**
     * @function
     * Función principal para renderizar el contenido del componente.
     */
    render() {
        return  (
            <div className="layout-profile">
                <div>
                    <img src="assets/layout/images/logo-vwm.png" alt="" />
                </div>
                <button className="p-link layout-profile-link" onClick={this.onClick}>
                    <span className="username">Nombre usuario</span>
                    <i className="pi pi-fw pi-cog"/>
                </button>
                <ul className={classNames({'layout-profile-expanded': this.state.expanded})}>
                    <li><button className="p-link"><i className="pi pi-fw pi-user"/><span>Account</span></button></li>
                    <li><button className="p-link"><i className="pi pi-fw pi-inbox"/><span>Notifications</span><span className="menuitem-badge">2</span></button></li>
                    <li><button className="p-link"><i className="pi pi-fw pi-power-off"/><span>Logout</span></button></li>
                </ul>
            </div>
        );
    }
}

export default AppProfile;
