import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import {TelegramSendFileRequestDto} from "../dto/telegram-send-file-request-dto";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private readonly apiUrl = environment.apiBaseUrl;
  private readonly sendTextMessageUrl = `${this.apiUrl}/tg/broadcast/text`;
  private readonly sendAsyncTextMessageUrl = `${this.apiUrl}/tg/broadcast/text`;
  private readonly sendPhotoMessageUrl = `${this.apiUrl}/tg/broadcast/photo`;
  private readonly sendPhotoMessageWithMinioUrl = `${this.apiUrl}/tg/v2/broadcast/photo`;
  private readonly sendDocumentMessageUrl = `${this.apiUrl}/tg/broadcast/file`;
  private readonly sendDocumentMessageWithMinioUrl = `${this.apiUrl}/tg/v2/broadcast/file`;
  private readonly sendPhotoGroupMessageUrl = `${this.apiUrl}/tg/broadcast/mediaGroup/photo`;
  private readonly sendPhotoGroupMessageWithMinioUrl = `${this.apiUrl}/tg/v2/broadcast/mediaGroup/photo`;
  private readonly uploadToMinioUrl = `${this.apiUrl}/minio/upload`;


  constructor(
    private httpClient: HttpClient,
  ) { }

  sendTextMessage(text: string): Observable<void> {
    return this.httpClient.post<void>(
      this.sendTextMessageUrl,
      text,
      );
  }

  sendAsyncTextMessage(text: string): Observable<void> {
    return this.httpClient.post<void>(
      this.sendAsyncTextMessageUrl,
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

  sendPhotoWithMinio(text: string, photo: string): Observable<void> {
    const requestDto = new TelegramSendFileRequestDto(text, [photo])
    return this.httpClient.post<void>(
      this.sendPhotoMessageWithMinioUrl,
      requestDto
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

  sendFileWithMinio(text: string, document: string): Observable<void> {
    const requestDto = new TelegramSendFileRequestDto(text, [document])
    return this.httpClient.post<void>(
      this.sendDocumentMessageWithMinioUrl,
      requestDto
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

  sendPhotoGroupWithMinio(photos: string[]): Observable<void> {
    const requestDto = new TelegramSendFileRequestDto('', [... photos])
    return this.httpClient.post<void>(
      this.sendPhotoGroupMessageWithMinioUrl,
      requestDto
    );
  }

  uploadToMinio(file: any): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    return this.httpClient.post<string>(
      this.uploadToMinioUrl,
      formData
    )
  }
}
