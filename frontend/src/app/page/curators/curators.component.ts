import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Curator } from '../../model/curator';
import { CuratorService } from '../../service/curator.service';
import { CreateCuratorModalComponent } from '../../modal/create-curator-modal/create-curator-modal.component';
import { CreateGroupModalComponent } from '../../modal/create-group-modal/create-group-modal.component';

@Component({
  selector: 'app-curators',
  templateUrl: './curators.component.html',
  styleUrls: ['./curators.component.scss']
})
export class CuratorsComponent implements OnInit, OnDestroy {
  curators!: Curator[];
  curatorSubscriptions: Subscription[] = [];

  constructor(
    private curatorService: CuratorService,
    private createCuratorModal: CreateCuratorModalComponent,
    private createGroupModalComponent: CreateGroupModalComponent,
  ) {
  }

  ngOnInit(): void {
    this.curatorSubscriptions.push(this.curatorService.loadCurators().subscribe());
    const subscription = this.curatorService.curators$.subscribe(
      (curators) => {
        this.curators = curators;
      });

    this.curatorSubscriptions.push(subscription);
  }

  ngOnDestroy(): void {
    this.curatorSubscriptions.forEach(
      subscription => subscription.unsubscribe()
    );
  }

  showCreateModal() {
    this.createCuratorModal.showModal();
  }

  delete(curatorId: number) {
    this.curatorSubscriptions.push(this.curatorService.delete(curatorId).subscribe());
  }

  addGroup(curator: Curator) {
    this.createGroupModalComponent.showModal(curator);
  }
}
