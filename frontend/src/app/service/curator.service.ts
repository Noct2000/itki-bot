import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable, tap, withLatestFrom} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Curator} from "../model/curator";
import {CuratorRequestDto} from "../dto/curator-request-dto";

@Injectable({
  providedIn: 'root'
})
export class CuratorService {
  private readonly baseUrl = environment.apiBaseUrl;
  private readonly curatorsUrl = `${this.baseUrl}/curators`;

  private curators$$ = new BehaviorSubject<Curator[]>([]);

  curators$ = this.curators$$.asObservable();

  constructor(
    private httpClient: HttpClient,
  ) { }

  loadCurators(): Observable<Curator[]> {
    return this.httpClient.get<Curator[]>(this.curatorsUrl)
      .pipe(
        tap(
          (curators) => {
            this.curators$$.next(curators);
          }
        )
      );
  }

  save(curatorRequestDto: CuratorRequestDto) {
    return this.httpClient.post<Curator>(
      this.curatorsUrl,
      curatorRequestDto
    )
      .pipe(
        withLatestFrom(this.curators$$),
        tap(
          ([newCurator, curators]) => {
            this.curators$$.next([...curators, newCurator]);
          }
        )
      );
  }

  delete(id: number) {
    return this.httpClient.delete<void>(
      `${this.curatorsUrl}/${id}`,
    )
      .pipe(
        withLatestFrom(this.curators$$),
        tap(
          ([_, curators]) => {
            this.curators$$.next(
              curators.filter(curator => curator.id !== id)
            );
          }
        )
      );
  }
}
