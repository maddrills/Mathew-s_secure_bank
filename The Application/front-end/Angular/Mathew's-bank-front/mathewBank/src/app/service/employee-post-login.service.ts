import { HttpClient } from '@angular/common/http';
import { ApplicationHttpRoutes } from '../constants/http-routes';
import { Injectable } from '@angular/core';
import { EmployeeDataModel } from '../model/employee-model';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  //all employees
  public allEmployeeData = new BehaviorSubject<EmployeeDataModel[] | null>(
    null
  );

  constructor(private http: HttpClient) {}

  //get all employee basic information from db
  public getAllEmployees() {
    return this.http.get<EmployeeDataModel[]>(
      ApplicationHttpRoutes.GET_ALL_EMPLOYEE_DATA,
      {
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      }
    );
  }
}
