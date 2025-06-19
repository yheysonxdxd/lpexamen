import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MainClienteComponent } from './main-cliente/main-cliente';
import { FormxClienteComponent } from './main-cliente/formx-cliente/formx-cliente';
import { MainAsignacionComponent } from './main-asignacion/main-asignacion';
import { FormxAsignacion } from './main-asignacion/form-asignacion/form-asignacion';
import { MainMensajeComponent } from './main-mensajes/main-mensajes';
import { FormMensajes } from './main-mensajes/form-mensajes/form-mensajes';


export const pagesRoutes: Routes =[

    { path: 'dashboard', component: DashboardComponent  },
     { path: 'cliente', component: MainClienteComponent,
    children:[
      { path: 'new', component: FormxClienteComponent },
      { path: 'edit/:id', component: FormxClienteComponent },
    ]
  },
  { path: 'cita', component: MainClienteComponent,
    children:[
      { path: 'new', component: FormxClienteComponent },
      { path: 'edit/:id', component: FormxClienteComponent },
    ]
  },
  { path: 'mensaje', component: MainMensajeComponent,
    children:[
      { path: 'new', component: FormMensajes },
      { path: 'edit/:id', component: FormMensajes },
    ]
  },
  { path: 'asignacion', component: MainAsignacionComponent,
    children:[
      { path: 'new', component: FormxAsignacion },
      { path: 'edit/:id', component: FormxAsignacion },
    ]
  },
];