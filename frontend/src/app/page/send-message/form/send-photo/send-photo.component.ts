import { Component, OnDestroy, OnInit } from '@angular/core';
import {NzUploadFile} from 'ng-zorro-antd/upload';
import { NzMessageService } from 'ng-zorro-antd/message';
import { MessageService } from '../../../../service/message.service';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-send-photo',
  templateUrl: './send-photo.component.html',
  styleUrls: ['./send-photo.component.scss']
})
export class SendPhotoComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];
  private filename: string = '';
  captionForm!: UntypedFormGroup;
  uploading = false;
  fileList: NzUploadFile[] = [];

  constructor(
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
    this.uploadToMinio(file);
    return false;
  };

  handleUpload(): void {
    this.uploading = true;
    const caption = this.captionForm.value.caption ? this.captionForm.value.caption : '';
    const subscription = this.messageService.sendPhotoWithMinio(caption.trim(), this.filename).subscribe(
      () => {
        this.uploading = false;
        this.fileList = [];
        this.captionForm.reset();
        this.nzMessageService.success('Ви успішно надіслали повідомлення');
      }
    );
    this.subscriptions.push(subscription);
  }

  uploadToMinio(file: NzUploadFile): Subscription {
    this.uploading = true;
    return this.messageService.uploadToMinio(file).subscribe(
      (fileResponse) => {
        this.uploading = false;
        this.filename = fileResponse.filenames[0];
      }, (error) => {
          console.log(`error = ${JSON.stringify(error)}`)
      }
    )
  }
}
