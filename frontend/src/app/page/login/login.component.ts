import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { LoginRequestDto } from '../../auth/login-request-dto';
import { TokenService } from '../../auth/token.service';
import { LoginResponseDto } from '../../auth/login-response-dto';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ROUTES } from '../../../constants/routes.constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  showPassword = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private nzMessageService: NzMessageService,
    private tokenService: TokenService,
    ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }

  getValidationStatus(controlName: string): string {
    const control = this.loginForm.get(controlName);
    if (control) {
      return control.touched && control.invalid ? 'error' : control.dirty ? 'success' : '';
    }
    return 'error';
  }


  onSubmit(): void {
    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.value;
      this.authService.authenticate(new LoginRequestDto(username, password)).subscribe(
        (loginResponseDto: LoginResponseDto) => {
          this.tokenService.saveTokens(loginResponseDto.token, loginResponseDto.refreshToken)
          window.location.href = ROUTES.menu
        },
        () => {
          this.nzMessageService.error('Невірний логін або пароль, спробуйте знов')
        },
      )
    } else {
      Object.values(this.loginForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
