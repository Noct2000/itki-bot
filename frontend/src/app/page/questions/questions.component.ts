import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Question } from '../../model/question';
import { QuestionService } from '../../service/question.service';
import { CreateQuestionsModalComponent } from '../../modal/create-questions-modal/create-questions-modal.component';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
export class QuestionsComponent implements OnInit, OnDestroy {
  questions!: Question[];
  questionSubscriptions: Subscription[] = [];

  constructor(
    private questionService: QuestionService,
    private createQuestionModal: CreateQuestionsModalComponent,
  ) {
  }

  ngOnInit(): void {
    this.questionSubscriptions.push(this.questionService.loadQuestions().subscribe());
    const subscription = this.questionService.questions$.subscribe(
      (questions) => {
        this.questions = questions;
      });

    this.questionSubscriptions.push(subscription);
  }

  ngOnDestroy(): void {
    this.questionSubscriptions.forEach(
      (subscription) => subscription.unsubscribe()
    );
  }

  delete(id: number) {
    this.questionSubscriptions.push(this.questionService.delete(id).subscribe());
  }

  showCreateModal() {
    this.createQuestionModal.showModal();
  }
}
