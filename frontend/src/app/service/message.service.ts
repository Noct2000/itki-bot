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
  private readonly sendPhotoMessageUrl = `${this.apiUrl}/tg/broadcast/photo`;
  private readonly sendDocumentMessageUrl = `${this.apiUrl}/tg/broadcast/file`;
  private readonly sendPhotoGroupMessageUrl = `${this.apiUrl}/tg/broadcast/mediaGroup/photo`;


  constructor(
    private httpClient: HttpClient,
  ) { }

  sendTextMessage(text: string): Observable<void> {
    return this.httpClient.post<void>(
      this.sendTextMessageUrl,
      text,
      );
  }

  sendPhoto(text: string, photo: any): Observable<void> {
    const formData = new FormData();
    formData.append('caption', text);
    formData.append('photo', photo);
    return this.httpClient.post<void>(
      this.sendPhotoMessageUrl,
      formData
    );
  }

  sendFile(text: string, document: any): Observable<void> {
    const formData = new FormData();
    formData.append('caption', text);
    formData.append('document', document);
    return this.httpClient.post<void>(
      this.sendDocumentMessageUrl,
      formData
    );
  }

  sendPhotoGroup(photos: any[]): Observable<void> {
    const formData = new FormData();
    photos.forEach(
      photo => formData.append('photo', photo)
    );
    return this.httpClient.post<void>(
      this.sendPhotoGroupMessageUrl,
      formData
    );
  }
}
