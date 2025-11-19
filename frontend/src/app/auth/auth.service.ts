import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { TokenService } from "./token.service";
import { LoginRequestDto } from "./login-request-dto";
import { TokenDto } from "./token-dto";
import { finalize, Observable, tap } from "rxjs";
import { ROUTES } from "../../constants/routes.constants";
import { Router } from "@angular/router";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.apiBaseUrl;

  private readonly loginUrl = `${this.baseUrl}/login`;
  private readonly refreshUrl = `${this.baseUrl}/refresh`;
  private readonly logoutUrl = `${this.baseUrl}/logout`;
  private readonly httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(
    private httpClient: HttpClient,
    private tokenService: TokenService,
    private router: Router,

  ) {
  }

  authenticate(loginRequestDto: LoginRequestDto): Observable<TokenDto> {
    return this.httpClient.post<TokenDto>(
      this.loginUrl,
      loginRequestDto,
      this.httpOptions,
    );
  }

  refreshToken(): Observable<TokenDto> {
    return this.httpClient.post<TokenDto>(
      this.refreshUrl,
      this.tokenService.getRefreshToken(),
      this.httpOptions,
    ).pipe(tap((loginResponseDto: TokenDto) => {
      this.tokenService.saveTokens(
        loginResponseDto.token,
        loginResponseDto.refreshToken,
      );
    }));
  }

  logout(): void {
    const tokenDto: TokenDto = {
      token: this.tokenService.getAccessToken(),
      refreshToken: this.tokenService.getRefreshToken()
    };
    this.httpClient.post<boolean>(this.logoutUrl, tokenDto, this.httpOptions)
      .pipe(
        finalize(() => {
          this.tokenService.deleteTokens();
          this.router.navigate([ROUTES.login]);
        })
      ).subscribe();
  }
}
