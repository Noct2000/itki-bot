import { Component } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-exit-nzModalService',
  templateUrl: './exit-modal.component.html',
  styleUrls: ['./exit-modal.component.scss']
})
export class ExitModalComponent {
  constructor(
    private nzModalService: NzModalService,
    private authService: AuthService,
  ) {
  }

  showModal(): void {
    this.nzModalService.closeAll();
    this.nzModalService.confirm({
      nzTitle: 'Вихід з облікового запису',
      nzWidth: '40%',
      nzMaskClosable: false,
      nzOnCancel: () => this.handleCancel(),
      nzOnOk: () => this.handleOk(),
      nzContent: ExitModalComponent,
      nzClosable: false,
      nzOkText: 'Гаразд',
      nzCancelText: 'Скасувати',
    });
  }

  handleOk(): void {
    this.authService.logout();
  }

  handleCancel(): void {
    this.nzModalService.closeAll();
  }
}
