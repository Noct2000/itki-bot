import { NgModule } from '@angular/core';
import {SendMessageRoutingModule} from "./send-message-routing.module";
import {SendMessageComponent} from "./send-message.component";



@NgModule({
  imports: [SendMessageRoutingModule],
  declarations: [SendMessageComponent],
  exports: [SendMessageComponent]
})
export class SendMessageModule { }
