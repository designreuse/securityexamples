import { Component, OnInit } from '@angular/core';
import { PatientService } from '../patient.service';
import { Patient } from '../entity/patient';
import {Router} from '@angular/router';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.less'],
  providers: [PatientService]
})
export class PatientListComponent implements OnInit {

  list: Patient[];

  constructor(
    private service : PatientService,
    private router: Router) { }

  getAllPatients() : void{
    this.service.getAllPatients().subscribe(results=>{
      this.list=results;
    })
  }

  selectPatient(patient : Patient) : void{
    this.router.navigate(['/patients/'+patient.svnr]);
  }

  ngOnInit() {
    this.getAllPatients();
  }

}
