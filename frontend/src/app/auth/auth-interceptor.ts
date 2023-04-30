import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpStatusCode,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  BehaviorSubject, Observable, throwError,
} from 'rxjs';
import {
  catchError, filter, retry, switchMap, take,
} from 'rxjs/operators';
import {AuthService} from "./auth.service";
import {TokenService} from "./token.service";
import {LoginResponseDto} from "./login-response-dto";

const refreshPathname = '/refresh';
const loginPathname = '/login'

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private isRefreshing = false;

  private refreshTokenSubject = new BehaviorSubject<string>('');

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.tokenService.getAccessToken();

    if (token != null) {
      req = this.addToken(req, token);
    }

    return next.handle(req).pipe(
      catchError((error) => {
        const url = new URL(error.url).pathname;

        if (req.url.endsWith(loginPathname)) {
          return next.handle(req);
        }

        if (this.isForbiddenStatus(error)
          && url === refreshPathname && this.tokenService.getRefreshToken() !== '') {
          this.authService.logout();

          return throwError(error);
        }

        if (this.isForbiddenStatus(error) && this.tokenService.getRefreshToken() !== '') {
          return this.refreshToken(req, next);
        }
        return throwError(error);
      }),
      retry(3),
    );
  }

  private isForbiddenStatus(error: any) {
    return error instanceof HttpErrorResponse
      && error.status === HttpStatusCode.Forbidden;
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  private refreshToken(
    request: HttpRequest<any>,
    next: HttpHandler,
  ): Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next('');

      return this.authService.refreshToken().pipe(
        switchMap((loginResponseDto: LoginResponseDto) => {
          const newAccessToken = loginResponseDto.token;

          this.refreshTokenSubject.next(newAccessToken);
          this.isRefreshing = false;

          return next.handle(this.addToken(request, newAccessToken));
        }),
      );
    }

    return this.refreshTokenSubject.pipe(
      filter((token) => token !== ''),
      take(1),
      switchMap((token) => next.handle(this.addToken(request, token))),
    );
  }
}
