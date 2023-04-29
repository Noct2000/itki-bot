import { Component } from '@angular/core';
import {
  UntypedFormBuilder,
  UntypedFormGroup,
  Validators
} from '@angular/forms';
import { NzModalService } from 'ng-zorro-antd/modal';
import { CuratorService } from '../../service/curator.service';
import { CuratorRequestDto } from '../../dto/curator-request-dto';

@Component({
  selector: 'app-create-curator-modal',
  templateUrl: './create-curator-modal.component.html',
  styleUrls: ['./create-curator-modal.component.scss']
})
export class CreateCuratorModalComponent {
  curatorForm: UntypedFormGroup;

  nameAutoTips: Record<string, Record<string, string>> = {
    default: {
      required: 'Введіть будь ласка ім\'я',
      maxlength: 'Ім\'я не може бути довшим за 30 символів'
    }
  };

  lastNameAutoTips: Record<string, Record<string, string>> = {
    default: {
      required: 'Введіть будь ласка прізвище',
      maxlength: 'Прізвище не може бути довшим за 30 символів'
    }
  };

  additionalNameAutoTips: Record<string, Record<string, string>> = {
    default: {
      required: 'Введіть будь ласка по батькові',
      maxlength: 'По батькові не може бути довшим за 30 символів'
    }
  };

  departmentAutoTips: Record<string, Record<string, string>> = {
    default: {
      required: 'Введіть будь ласка назву вашої кафедри',
      maxlength: 'Назва кафедри не може бути довша за 100 сімволів'
    }
  };

  positionAutoTips: Record<string, Record<string, string>> = {
    default: {
      required: 'Введіть будь ласка вагу посаду',
      maxlength: 'Назва посади не може бути довша за 30 сімволів'
    }
  };

  constructor(
    private formBuilder: UntypedFormBuilder,
    private nzModalService: NzModalService,
    private curatorService: CuratorService,
  ) {
    this.curatorForm = this.formBuilder.group({
      name: ['', [
        Validators.required,
        Validators.maxLength(30),
      ]],
      lastName: ['', [
        Validators.required,
        Validators.maxLength(30)
      ]],
      additionalName: ['', [
        Validators.required,
        Validators.maxLength(30)
      ]],
      department: ['', [
        Validators.required,
        Validators.maxLength(100)
      ]],
      position: ['', [
        Validators.required,
        Validators.maxLength(20)
      ]],
    });
  }

  showModal(): void {
    this.nzModalService.closeAll();
    this.nzModalService.create({
      nzTitle: 'Додавання куратора',
      nzWidth: '60%',
      nzMaskClosable: false,
      nzOnCancel: () => this.handleCancel(),
      nzOnOk: () => this.handleOk(),
      nzContent: CreateCuratorModalComponent,
      nzClosable: false,
    });
  }

  handleOk(): void {
    if (this.curatorForm.valid) {
      const {
        name,
        lastName,
        additionalName,
        department,
        position,
      } = this.curatorForm.value;
      this.curatorService.save(new CuratorRequestDto(
        name.trim(),
        lastName.trim(),
        additionalName.trim(),
        department.trim(),
        position.trim(),
      ))
        .subscribe();
      this.nzModalService.closeAll();
    } else {
      Object.values(this.curatorForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  handleCancel(): void {
    this.nzModalService.closeAll();
  }
}
