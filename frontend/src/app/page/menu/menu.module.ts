import { NgModule } from '@angular/core';
import { MenuComponent } from './menu.component';
import { FormsModule } from '@angular/forms';
import { IconsProviderModule } from '../../icons-provider.module';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { MenuRoutingModule } from './menu-routing.module';
import { ExitModalComponent } from '../../modal/exit-modal/exit-modal.component';
import { NzModalModule } from 'ng-zorro-antd/modal';
import {NzTypographyModule} from "ng-zorro-antd/typography";



@NgModule({
  declarations: [
    MenuComponent,
    ExitModalComponent,
  ],
  imports: [
    MenuRoutingModule,
    FormsModule,
    NzLayoutModule,
    NzMenuModule,
    IconsProviderModule,
    NzModalModule,
    NzTypographyModule,
  ],
  providers: [
    ExitModalComponent,
  ],
})
export class MenuModule { }
