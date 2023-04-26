import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from "./auth/auth.guard";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/menu',
  },
  {
    path: 'menu',
    loadChildren: () => import('./page/menu/menu.module').then(m => m.MenuModule),
    canActivate: [AuthGuard],
  },
  {
    path: "login",
    loadChildren: () => import('./page/login/login.module').then(m => m.LoginModule),
    canActivate: [AuthGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
