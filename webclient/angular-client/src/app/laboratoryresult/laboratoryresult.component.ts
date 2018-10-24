import { Component, OnInit } from '@angular/core';
import { LaboratoryService } from '../laboratory.service';
import { LaboratoryResult } from '../entity/laboratoryResult';

@Component({
  selector: 'app-laboratoryresult',
  templateUrl: './laboratoryresult.component.html',
  styleUrls: ['./laboratoryresult.component.less'],
  providers: [LaboratoryService]
})
export class LaboratoryresultComponent implements OnInit {

  list: LaboratoryResult[];
  error: any;

  getResults() : void {
    this.laboratoryService.getAllLaboratoryResults().subscribe((results) => {
      this.list=results
    }, (error) => {
      this.error = error;
      console.log(error);
    });
  }

  constructor(private laboratoryService: LaboratoryService) { }

  ngOnInit() {
    this.error=undefined;
    this.getResults();
  }

}
