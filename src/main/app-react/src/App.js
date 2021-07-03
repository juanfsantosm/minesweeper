import './Themes/layout/layout.scss';
import './Themes/layout/App.scss';

/**
* Configuración de las rutas.
*/
import AppRoutes from './Navigation/Routes';

/**
* Configuración de los iconos.
*/
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faTruck, faPlus, faKey, faUserTimes, faUserPlus, faCarCrash,
  faCheckCircle, faExclamationCircle, faMapMarker, faStoreAltSlash, faBan,
  faCommentAlt
} from '@fortawesome/free-solid-svg-icons';
library.add(faTruck, faPlus, faKey, faUserTimes, faUserPlus, faCarCrash,
  faCheckCircle, faExclamationCircle, faMapMarker, faStoreAltSlash, faBan,
  faCommentAlt);

/**
* @constant
* Función para renderizar la configuración de las rutas.
* @returns {React.FC} Componente funcional de rutas.
*/
const App = () => {
  return <AppRoutes />;
};

export default App;
