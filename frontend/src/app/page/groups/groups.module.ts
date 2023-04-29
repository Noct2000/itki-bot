import { NgModule } from '@angular/core';
import { GroupsRoutingModule } from './groups-routing.module';
import { GroupsComponent } from './groups.component';
import {NzTypographyModule} from "ng-zorro-antd/typography";
import {NzTableModule} from "ng-zorro-antd/table";
import {CommonModule} from "@angular/common";



@NgModule({
  imports: [GroupsRoutingModule, NzTypographyModule, NzTableModule, CommonModule],
  declarations: [GroupsComponent],
  exports: [GroupsComponent]
})
export class GroupsModule { }
