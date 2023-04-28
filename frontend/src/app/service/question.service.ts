import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable, tap, withLatestFrom} from "rxjs";
import {Question} from "../model/question";
import {environment} from "../../environments/environment";
import {QuestionRequestDto} from "../dto/question-request-dto";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private readonly baseUrl = environment.apiBaseUrl;
  private readonly questionsUrl = `${this.baseUrl}/questions`;

  private questions$$ = new BehaviorSubject<Question[]>([]);

  questions$ = this.questions$$.asObservable();

  constructor(
    private httpClient: HttpClient,
    ) { }

  loadQuestions(): Observable<Question[]> {
    return this.httpClient.get<Question[]>(this.questionsUrl)
      .pipe(
        tap(
        (questions) => {
          this.questions$$.next(questions);
        }
      )
    );
  }

  save(questionRequestDto: QuestionRequestDto) {
    return this.httpClient.post<Question>(
      this.questionsUrl,
      questionRequestDto
    )
      .pipe(
        withLatestFrom(this.questions$$),
        tap(
          ([newQuestion, questions]) => {
            this.questions$$.next([...questions, newQuestion]);
          }
        )
      );
  }

  delete(id: number) {
    return this.httpClient.delete<void>(
      `${this.questionsUrl}/${id}`,
    )
      .pipe(
        withLatestFrom(this.questions$$),
        tap(
          ([_, questions]) => {
            this.questions$$.next(
              questions.filter(question => question.questionId !== id)
            );
          }
        )
      );
  }
}
