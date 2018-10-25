import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from './entity/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private baseUrl = 'http://localhost:8081/api/patients';

  constructor(private http: HttpClient) { }

  getAllPatients(): Observable<Patient[]>{
    return this.http.get<Patient[]>(this.baseUrl);
  }

  getPatient(svnr:number): Observable<Patient>{
    return this.http.get<Patient>(this.baseUrl+"/"+svnr);
  }

}
