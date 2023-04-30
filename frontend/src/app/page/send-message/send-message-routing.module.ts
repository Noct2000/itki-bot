import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SendMessageComponent} from "./send-message.component";

const routes: Routes = [
  { path: '', component: SendMessageComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SendMessageRoutingModule { }
