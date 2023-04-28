import { NgModule } from '@angular/core';
import { ExitModalComponent } from './exit-modal/exit-modal.component';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { CreateQuestionsModalComponent } from './create-questions-modal/create-questions-modal.component';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
  ],
  imports: [
    NzModalModule,
    NzTypographyModule,
    NzFormModule,
    NzInputModule,
    NzButtonModule,
    ReactiveFormsModule,
    NzFormModule,
  ],
  providers: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
  ],
  exports: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
  ],
})
export class ModalModule { }
