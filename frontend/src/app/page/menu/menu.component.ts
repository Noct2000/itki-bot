import { Component } from '@angular/core';
import {ROUTES} from "../../../constants/routes.constants";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  isCollapsed = false;
  readonly QUESTIONS_URL = ROUTES.questions;
  readonly CURATORS_URL = ROUTES.curators;
  readonly TELEGRAM_USERS_URL = ROUTES.telegramUsers;
  readonly SEND_MESSAGE_URL = ROUTES.sendMessage;
}
