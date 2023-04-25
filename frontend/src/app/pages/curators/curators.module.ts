import { NgModule } from '@angular/core';
import {CuratorsRoutingModule} from "./curators-routing.module";
import {CuratorsComponent} from "./curators.component";

@NgModule({
  imports: [CuratorsRoutingModule],
  declarations: [CuratorsComponent],
  exports: [CuratorsComponent]
})
export class CuratorsModule { }
