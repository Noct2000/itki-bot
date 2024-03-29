import { NgModule } from '@angular/core';
import { MenuComponent } from './menu.component';
import { FormsModule } from '@angular/forms';
import { IconsProviderModule } from '../../icons-provider.module';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { MenuRoutingModule } from './menu-routing.module';
import { ModalModule } from '../../modal/modal.module';



@NgModule({
  declarations: [
    MenuComponent,
  ],
  imports: [
    MenuRoutingModule,
    FormsModule,
    NzLayoutModule,
    NzMenuModule,
    IconsProviderModule,
    ModalModule,
  ],
})
export class MenuModule { }
