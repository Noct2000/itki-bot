import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TelegramUser } from '../model/telegram-user';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TelegramUserService {
  private readonly baseUrl = environment.apiBaseUrl;

  constructor(
  private  httpClient: HttpClient,
  ) { }

  getAll(): Observable<TelegramUser[]> {
    return this.httpClient.get<TelegramUser[]>(`${this.baseUrl}/telegram-users`)
  }
}
