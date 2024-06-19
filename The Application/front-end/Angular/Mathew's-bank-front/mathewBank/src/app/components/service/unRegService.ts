import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

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
  }

  //employee login data
  private employeeLogin(employeeId: number, password: string) {
    //make an http get request for employee login
    return this.http.get<any>('http://localhost:8080/employee/employee-login', {
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
