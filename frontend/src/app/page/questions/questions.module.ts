import { NgModule } from '@angular/core';

import { QuestionsRoutingModule } from './questions-routing.module';

import { QuestionsComponent } from './questions.component';
import { NzTableModule } from 'ng-zorro-antd/table';
import { CommonModule } from '@angular/common';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { ModalModule } from '../../modal/modal.module';


@NgModule({
  imports: [
    QuestionsRoutingModule,
    NzTableModule,
    CommonModule,
    NzTypographyModule,
    NzButtonModule,
    NzIconModule,
    ModalModule,
  ],
  declarations: [QuestionsComponent],
  exports: [QuestionsComponent]
})
export class QuestionsModule {
}
