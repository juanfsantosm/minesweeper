import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import { BrowserRouter, Router } from 'react-router-dom'
import 'react-app-polyfill/ie11';
import History from './Navigation/History';

/** Componentes para inicializar el sistema */
import AppScrollToTop from './Layout/AppScrollToTop';

/** Service worker */
import * as serviceWorker from './serviceWorker';
import { MinesWeeper } from './MinesWeeper';

ReactDOM.render(
  <BrowserRouter>
    <AppScrollToTop>
      <Router history={History}>
        <App />
      </Router>
    </AppScrollToTop>
  </BrowserRouter>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
