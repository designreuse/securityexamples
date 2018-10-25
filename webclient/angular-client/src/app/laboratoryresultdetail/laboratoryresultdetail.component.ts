import { Component, OnInit, Input } from '@angular/core';
import { LaboratoryResult } from '../entity/laboratoryResult';
import { LaboratoryService } from '../laboratory.service';
import { PatientService } from '../patient.service';
import { ActivatedRoute } from '@angular/router';
import { Patient } from '../entity/patient';

@Component({
  selector: 'app-laboratoryresultdetail',
  templateUrl: './laboratoryresultdetail.component.html',
  styleUrls: ['./laboratoryresultdetail.component.css']
})
export class LaboratoryresultdetailComponent implements OnInit {

  result: LaboratoryResult;
  patient: Patient;

  constructor(
    private patientService : PatientService,
    private route: ActivatedRoute,
    private laboratoryService: LaboratoryService
  ) { }

  getResult():void{
    const resultId = +this.route.snapshot.paramMap.get('id');
    this.laboratoryService.getResult(resultId).subscribe(result=>{
      this.result=result;
      this.getPatient(this.result.patientSvnr);
    });
  }

  getPatient(svnr:number):void{
    this.patientService.getPatient(svnr).subscribe(patient=>{
      this.patient=patient;
    });
  }

  ngOnInit() {
    this.getResult();
  }

}
