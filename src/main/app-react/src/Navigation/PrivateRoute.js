import * as React from 'react';
import { Route, Redirect } from 'react-router-dom';

import { ValidateToken } from '../Utils/Helpers2';
import { ROUTES } from '../Utils/constants';
import { getUsuarioLogueado } from '../Utils/gestionSessionStorage';

/**
 * Validar si el usuario tiene acceso al componente.
 * @param {Component|any} component Componente para renderizar.
 * @returns {Route} Componente autorizado.
 */
const PrivateRoute = ({ component: Component }: any & { component: any }) => {
  const checkUserAuth = ValidateToken(getUsuarioLogueado()?.token);
  return (
    <Route
      render={props => {
        return checkUserAuth ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{
              pathname: ROUTES.LOGIN
            }}
          />
        );
      }}
    />
  );
};

export default PrivateRoute;
