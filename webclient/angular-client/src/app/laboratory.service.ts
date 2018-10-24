import { Injectable } from '@angular/core';
import { LaboratoryResult } from './entity/laboratoryResult';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LaboratoryService {

  private baseUrl = 'http://localhost:8080/api/laboratory-results';

  constructor(private http: HttpClient) { }

  getAllLaboratoryResults(): Observable<LaboratoryResult[]>{
    return this.http.get<LaboratoryResult[]>(this.baseUrl);
  }

  getResult(id:number): Observable<LaboratoryResult>{
    return this.http.get<LaboratoryResult>(this.baseUrl+"/"+id);
  }

 

}
