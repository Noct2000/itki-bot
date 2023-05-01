import {Component, OnDestroy, OnInit} from '@angular/core';
import {GroupService} from "../../service/group.service";
import {Group} from "../../model/group";
import {Subscription} from "rxjs";
import {Curator} from "../../model/curator";

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.scss']
})
export class GroupsComponent implements OnInit, OnDestroy {
  private groupSubscriptions: Subscription[] = [];
  groups!: Group[];

  constructor(
    private groupService: GroupService,
  ) {
  }

  ngOnInit(): void {
    this.groupSubscriptions.push(this.groupService.loadGroups().subscribe());
    const subscription = this.groupService.groups$.subscribe(
      (groups) => {
        this.groups = groups;
      });

    this.groupSubscriptions.push(subscription);
  }

  ngOnDestroy(): void {
    this.groupSubscriptions.forEach(
      (subscription) => subscription.unsubscribe()
    );
  }

  toStringCurator(curator: Curator): string {
    return `${curator.lastName} ${curator.name} ${curator.additionalName}`;
  }

  delete(groupId: number) {
    this.groupSubscriptions.push(this.groupService.delete(groupId).subscribe());
  }
}
