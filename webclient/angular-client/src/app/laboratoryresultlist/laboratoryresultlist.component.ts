import { Component, OnInit, Input } from '@angular/core';
import { LaboratoryResult } from '../entity/laboratoryResult';

@Component({
  selector: 'app-laboratoryresultlist',
  templateUrl: './laboratoryresultlist.component.html',
  styleUrls: ['./laboratoryresultlist.component.css']
})
export class LaboratoryresultlistComponent implements OnInit {

  @Input() list: LaboratoryResult[];
  selectedResult: LaboratoryResult;

  constructor() { }

  ngOnInit() {
  }

  selectResult(result: LaboratoryResult) : void{
    this.selectedResult=result;
  } 

}
