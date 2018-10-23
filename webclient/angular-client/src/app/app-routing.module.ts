import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LaboratoryresultlistComponent } from './laboratoryresultlist/laboratoryresultlist.component';
import {WelcomeComponent} from './welcome/welcome.component'

const routes: Routes = [
  { path: 'results', component: LaboratoryresultlistComponent },
  { path: '', component: WelcomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
