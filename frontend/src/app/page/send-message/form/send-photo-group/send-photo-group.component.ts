import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { NzUploadFile } from 'ng-zorro-antd/upload';
import { HttpClient } from '@angular/common/http';
import { NzMessageService } from 'ng-zorro-antd/message';
import { MessageService } from '../../../../service/message.service';

@Component({
  selector: 'app-send-photo-group',
  templateUrl: './send-photo-group.component.html',
  styleUrls: ['./send-photo-group.component.scss']
})
export class SendPhotoGroupComponent implements OnDestroy {
  private subscriptions: Subscription[] = [];
  uploading = false;
  fileList: NzUploadFile[] = [];

  constructor(
    private http: HttpClient,
    private nzMessageService: NzMessageService,
    private messageService: MessageService,
  ) {}

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
    const subscription = this.messageService.sendPhotoGroup(this.fileList).subscribe(
      () => {
        this.uploading = false;
        this.fileList = [];
        this.nzMessageService.success('Ви успішно надіслали повідомлення');
      }
    );
    this.subscriptions.push(subscription);
  }
}
