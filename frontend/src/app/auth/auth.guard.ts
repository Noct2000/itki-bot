import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { TokenService } from './token.service';
import {ROUTES} from "../../constants/routes.constants";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  private readonly MENU_PAGE_URL = ROUTES.menu;
  private readonly LOGIN_PAGE_URL = '/login';

  constructor(
    private tokenService: TokenService,
    private router: Router,
  ) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    const hasToken = this.tokenService.getAccessToken() !== '';

    if (this.isForbiddenUrl(state.url) && !hasToken) {
      return true;
    }

    if (hasToken) {
      if (this.isForbiddenUrl(state.url)) {
        this.router.navigate([this.MENU_PAGE_URL]);

        return false;
      }

      return true;
    }

    this.router.navigate([this.LOGIN_PAGE_URL]);

    return false;
  }

  private isForbiddenUrl(url: string) {
    return url === this.LOGIN_PAGE_URL;
  }
}
