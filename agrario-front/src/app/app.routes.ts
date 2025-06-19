import { Routes } from '@angular/router';
import { LayoutComponent } from './paginas/layout/layout.component';

export const routes: Routes = [
    { path: '', redirectTo:'pages' , pathMatch: 'full' },
  { path: 'main', component: LayoutComponent, },
  { path: 'pages',
    component: LayoutComponent,
    loadChildren:()=>import('./paginas/pages.routes').then(x=>x.pagesRoutes)
  },

];
