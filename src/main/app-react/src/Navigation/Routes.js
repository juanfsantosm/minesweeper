import * as React from 'react';
import {Route, Switch, useLocation} from 'react-router-dom';
import {ROUTES} from '../Utils/constants';
import { MinesWeeper } from '../MinesWeeper';
import { Play } from '../Play';

/**
 * @class
 * Componente para configurar las rutas del sistema.
 */
class AppRouter extends React.Component {

  /**
     * @function
     * Función principal para renderizar el contenido del componente.
     */
  render() {
    /**
     * Componente funcional para generar la página 404.
     */
    const Page404 = () => {
      const location = useLocation();
      return (
        <div className="p-grid p-fluid p-align-center">
          <div className="p-col-4"></div>
          <div className="p-col-4">
            <h1>
              {'Not Found'}
              <br />
              <code>{location.pathname}</code>
            </h1>
          </div>
          <div className="p-col-4"></div>
        </div>
      );
    };

    return (
      // si se agregan rutas al DashboardContainer,
      // es necesario definirlas aqui
      <Switch>
          <Route exact={true} path={ROUTES.START} component={MinesWeeper} />
          <Route exact={true} path={ROUTES.PLAY} component={Play} />
          {/* pagina no encontrada */}
          <Route component={Page404} />
      </Switch>
    );
  }
}

export default AppRouter;
