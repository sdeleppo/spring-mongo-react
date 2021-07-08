import React from "react";
import RDOM from "react-dom"
import { BrowserRouter } from "react-router-dom";

import App from "./App";
import * as serviceWorker from "./serviceWorker";
console.log('reactdom', RDOM);
RDOM.render(
<BrowserRouter>
  <App />
</BrowserRouter>,
document.getElementById("root"));

serviceWorker.unregister();