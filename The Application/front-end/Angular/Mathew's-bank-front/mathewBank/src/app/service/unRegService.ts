import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationHttpRoutes } from '../constants/http-routes';

@Injectable({ providedIn: 'root' })
export class UnRegService {
  //initiate Http service
  constructor(private http: HttpClient) {}

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
        next: (a) => console.log(a),
        error: (e) => console.log(e),
      });
      return;
    }
    //then its a user
    this.userLogin(username, password).subscribe({
      next: (a) => console.log(a),
      error: (e) => console.log(e),
    });
  }

  //user login data
  private userLogin(employeeId: string, password: string) {
    //make an http get request for userLogin login
    return this.http.get<any>(ApplicationHttpRoutes.LOGIN_ROUTE_USER, {
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
}
