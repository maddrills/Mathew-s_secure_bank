import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ApplicationHttpRoutes } from '../constants/http-routes';
import { Injectable } from '@angular/core';
import { EmployeeDataModel } from '../model/employee-model';
import { BehaviorSubject } from 'rxjs';
import { rolesModel } from '../model/roles-model';
import { applicationsModel } from '../model/applications-model';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  //all employees
  public allEmployeeData = new BehaviorSubject<EmployeeDataModel[] | null>(
    null
  );

  public employeeById = new BehaviorSubject<EmployeeDataModel | null>(null);

  //roles from adder
  public rolesFromBackend = new BehaviorSubject<rolesModel[] | null>(null);
  public rolesToBackend = new BehaviorSubject<Map<number, rolesModel> | null>(
    null
  );
  //auth view for applications under employee
  public authViewActive = new BehaviorSubject<boolean>(false);
  public employeeSelected = new BehaviorSubject<number>(0);

  //roles from remover
  public rolesToBeRemovedFromBackend = new BehaviorSubject<rolesModel[] | null>(
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

  //get employee by id
  public getEmployeeById(empId: number) {
    // TODO: assign number as a parameter to the route

    this.http
      .get<EmployeeDataModel>(ApplicationHttpRoutes.GET_EMPLOYEE_BY_ID, {
        params: new HttpParams().append('employeeId', empId),
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

  //admin get employee under employee
  public getAllOfficeRoles() {
    this.http
      .get<rolesModel[]>(ApplicationHttpRoutes.GET_ALL_OFFICE_PERMISSIONS, {
        //params: new HttpParams().append('employeeId', empId),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      })
      .subscribe({
        next: (roles) => {
          console.log(roles.body);
          this.rolesFromBackend.next(roles.body);
        },
        error: (er) => {},
      });
  }

  public createAnEmployee(
    phone_number: number,
    full_name: string,
    email: string,
    dateOfBirth: string,
    salary: number,
    password: string
  ) {
    console.log('called');
    const rolesArray: rolesModel[] = [];
    //list out all the sending permissions
    this.rolesToBackend.subscribe((permission) => {
      permission?.forEach((value, key, fullArray) => {
        console.log('createAnEmployee Keys');
        console.log(key);
        console.log(value);
        console.log(fullArray);
        rolesArray.push(new rolesModel(value.roleName.slice(5), false));
      });
    });

    console.log('Full array is this');
    console.log(rolesArray);
    console.log('Form info is');
    return this.http.post<any>(
      ApplicationHttpRoutes.POST_AN_EMPLOYEE,
      {
        phone_number,
        full_name,
        email,
        dateOfBirth,
        salary,
        password,
        rolesName: rolesArray,
      },
      {
        //params: new HttpParams().append('employeeId', empId),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      }
    );
  }

  //fetch all user applications

  public fetchAllUserApplications() {
    return this.http.get<applicationsModel[]>(
      ApplicationHttpRoutes.GET_ALL_APPLICATIONS,
      {
        //params: new HttpParams().append('employeeId', empId),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      }
    );
  }

  //fetch applications under user
  public getAllApplicationsUnderMe() {
    return this.http.get<applicationsModel[]>(
      ApplicationHttpRoutes.GET_APPLICATIONS_ASSIGNED_TO_ME,
      {
        //params: new HttpParams().append('employeeId', empId),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      }
    );
  }

  //fetch application data under any employee
  public getAllApplicationsUnderAnyEmployee(employeeId: number) {
    return this.http.get<applicationsModel[]>(
      ApplicationHttpRoutes.GET_APPLICATIONS_ASSIGNED_To_SOMEONE,
      {
        params: new HttpParams().append('employeeId', employeeId),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
      }
    );
  }
}
