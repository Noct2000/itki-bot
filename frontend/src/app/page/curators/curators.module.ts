import { NgModule } from '@angular/core';
import {CuratorsRoutingModule} from "./curators-routing.module";
import {CuratorsComponent} from "./curators.component";
import {NzTypographyModule} from "ng-zorro-antd/typography";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzTableModule} from "ng-zorro-antd/table";
import {CommonModule} from "@angular/common";
import {NzIconModule} from "ng-zorro-antd/icon";

@NgModule({
  imports: [CuratorsRoutingModule, NzTypographyModule, NzButtonModule, NzTableModule, CommonModule, NzIconModule],
  declarations: [CuratorsComponent],
  exports: [CuratorsComponent]
})
export class CuratorsModule { }
