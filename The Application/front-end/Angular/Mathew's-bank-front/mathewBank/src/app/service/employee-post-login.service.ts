import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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

  public employeeById = new BehaviorSubject<EmployeeDataModel | null>(null);

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

  //get employee by id
  public getEmployeeById(empId: number) {
    // TODO: assign number as a parameter to the route

    this.http
      .get<EmployeeDataModel>(ApplicationHttpRoutes.GET_EMPLOYEE_BY_ID, {
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      })
      .subscribe({
        next: (employee) => this.employeeById.next(employee.body),
        error: (err) =>
          console.log(err, 'while getting an employee by id from backend'),
      });
  }

  //admin get employee under employee
  public getEmployeesUnderEmployee(empId: number) {
    return this.http.get<EmployeeDataModel[]>(
      ApplicationHttpRoutes.GET_employee_UNDER_EMPLOYEE,
      {
        params: new HttpParams().append('employeeId', empId),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      }
    );
  }
}
