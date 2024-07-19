import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { UserModel } from '../model/user-model';
import { rolesModel } from '../model/roles-model';
import { EmployeeDataModel } from '../model/employee-model';

@Injectable({ providedIn: 'root' })
export class EmployeePermissionService {
  //this method must take the user data from local storage
  isClerkSub = new BehaviorSubject<boolean>(false);
  isManagerSub = new BehaviorSubject<boolean>(false);
  isAdminSub = new BehaviorSubject<boolean>(false);

  isClerk: boolean = false;
  isManager: boolean = false;
  isAdmin: boolean = false;
  constructor() {}
  // check if available
  private checkIfUserAvailable(): boolean {
    if (localStorage.getItem('employeeData')) {
      console.log('------------VVVV--------------');
      return true;
    }
    return false;
  }

  //   private allPermissions(): rolesModel[] {
  //     return [
  //       new rolesModel('ROLE_admin', true),
  //       new rolesModel('ROLE_clerk', true),
  //       new rolesModel('ROLE_employee', true),
  //       new rolesModel('ROLE_manager', true),
  //     ];
  //   }

  // get all permissions

  public checkUserAuth() {
    //if empty
    console.log('Check Local Storage Avvailable');
    if (!this.checkIfUserAvailable()) return;
    console.log('Check Local Storage Avvailable YES');

    //get user details from local storage
    console.log('Translating Data');
    const userDetails: EmployeeDataModel = JSON.parse(
      localStorage.getItem('employeeData')!
    );
    console.log('Translating Data YEs');

    //get the permissions from userDetails
    console.log('Roles Data ');
    if (!userDetails.rolesName) return;
    console.log('Roles Data YEs');
    const permissionsArray: rolesModel[] = userDetails.rolesName!;
    console.log(permissionsArray);
    //cycle through all the permissions
    permissionsArray.forEach((per) => {
      switch (per.roleName) {
        case 'ROLE_admin':
          this.isAdminSub.next(true);
          this.isClerk = true;
          break;
        case 'ROLE_manager':
          this.isManagerSub.next(true);
          this.isManager = true;
          break;
        case 'ROLE_clerk':
          this.isClerkSub.next(true);
          this.isAdmin = true;
          break;
        case 'ROLE_employee':
          break;
        default:
          break;
      }
    });
  }
  // radiate permissions throughout the application as auth guard
}
