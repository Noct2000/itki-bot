import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import {AuthService} from "../../auth/auth.service";
import {LoginRequestDto} from "../../auth/login-request-dto";
import {TokenService} from "../../auth/token.service";
import {LoginResponseDto} from "../../auth/login-response-dto";
import {NzMessageService} from "ng-zorro-antd/message";
import {ROUTES} from "../../../constants/routes.constants";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: UntypedFormGroup;

  constructor(
    private fb: UntypedFormBuilder,
    private authService: AuthService,
    private tokenService: TokenService,
    private nzMessageService: NzMessageService,
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  submitForm(): void {
    if (this.loginForm.valid) {
    } else {
      Object.values(this.loginForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
    const { userName, password } = this.loginForm.value;
    this.authService.authenticate(new LoginRequestDto(userName, password)).subscribe(
      (loginResponseDto: LoginResponseDto) => {
        this.tokenService.saveTokens(loginResponseDto.token, loginResponseDto.refreshToken)
        window.location.href = ROUTES.menu
      },
      () => {
        this.nzMessageService.error('Невірний логін або пароль, спробуйте знов')
      },
    )
  }
}
