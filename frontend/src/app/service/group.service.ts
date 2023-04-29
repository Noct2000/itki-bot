import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Observable, tap, withLatestFrom } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Group } from '../model/group';
import { GroupRequestDto } from '../dto/group-request-dto';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  private readonly baseUrl = environment.apiBaseUrl;
  private readonly groupsUrl = `${this.baseUrl}/groups`;

  private groups$$ = new BehaviorSubject<Group[]>([]);

  groups$ = this.groups$$.asObservable();

  constructor(
    private httpClient: HttpClient,
  ) { }

  loadGroups(): Observable<Group[]> {
    return this.httpClient.get<Group[]>(this.groupsUrl)
      .pipe(
        tap(
          (groups) => {
            this.groups$$.next(groups);
          }
        )
      );
  }

  save(groupRequestDto: GroupRequestDto) {
    return this.httpClient.post<Group>(
      this.groupsUrl,
      groupRequestDto
    )
      .pipe(
        withLatestFrom(this.groups$$),
        tap(
          ([newGroup, groups]) => {
            this.groups$$.next([...groups, newGroup]);
          }
        )
      );
  }

  delete(id: number) {
    return this.httpClient.delete<void>(
      `${this.groupsUrl}/${id}`,
    )
      .pipe(
        withLatestFrom(this.groups$$),
        tap(
          ([_, groups]) => {
            this.groups$$.next(
              groups.filter(group => group.id !== id)
            );
          }
        )
      );
  }
}
