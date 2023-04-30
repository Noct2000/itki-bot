import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TelegramUsersComponent} from "./telegram-users.component";

const routes: Routes = [
  { path: '', component: TelegramUsersComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TelegramUsersRoutingModule { }
