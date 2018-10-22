import { Component, OnInit } from '@angular/core';
import { LaboratoryResult } from '../entity/laboratoryResult';
import { LaboratoryService } from '../laboratory.service';

@Component({
  selector: 'app-laboratoryresultlist',
  templateUrl: './laboratoryresultlist.component.html',
  styleUrls: ['./laboratoryresultlist.component.css'],
  providers: [LaboratoryService]
})
export class LaboratoryresultlistComponent implements OnInit {

  list: LaboratoryResult[];
  selectedResult: LaboratoryResult;
  selectResult(result: LaboratoryResult) : void{
    this.selectedResult=result;
  } 
  getResults() : void {
    this.laboratoryService.getAllLaboratoryResults().subscribe((results) => {
      this.list=results
    });
  }

  constructor(private laboratoryService: LaboratoryService) { }

  ngOnInit() {
    this.getResults();
  }

}
