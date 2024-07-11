import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BranchModel } from '../model/branch-model';
import { ApplicationHttpRoutes } from '../constants/http-routes';
import { EmployeeDataModel } from '../model/employee-model';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BankService {
  public allEmployeesUnderBranch = new BehaviorSubject<
    EmployeeDataModel[] | null
  >(null);

  public managerSubject = new BehaviorSubject<EmployeeDataModel | null>(null);

  constructor(private http: HttpClient) {}

  public getAllBankBranches() {
    return this.http.get<BranchModel[]>(
      ApplicationHttpRoutes.LIST_ALL_BRANCHES,
      {
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  public creaateAbranch(branch: BranchModel) {
    return this.http.post<any>(
      ApplicationHttpRoutes.ADD_A_BRANCH_WITH_OR_WITHOUT_A_MANAGER,
      branch,
      {
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  //get employee by id
  public findEmployeeById(id: number) {
    return this.http
      .get<EmployeeDataModel>(ApplicationHttpRoutes.FIND_EMPLOYEE_BY_ID, {
        params: new HttpParams().set('employeeId', id),
        observe: 'response',
        withCredentials: true,
      })
      .subscribe({
        next: (n) => {
          console.log('GET_ALL_EMPLOYEE_BY_UNDER_BRANCH');
          console.log(n.body);
          this.managerSubject.next(n.body);
        },
        error: (e) => console.log(e),
      });
  }

  public findAllEmployeesUnderBranch(bankBranch: number) {
    console.log('the branch is' + bankBranch);

    this.http
      .get<EmployeeDataModel[]>(
        ApplicationHttpRoutes.GET_ALL_EMPLOYEE_BY_UNDER_BRANCH,
        {
          params: new HttpParams().set('branchId', bankBranch),
          observe: 'response',
          withCredentials: true,
        }
      )
      .subscribe({
        next: (n) => {
          console.log('GET_ALL_EMPLOYEE_BY_UNDER_BRANCH');
          console.log(n.body);
          this.allEmployeesUnderBranch.next(n.body);
        },
        error: (e) => console.log(e),
      });
  }

  public removeEmployeeFrommBranch(id: number) {
    return this.http
      .patch<EmployeeDataModel>(
        ApplicationHttpRoutes.REMOVE_MANAGER_FROM_BRANCH,
        null,
        {
          params: new HttpParams().set('employeeAKAManager', id),
          observe: 'response',
          withCredentials: true,
        }
      )
      .subscribe({
        next: (n) => {
          console.log('REMOVE_MANAGER_FROM_BRANCH');
          console.log(n.body);
          this.managerSubject.next(null);
        },
        error: (e) => console.log(e),
      });
  }

  //add manger to branch ADMIN only
  public putManagerInBranch(id: number, branchId: number) {
    return this.http.put<EmployeeDataModel>(
      ApplicationHttpRoutes.PUT_MANAGER_IN_BANK,
      null,
      {
        params: new HttpParams()
          .set('employeeAKAManager', id)
          .set('branchId', branchId),
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  public removeAClerkFromABranch(id: number, branchId: number) {
    console.log(id, branchId);
    return this.http.patch<EmployeeDataModel>(
      ApplicationHttpRoutes.REMOVE_EMPLOYEE_FROM_BRANCH_Admin,
      null,
      {
        params: new HttpParams().set('clerkId', id).set('bankId', branchId),
        observe: 'response',
        withCredentials: true,
      }
    );
  }
}
