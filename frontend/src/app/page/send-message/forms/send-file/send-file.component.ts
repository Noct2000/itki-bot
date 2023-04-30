import { Component, OnDestroy, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { NzUploadFile } from 'ng-zorro-antd/upload';
import { HttpClient } from '@angular/common/http';
import { NzMessageService } from 'ng-zorro-antd/message';
import { MessageService } from '../../../../service/message.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-send-file',
  templateUrl: './send-file.component.html',
  styleUrls: ['./send-file.component.scss']
})
export class SendFileComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];
  captionForm!: UntypedFormGroup;
  uploading = false;
  fileList: NzUploadFile[] = [];

  constructor(
    private http: HttpClient,
    private nzMessageService: NzMessageService,
    private messageService: MessageService,
    private formBuilder: UntypedFormBuilder,
) {}

  ngOnInit(): void {
    this.captionForm = this.formBuilder.group({
      caption: [null, [Validators.maxLength(1000)]]
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(
      subscription => subscription.unsubscribe()
    );
  }

  beforeUpload = (file: NzUploadFile): boolean => {
    this.fileList = this.fileList.concat(file);
    return false;
  };

  handleUpload(): void {
    this.uploading = true;
    const caption = this.captionForm.value.caption ? this.captionForm.value.caption : '';
    const subscription = this.messageService.sendFile(caption.trim(), this.fileList[0]).subscribe(
      () => {
        this.uploading = false;
        this.fileList = [];
        this.captionForm.reset();
        this.nzMessageService.success('Ви успішно надіслали повідомлення');
      }
    );
    this.subscriptions.push(subscription);
  }
}
