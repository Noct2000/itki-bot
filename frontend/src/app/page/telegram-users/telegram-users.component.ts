import { Component, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { TelegramUser } from '../../model/telegram-user';
import { TelegramUserService } from '../../service/telegram-user.service';

@Component({
  selector: 'app-telegram-users',
  templateUrl: './telegram-users.component.html',
  styleUrls: ['./telegram-users.component.scss'],
})
export class TelegramUsersComponent implements OnInit, OnDestroy {
  telegramUsers$!: Observable<TelegramUser[]>;
  telegramUsers!: TelegramUser[];
  telegramUsersSubscription!: Subscription;

  constructor(
    private telegramUserService: TelegramUserService,
  ) {
  }

  ngOnInit(): void {
    this.telegramUsers$ = this.telegramUserService.getAll();
    this.telegramUsersSubscription = this.telegramUsers$.subscribe(
      tgUsers => this.telegramUsers = tgUsers
    );
  }

  ngOnDestroy(): void {
    this.telegramUsersSubscription.unsubscribe();
  }

}
