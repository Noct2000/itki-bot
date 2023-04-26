import { NgModule } from '@angular/core';
import {LoginRoutingModule} from "./login-routing.module";
import {LoginComponent} from "./login.component";
import {NzGridModule} from "ng-zorro-antd/grid";
import {ReactiveFormsModule} from "@angular/forms";
import {NzButtonModule} from "ng-zorro-antd/button";
import {AuthService} from "../../auth/auth.service";
import {HttpClientModule} from "@angular/common/http";
import { NzMessageServiceModule} from "ng-zorro-antd/message";


@NgModule({
  imports: [
    LoginRoutingModule,
    NzGridModule,
    ReactiveFormsModule,
    NzButtonModule,
    HttpClientModule,
    NzMessageServiceModule,
  ],
  declarations: [LoginComponent],
  exports: [LoginComponent],
  providers: [AuthService],
})
export class LoginModule { }
