import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { NzModalService } from 'ng-zorro-antd/modal';
import { Curator } from '../../model/curator';
import { GroupService } from '../../service/group.service';
import { GroupRequestDto } from '../../dto/group-request-dto';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-create-group-modal',
  templateUrl: './create-group-modal.component.html',
  styleUrls: ['./create-group-modal.component.scss']
})
export class CreateGroupModalComponent implements OnInit {
  curator!: Curator;
  groupForm!: UntypedFormGroup;

  nameAutoTips: Record<string, Record<string, string>> = {
    default: {
      required: 'Введіть будь ласка назву групи',
      maxlength: 'Назва групи не може бути довша за 30 символів',
      pattern: 'Назва не повинна містити пробілів'
    }
  };

  constructor(
    private formBuilder: UntypedFormBuilder,
    private nzModalService: NzModalService,
    private groupService: GroupService,
    private nzMessageService: NzMessageService,
    ) {}

  ngOnInit(): void {
    this.groupForm = this.formBuilder.group({
      name: ['', [
        Validators.required,
        Validators.maxLength(30),
        Validators.pattern('^[^\\s]*$'),
      ]],
    });
  }

  showModal(curator: Curator): void {
    this.nzModalService.closeAll();
    this.nzModalService.create({
      nzTitle: `Додавання групи для ${curator.lastName} ${curator.name} ${curator.additionalName}`,
      nzWidth: '40%',
      nzMaskClosable: false,
      nzOnCancel: () => this.handleCancel(),
      nzOnOk: () => this.handleOk(),
      nzContent: CreateGroupModalComponent,
      nzClosable: false,
      nzComponentParams: {
        curator: curator
      },
    });
  }

  handleOk(): void {
    if (this.groupForm.valid) {
      const { name } = this.groupForm.value;
      this.groupService.save(new GroupRequestDto(name, this.curator.id))
        .subscribe(
          () => this.nzMessageService.success(`Ви успішно створили групу з назвою ${name}`)
        );
    } else {
      Object.values(this.groupForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
    this.nzModalService.closeAll();
  }

  handleCancel(): void {
    this.nzModalService.closeAll();
  }
}
