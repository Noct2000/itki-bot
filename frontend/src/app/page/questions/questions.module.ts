import { NgModule } from '@angular/core';

import { QuestionsRoutingModule } from './questions-routing.module';

import { QuestionsComponent } from './questions.component';


@NgModule({
  imports: [QuestionsRoutingModule],
  declarations: [QuestionsComponent],
  exports: [QuestionsComponent]
})
export class QuestionsModule { }
