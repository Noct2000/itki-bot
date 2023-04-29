import { NgModule } from '@angular/core';
import { ExitModalComponent } from './exit-modal/exit-modal.component';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { CreateQuestionsModalComponent } from './create-questions-modal/create-questions-modal.component';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { ReactiveFormsModule } from '@angular/forms';
import { CreateCuratorModalComponent } from './create-curator-modal/create-curator-modal.component';


@NgModule({
  declarations: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
    CreateCuratorModalComponent,
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
    CreateCuratorModalComponent,
  ],
  exports: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
    CreateCuratorModalComponent,
  ],
})
export class ModalModule { }
