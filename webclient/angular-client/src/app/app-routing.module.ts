import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component'
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientDetailComponent } from './patient-detail/patient-detail.component';
import { LaboratoryresultComponent } from './laboratoryresult/laboratoryresult.component';
import { LaboratoryresultdetailComponent } from './laboratoryresultdetail/laboratoryresultdetail.component';

const routes: Routes = [
  { path: 'profile', component: UserProfileComponent },
  { path: 'results', component: LaboratoryresultComponent },
  { path: 'results/:id', component: LaboratoryresultdetailComponent },
  { path: 'patients', component: PatientListComponent },
  { path: 'patients/:svnr', component: PatientDetailComponent },
  
  { path: '', component: WelcomeComponent },
  { path: '**', component: WelcomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
