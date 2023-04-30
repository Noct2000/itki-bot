import { NgModule } from '@angular/core';
import {SendMessageRoutingModule} from "./send-message-routing.module";
import {SendMessageComponent} from "./send-message.component";
import {NzTypographyModule} from "ng-zorro-antd/typography";
import {NzRadioModule} from "ng-zorro-antd/radio";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NzGridModule} from "ng-zorro-antd/grid";
import { SendTextComponent } from './forms/send-text/send-text.component';
import {NzFormModule} from "ng-zorro-antd/form";
import {NzInputModule} from "ng-zorro-antd/input";
import {CommonModule} from "@angular/common";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzIconModule} from "ng-zorro-antd/icon";



@NgModule({
  imports: [SendMessageRoutingModule, NzTypographyModule, NzRadioModule, FormsModule, NzGridModule, NzFormModule, ReactiveFormsModule, NzInputModule, CommonModule, NzButtonModule, NzIconModule],
  declarations: [SendMessageComponent, SendTextComponent],
  exports: [SendMessageComponent]
})
export class SendMessageModule {
}
