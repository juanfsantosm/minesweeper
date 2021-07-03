import { Auth } from 'aws-amplify';

import History from './History';
import { ROUTES } from '../Utils/constants';

/**
 * Función para cerrar sesión del usuario logueado.
 */
export default async () => {
    await Auth.signOut({ global: true })
    .then(() => {
        sessionStorage.clear();
        History.push(ROUTES.LOGIN);
    })
    .catch(() => {
        sessionStorage.clear();
        History.push(ROUTES.LOGIN);
    });
}
