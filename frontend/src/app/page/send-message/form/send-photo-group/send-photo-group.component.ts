import { Component, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { NzUploadFile } from 'ng-zorro-antd/upload';
import { NzMessageService } from 'ng-zorro-antd/message';
import { MessageService } from '../../../../service/message.service';

@Component({
  selector: 'app-send-photo-group',
  templateUrl: './send-photo-group.component.html',
  styleUrls: ['./send-photo-group.component.scss']
})
export class SendPhotoGroupComponent implements OnDestroy {
  private subscriptions: Subscription[] = [];
  private filenameMinioIdMap: Map<string, string> = new Map<string, string>();
  uploading = false;
  fileList: NzUploadFile[] = [];

  constructor(
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
    this.uploadToMinio(file);
    return false;
  };


  onRemoveFile = (file: NzUploadFile): boolean => {
    this.filenameMinioIdMap.delete(file.uid);
    return true;
  };

  handleUpload(): void {
    this.uploading = true;
    const filenames: string[] = [... this.filenameMinioIdMap.values()];
    const subscription = this.messageService.sendPhotoGroupWithMinio(filenames).subscribe(
      () => {
        this.uploading = false;
        this.fileList = [];
        this.filenameMinioIdMap.clear();
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
        this.filenameMinioIdMap.set(file.uid, fileResponse.filenames[0]);
      }, (error) => {
        console.log(`error = ${JSON.stringify(error)}`)
      }
    )
  }
}
