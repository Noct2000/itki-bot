import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription, tap} from "rxjs";
import {Question} from "../../model/question";
import {QuestionService} from "../../service/question.service";

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
    console.log(`CREATE`)
  }
}
