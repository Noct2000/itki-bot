import { NgModule } from '@angular/core';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { ReactiveFormsModule } from '@angular/forms';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { AuthService } from '../../auth/auth.service';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { NzMessageModule } from 'ng-zorro-antd/message';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzFormModule } from 'ng-zorro-antd/form';


@NgModule({ declarations: [LoginComponent],
    exports: [LoginComponent], imports: [LoginRoutingModule,
        NzGridModule,
        ReactiveFormsModule,
        NzButtonModule,
        NzMessageModule,
        NzLayoutModule,
        NzFormModule], providers: [AuthService, provideHttpClient(withInterceptorsFromDi())] })
export class LoginModule { }
