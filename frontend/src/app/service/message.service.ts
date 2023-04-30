import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private readonly apiUrl = environment.apiBaseUrl;
  private readonly sendTextMessageUrl = `${this.apiUrl}/tg/broadcast/text`;

  constructor(
    private httpClient: HttpClient,
  ) { }

  sendTextMessage(text: string): Observable<void> {
    return this.httpClient.post<void>(
      this.sendTextMessageUrl,
      text,
      );
  }
}
