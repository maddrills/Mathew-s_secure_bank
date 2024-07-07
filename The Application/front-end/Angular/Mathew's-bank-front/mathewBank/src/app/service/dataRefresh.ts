import { Injectable } from '@angular/core';
import { EmployeeDataModel } from '../model/employee-model';
import { UnRegService } from './unRegService';

@Injectable({ providedIn: 'root' })
export class RefreshDataFetcherService {
  constructor(private userRefresh: UnRegService) {}

  public checkIfEmployeeDataAvailable() {
    const empData: EmployeeDataModel | null = JSON.parse(
      localStorage.getItem('employeeData')!
    );

    if (empData != null) {
      this.userRefresh.userIsLoggedIn.next(true);
    }

    console.log('------------------------------', empData);
    this.userRefresh.employeeData.next(
      new EmployeeDataModel(
        empData?.empId,
        empData?.reportsTo,
        empData?.branchId,
        empData?.empDetailsId,
        empData?.phone_number,
        empData?.full_name,
        empData?.email,
        empData?.dateOfBirth,
        empData?.salary,
        empData?.salaryAccount,
        empData?.branchName,
        empData?.rolesName
      )
    );
  }
}
