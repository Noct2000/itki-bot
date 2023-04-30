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
import { CreateGroupModalComponent } from './create-group-modal/create-group-modal.component';
import {NzMessageServiceModule} from "ng-zorro-antd/message";


@NgModule({
  declarations: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
    CreateCuratorModalComponent,
    CreateGroupModalComponent,
  ],
  imports: [
    NzModalModule,
    NzTypographyModule,
    NzFormModule,
    NzInputModule,
    NzButtonModule,
    ReactiveFormsModule,
    NzFormModule,
    NzMessageServiceModule,
  ],
  providers: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
    CreateCuratorModalComponent,
    CreateGroupModalComponent,
  ],
  exports: [
    ExitModalComponent,
    CreateQuestionsModalComponent,
    CreateCuratorModalComponent,
    CreateGroupModalComponent,
  ],
})
export class ModalModule { }
