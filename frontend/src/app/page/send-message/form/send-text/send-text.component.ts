import { Component, OnDestroy, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { MessageService } from '../../../../service/message.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-send-text',
  templateUrl: './send-text.component.html',
  styleUrls: ['./send-text.component.scss']
})
export class SendTextComponent implements OnInit, OnDestroy {
  textForm!: UntypedFormGroup;
  private messageSubscription!: Subscription;

  constructor(
    private formBuilder: UntypedFormBuilder,
    private nzMessageService: NzMessageService,
    private messageService: MessageService,
  ) {}

  ngOnInit(): void {
    this.textForm = this.formBuilder.group({
      text: [null, [Validators.required,
        Validators.maxLength(4000),
        Validators.pattern(/^(?!\s*$).+/),
      ]],
    });
  }

  ngOnDestroy(): void {
    if (this.messageSubscription) {
      this.messageSubscription.unsubscribe();
    }
  }

  sendMessage() {
    if (this.textForm.valid) {
      const { text } = this.textForm.value;
      this.messageSubscription= this.messageService.sendAsyncTextMessage(text.trim()).subscribe(
        () => {
          this.textForm.reset();
          this.nzMessageService.success("Ви успішно надіслали повідомлення")
        }
      );
    } else {
      Object.values(this.textForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
