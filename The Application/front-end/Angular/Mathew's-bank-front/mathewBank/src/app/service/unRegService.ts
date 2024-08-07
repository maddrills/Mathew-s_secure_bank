import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationHttpRoutes } from '../constants/http-routes';
import { UserModel } from '../model/user-model';
import { BehaviorSubject } from 'rxjs';
import { EmployeeDataModel } from '../model/employee-model';
import { Router } from '@angular/router';
import { BranchModel } from '../model/branch-model';

@Injectable({ providedIn: 'root' })
export class UnRegService {
  //initiate Http service
  constructor(private http: HttpClient, private router: Router) {}

  public employeeData = new BehaviorSubject<EmployeeDataModel | null>(null);
  public userDetails = new BehaviorSubject<UserModel | null>(null);
  public logInDetected = new BehaviorSubject<boolean>(false);
  public bankUserLoggedIn = new BehaviorSubject<boolean>(false);
  public employeeIsLoggedIn = new BehaviorSubject<boolean>(false);

  public commonUserLogin(username: string, password: string): void {
    console.log(username, password);

    //check if user is a bank employee or user
    // employee starts with a number
    //and user starts with a character

    let employeeId = Number(username);

    //employee detected
    if (employeeId) {
      console.log('Its a number');
      this.employeeLogin(employeeId, password).subscribe({
        next: (employee) => {
          console.log(employee);
          const resultBody = employee.body;
          const resultBodyString = JSON.stringify(employee.body);

          localStorage.setItem('employeeData', resultBodyString);
          this.logInDetected.next(true);
          this.bankUserLoggedIn.next(true);
          this.employeeIsLoggedIn.next(true);
          //on success navigate out to employee
          this.router.navigate(['employee-welcome']);
        },
        error: (e) => console.log(e),
      });
      return;
    }
    //then its a user
    this.userLogin(username, password).subscribe({
      next: (userDetailsBackEnd) => {
        const resultBody = userDetailsBackEnd.body;
        //update the behaviorSubject of type user
        this.userDetails.next(
          new UserModel(
            resultBody?.userId,
            resultBody?.userName,
            resultBody?.branchId,
            resultBody?.fullName,
            resultBody?.phoneNumber,
            resultBody?.dateOfBerth,
            resultBody?.age,
            resultBody?.email,
            resultBody?.userAccountDTO,
            resultBody?.rolesDto
          )
        );
        const resultBodyString = JSON.stringify(resultBody);
        localStorage.setItem('activeUser', resultBodyString);
        //navigate to user
        this.router.navigate(['user-welcome']);
      },
      error: (e) => console.log(e),
    });

    this.userDetails.subscribe(
      //if the details has user as permission
      (userDetails) => {
        console.log('User Service here');
        console.log(userDetails);

        userDetails?.rolesDto?.forEach((roles) => {
          this.logInDetected.next(roles.roleName == 'ROLE_user');
        });

        console.log('the Role name is');
        userDetails?.rolesDto?.forEach((a) => console.log(a.roleName));
      }
    );
  }

  //user login data
  private userLogin(employeeId: string, password: string) {
    //make an http get request for userLogin login
    return this.http.get<UserModel>(ApplicationHttpRoutes.LOGIN_ROUTE_USER, {
      headers: this.basicAuthCredentialsBuilder(employeeId, password),
      observe: 'response',
      //send all relevant cookeys
      withCredentials: true,
    });
  }

  //employee login data
  private employeeLogin(employeeId: number, password: string) {
    //make an http get request for employee login
    return this.http.get<any>(ApplicationHttpRoutes.LOGIN_ROUTE_EMPLOYEE, {
      headers: this.basicAuthCredentialsBuilder(employeeId, password),
      observe: 'response',
      //send all relevant cookeys
      withCredentials: true,
    });
  }

  //common basic auth header builder
  private basicAuthCredentialsBuilder(
    employeeOrUserId: number | string,
    password: string
  ): HttpHeaders {
    //builds the appropriate format for basic auth
    return new HttpHeaders().set(
      'Authorization',
      'Basic ' + window.btoa(employeeOrUserId + ':' + password)
    );
  }

  //common logout route
  //needs modification when XSRF token is involved
  public logUserOut() {
    console.log(ApplicationHttpRoutes.LOGOUT);

    return this.http
      .post(ApplicationHttpRoutes.LOGOUT, null, {
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
        responseType: 'text',
      })
      .subscribe({
        next: (nextIng) => {
          console.log(nextIng);
          this.logInDetected.next(false);
          this.bankUserLoggedIn.next(false);
          this.employeeIsLoggedIn.next(false);
          this.employeeIsLoggedIn.next(false);
          this.userDetails.next(null);
        },
        error: (errorIng) => console.log(errorIng, 'Error Logging out'),
      });
  }

  public getCountryNames() {
    console.log(ApplicationHttpRoutes.GET_COUNTRY_S);

    return this.http.get<string[]>(ApplicationHttpRoutes.GET_COUNTRY_S, {
      observe: 'response',
      //send all relevant cookeys
      withCredentials: true,
      //responseType: 'text',
    });
  }

  public getStateNames(countryName: string) {
    console.log(ApplicationHttpRoutes.GET_STATE_S);

    return this.http.get<string[]>(ApplicationHttpRoutes.GET_STATE_S, {
      params: new HttpParams().set('country', countryName),
      observe: 'response',
      //send all relevant cookeys
      withCredentials: true,
      //responseType: 'text',
    });
  }

  public getBranchByCountryAndState(country: string, state: string) {
    console.log(ApplicationHttpRoutes.GET_STATE_S);

    return this.http.get<BranchModel[]>(
      ApplicationHttpRoutes.GET_STATE_AND_COUNTRY,
      {
        params: new HttpParams().set('country', country).set('state', state),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
        //responseType: 'text',
      }
    );
  }

  public applyForAnAccount(
    branchId: number,
    fullName: string,
    phoneNumber: string,
    dateOfBirth: string,
    email: string,
    age: number
  ) {
    console.log(ApplicationHttpRoutes.GET_STATE_S);

    return this.http.post<any>(
      ApplicationHttpRoutes.REGISTER_USER,
      {
        fullName: fullName,
        phoneNumber: phoneNumber,
        dateOfBirth: dateOfBirth,
        age: age,
        email: email,
      },
      {
        params: new HttpParams().set('branchId', branchId),
        observe: 'response',
        //send all relevant cookeys
        withCredentials: true,
        //responseType: 'text',
      }
    );
  }

  public checkIfUserIsLoggedIn() {
    //first time submit ***
    const empData: EmployeeDataModel | null = JSON.parse(
      localStorage.getItem('employeeData')!
    );

    if (!localStorage.getItem('activeUser')) {
      this.userDetails.next(null);
      this.logInDetected.next(false);
      this.bankUserLoggedIn.next(false);
      return;
    }
    //bank user presence
    const resultBodyString: UserModel = JSON.parse(
      localStorage.getItem('activeUser')!
    );
    this.userDetails.next(resultBodyString);
    this.logInDetected.next(true);
    this.bankUserLoggedIn.next(true);
  }
}
