import { Injectable } from '@angular/core';
import { LaboratoryResult } from './entity/laboratoryResult';
import { RESULTS } from './mock-laboratory';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class LaboratoryService {

  private baseUrl = 'http://localhost:8080/api/laboratory-results';

  constructor(private http: HttpClient) { }

  getAllLaboratoryResults(): Observable<LaboratoryResult[]>{
    //window.location.href="http://localhost:8080/sso/login";
    //this.http.get<any>("http://localhost:8080/sso/login").subscribe(t => console.log(t));
    return this.http.get<LaboratoryResult[]>(this.baseUrl);
    //return of(RESULTS);
  }

 

}
