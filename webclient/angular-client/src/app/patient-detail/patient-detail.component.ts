import { Component, OnInit } from '@angular/core';
import { Patient } from '../entity/patient';
import { PatientService } from '../patient.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-patient-detail',
  templateUrl: './patient-detail.component.html',
  styleUrls: ['./patient-detail.component.less']
})
export class PatientDetailComponent implements OnInit {

  patient: Patient;

  constructor(
    private service : PatientService,
    private route: ActivatedRoute) { }

  getPatient(): void {
    const svnr = +this.route.snapshot.paramMap.get('svnr');
    this.service.getPatient(svnr).subscribe(result=>{
      this.patient=result;
    })
  }

  ngOnInit() {
    this.getPatient();
  }

}
