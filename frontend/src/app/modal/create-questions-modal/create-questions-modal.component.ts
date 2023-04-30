import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuestionService } from '../../service/question.service';
import { QuestionRequestDto } from '../../dto/question-request-dto';

@Component({
  selector: 'app-create-questions-modal',
  templateUrl: './create-questions-modal.component.html',
  styleUrls: ['./create-questions-modal.component.scss']
})
export class CreateQuestionsModalComponent implements OnInit {
  questionForm!: FormGroup;
  loading = false;

  constructor(
    private nzModalService: NzModalService,
    private formBuilder: FormBuilder,
    private questionService: QuestionService,
  ) {
  }

  ngOnInit(): void {
    this.questionForm = this.formBuilder.group({
      question: ['', [
        Validators.required,
        Validators.maxLength(500),
        Validators.pattern(/^(?!\s*$).+/),
      ]],
      answer: ['', [
        Validators.required,
        Validators.maxLength(500),
        Validators.pattern(/^(?!\s*$).+/),
      ]]
    });
  }

  showModal(): void {
    this.nzModalService.closeAll();
    this.nzModalService.create({
      nzTitle: 'Додавання нового питання',
      nzWidth: '40%',
      nzMaskClosable: false,
      nzOnCancel: () => this.handleCancel(),
      nzOnOk: () => this.handleOk(),
      nzContent: CreateQuestionsModalComponent,
      nzClosable: false,
      nzOkText: 'Додати',
      nzCancelText: 'Скасувати',
      nzOkDisabled: this.isInvalid(),
    });
  }

  handleOk(): void {
    const {question, answer} = this.questionForm.value;
    console.log(`question = '${question.trim()}'`)
    console.log(`answer = '${answer.trim()}'`)
    this.loading = true;
    this.questionService.save(new QuestionRequestDto(question, answer))
      .subscribe(
        () => {
          this.loading = false;
        }
      );
    this.nzModalService.closeAll();
  }

  handleCancel(): void {
    this.nzModalService.closeAll();
  }

  isInvalid(): boolean {
    return this.questionForm ? this.questionForm.invalid : false
  }
}
