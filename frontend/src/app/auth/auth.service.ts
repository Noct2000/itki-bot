import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenService} from "./token.service";
import {LoginRequestDto} from "./login-request-dto";
import {LoginResponseDto} from "./login-response-dto";
import {Observable, tap} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.apiBaseUrl;

  private readonly loginUrl = `${this.baseUrl}/login`;
  private readonly refreshUrl = `${this.baseUrl}/refresh`;
  private readonly httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService,
  ) {
  }

  authenticate(loginRequestDto: LoginRequestDto): Observable<LoginResponseDto> {
    return this.httpClient.post<LoginResponseDto>(
      this.loginUrl,
      loginRequestDto,
      this.httpOptions,
    );
  }

  refreshToken(): Observable<LoginResponseDto> {
    return this.httpClient.post<LoginResponseDto>(
      this.refreshUrl,
      this.tokenService.getRefreshToken(),
      this.httpOptions,
    ).pipe(tap((loginResponseDto: LoginResponseDto) => {
      this.tokenService.saveTokens(
        loginResponseDto.token,
        loginResponseDto.refreshToken,
      );
    }));
  }

  logout(): void {
    this.tokenService.deleteTokens();
  }
}
