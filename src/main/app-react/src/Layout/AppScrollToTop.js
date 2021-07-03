import React  from 'react';
import { withRouter, RouteComponentProps } from 'react-router-dom';

/**
 * Se define el tipo de propiedades que recibe el componente.
 */
type Props = RouteComponentProps & {
    location: any;
};

/**
 * Se define el estado del componente.
 */
type State = {
    location: any
};

/**
 * @class
 * Componente que permite ubicar cualquier página del sistema 
 * hasta la parte superior de la pantalla.
 */
class AppScrollToTop extends React.Component<Props, State> {
    /**
     * Acción que se realiza después de montar el componente.
     * @param {State} prevProps 
     */
    componentDidUpdate(prevProps: State) {
        if (this.props.location !== prevProps.location) {
            window.scrollTo(0, 0)
        }
    }

    /**
     * @function
     * Función principal para renderizar el contenido del componente.
     */
    render() {
        return this.props.children
    }
}

export default withRouter(AppScrollToTop);
