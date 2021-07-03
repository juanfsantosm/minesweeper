import React from 'react';
import {NavLink} from 'react-router-dom'
import classNames from 'classnames';

/**
 * Definir las propiedades que recibe del componente.
 */
type Props = {
    className: string,
    items: object[],
    onMenuItemClick: Function,
    root: boolean
}

/**
 * Definir el estado del componente.
 */
type State = {
    activeIndex: any
}

/**
 * @class
 * Componente que permite estructurar las opciones del menú
 * que corresponde al usuario logueado en el sistema.
 */
class AppSubMenu extends React.Component<Props, State> {
    /**
     * Inicializar las propiedades del componente.
     */
    static defaultProps = {
        className: null,
        items: null,
        onMenuItemClick: null,
        root: false
    }

    /**
     * @constructor
     * @param {any} props Propiedades heredadas de React.Componet. 
     */
    constructor(props: any) {
        super(props);
        this.state = {activeIndex: null};
    }

    /**
     * Ejecutar acción de la opción seleccionadda del menú.
     * @param {any} event Evento del elemento HTML seleccionado.| 
     * @param {any} item Opción del menú.
     * @param {any} index Índice correspondiente a la opción del menú.
     */
    onMenuItemClick(event: any, item: any, index: any) {
        //avoid processing disabled items
        if(item.disabled) {
            event.preventDefault();
            return true;
        }
                        
        //execute command
        if(item.command) {
            item.command({originalEvent: event, item: item});
        }

        if(index === this.state.activeIndex)
            this.setState({activeIndex: null});    
        else
            this.setState({activeIndex: index});

        if(this.props.onMenuItemClick) {
            this.props.onMenuItemClick({
                originalEvent: event,
                item: item
            });
        }
    }

    /**
     * Renderizar el contenido del enlace que pertenece
     * a la opción seleccioanda del menú.
     * @param {any} item Opción del menú.
     */
	renderLinkContent(item: any) {
		let submenuIcon = item.items && <i className="pi pi-fw pi-angle-down menuitem-toggle-icon"></i>;
		let badge = item.badge && <span className="menuitem-badge">{item.badge}</span>;

		return (
			<React.Fragment>
				<i className={item.icon}></i>
				<span>{item.label}</span>
				{submenuIcon}
				{badge}
			</React.Fragment>
		);
	}

    /**
     * 
     * @param {qny} item Opción del menú.
     * @param {any} i Índice correspondiente a la opción del menú.
     */
	renderLink(item: any, i: any) {
		let content = this.renderLinkContent(item);

		if (item.to) {
			return (
				<NavLink activeClassName="active-route" to={item.to} onClick={(e) => this.onMenuItemClick(e, item, i)} exact target={item.target}>
                    {content}
                </NavLink>
			)
		}
		else {
			return (
				<a href={item.url} onClick={(e) => this.onMenuItemClick(e, item, i)} target={item.target}>
					{content}
				</a>
			);

		}
	}
    
    /**
     * @function
     * Función principal para renderizar el contenido del componente.
     */
    render() {
        let items = this.props.items && this.props.items.map((item:{[key:string]:any}, i) => {
            let active = this.state.activeIndex === i;
            let styleClass = classNames(item.badgeStyleClass, {'active-menuitem': active && !item.to});

            return (
                <li className={styleClass} key={i}>
                    {item.items && this.props.root===true && <div className='arrow'></div>}
					{this.renderLink(item, i)}
                    <AppSubMenu items={item.items} onMenuItemClick={this.props.onMenuItemClick}/>
                </li>
            );
        });
        return items ? <ul className={this.props.className}>{items}</ul> : null;
    }
}

export default AppSubMenu;
