import React from 'react';
import AppSubMenu from './AppSubMenu';

/**
 * Se define el tipo de propiedades que
 * recibe el componente.
 */
type Props = {
    model: object[],
    onMenuItemClick: Function
}

/**
 * @class
 * Componente que permite crear el menú del sistema.
 */
class AppMenu extends React.Component<Props> {
    static defaultProps = {
        model: null,
        onMenuItemClick: null
    }

    /**
     * @function
     * Función principal para renderizar el contenido del componente.
     */
    render() {
        return (
            <div className="layout-menu-container">
                <AppSubMenu items={this.props.model} className="layout-menu" onMenuItemClick={this.props.onMenuItemClick} root={true}/>
            </div>
        );
    }
}

export default AppMenu;
