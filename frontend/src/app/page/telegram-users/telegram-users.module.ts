import { NgModule } from '@angular/core';
import { TelegramUsersComponent } from './telegram-users.component';
import {TelegramUsersRoutingModule} from "./telegram-users-routing.module";



@NgModule({
  imports: [TelegramUsersRoutingModule],
  declarations: [TelegramUsersComponent],
  exports: [TelegramUsersComponent]
})
export class TelegramUsersModule { }
