import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CuratorsComponent} from "./curators.component";

const routes: Routes = [
  { path: '', component: CuratorsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CuratorsRoutingModule { }
