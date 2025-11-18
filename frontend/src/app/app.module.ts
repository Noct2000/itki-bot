import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { uk_UA } from 'ng-zorro-antd/i18n';
import { CommonModule, registerLocaleData } from '@angular/common';
import uk from '@angular/common/locales/uk';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthInterceptor } from './auth/auth-interceptor';

registerLocaleData(uk);

@NgModule({ declarations: [
        AppComponent,
    ],
    bootstrap: [AppComponent], imports: [BrowserModule,
        CommonModule,
        AppRoutingModule,
        BrowserAnimationsModule], providers: [
        { provide: NZ_I18N, useValue: uk_UA },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
        },
        provideHttpClient(withInterceptorsFromDi()),
    ] })
export class AppModule { }
