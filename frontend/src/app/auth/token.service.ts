import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { COOKIE } from '../../constants/cookie.constants';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  constructor(private cookieService: CookieService) { }

  deleteTokens(): void {
    this.cookieService.delete(COOKIE.authToken, '/');
    this.cookieService.delete(COOKIE.refreshToken, '/');
  }

  saveTokens(authToken: string, refreshToken: string): void {
    this.deleteTokens();
    this.cookieService.set(COOKIE.authToken, authToken, 1, '/');
    this.cookieService.set(COOKIE.refreshToken, refreshToken, 1, '/');
  }

  public getAccessToken(): string {
    return this.cookieService.get(COOKIE.authToken);
  }

  public getRefreshToken(): string {
    return this.cookieService.get(COOKIE.refreshToken);
  }
}
