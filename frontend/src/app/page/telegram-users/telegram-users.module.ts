import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TelegramUsersComponent } from './telegram-users.component';
import { TelegramUsersRoutingModule } from './telegram-users-routing.module';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzTypographyModule } from 'ng-zorro-antd/typography';

@NgModule({
  declarations: [
    TelegramUsersComponent
  ],
  imports: [
    CommonModule,
    TelegramUsersRoutingModule,
    NzTableModule,
    NzDividerModule,
    NzTypographyModule,
  ],
  exports: [TelegramUsersComponent]
})
export class TelegramUsersModule { }
