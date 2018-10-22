import { Component, OnInit, Input } from '@angular/core';
import { LaboratoryResult } from '../entity/laboratoryResult';

@Component({
  selector: 'app-laboratoryresultdetail',
  templateUrl: './laboratoryresultdetail.component.html',
  styleUrls: ['./laboratoryresultdetail.component.css']
})
export class LaboratoryresultdetailComponent implements OnInit {

  @Input() result: LaboratoryResult;

  constructor() { }

  ngOnInit() {
    /*this.result = {
      id: 1,
      valueA: 123,
      valueB: 456,
      valueC: "saldf",
      valueD: true,
      patient: {
        username: "patient1",
        authorities: undefined
      }

    }*/
  }

}
