import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LaboratoryresultlistComponent } from './laboratoryresultlist/laboratoryresultlist.component';
import { WelcomeComponent } from './welcome/welcome.component'
import { UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
  { path: 'profile', component: UserProfileComponent },
  { path: 'results', component: LaboratoryresultlistComponent },
  
  { path: '', component: WelcomeComponent },
  { path: '**', component: WelcomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
