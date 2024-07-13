import { CommonModule } from '@angular/common';
import { Component, OnChanges } from '@angular/core';
import { rolesModel } from '../../../model/roles-model';
import { EmployeeService } from '../../../service/employee-post-login.service';

@Component({
  selector: 'app-permission-set',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './permission-set.component.html',
  styleUrl: './permission-set.component.css',
})
export class PermissionSetComponent implements OnChanges {
  public openUp: boolean = false;
  public permissionCount: number = 0;
  //map that hold the position and array ref
  public permissions: Map<number, rolesModel> = new Map();
  public arrayOfPermissions: rolesModel[] = [];
  public subEmployee: boolean = false;
  public clickedReset: boolean = false;

  ngOnChanges() {
    console.log('Change detected');
  }

  constructor(private employeeService: EmployeeService) {
    //reset
    this.employeeService.rolesFromBackend.next(null);

    this.employeeService.authViewActive.subscribe({
      next: (condition) => {
        if (condition) {
          this.subEmployee = condition;
          this.employeeService.rolesToBeRemovedFromBackend.subscribe({
            next: (rolesToBERemoved) => {
              //gets all valid roles from db
              this.employeeService.getAllOfficeRoles();
              this.employeeService.rolesFromBackend.subscribe((roles) => {
                this.reset();
                if (roles != null) {
                  this.arrayOfPermissions = roles;

                  //after that loop through roles and roles to be removed
                  rolesToBERemoved?.forEach((removeRole, index) => {
                    this.permissionCount = rolesToBERemoved.length;
                    this.arrayOfPermissions.forEach((allValidRoles) => {
                      if (removeRole.roleName == allValidRoles.roleName) {
                        allValidRoles.added = true;
                        this.permissions.set(index, allValidRoles);
                      }
                    });
                  });
                }
              });
            },
          });
        } else {
          this.employeeService.getAllOfficeRoles();
          this.employeeService.rolesFromBackend.subscribe((roles) => {
            if (roles != null) {
              this.arrayOfPermissions = roles;
            }
          });
        }
      },
    });

    this.employeeService.rolesToBackend.next(null);
  }
  //TODO: replace with api data
  // public arrayOfPermissions: rolesModel[] = [
  //   new rolesModel('ROLE_employee', false),
  //   new rolesModel('ROLE_clerk', false),
  //   new rolesModel('ROLE_manager', false),
  //   new rolesModel('ROLE_admin', false),
  // ];

  addPermission(role: rolesModel) {
    this.openUp = !this.openUp;
    this.permissions.set(this.permissionCount++, role);
    role.added = true;

    //export all the selected roles
    this.permissions.forEach((value, key, fullArray) => {});
    this.employeeService.rolesToBackend.next(this.permissions);
  }

  click() {
    if (!this.subEmployee) {
      if (!this.clickedReset) {
        this.reset();
        this.clickedReset = true;
      }
    }
    this.openUp = !this.openUp;
    console.log(this.arrayOfPermissions);
  }

  remove(valueInMap: number) {
    //set the array reference in the map to become false if permission removed from map
    const permissions = this.permissions.get(valueInMap);

    if (permissions != null) {
      permissions.added = false;
      this.permissions.delete(valueInMap);
    }
  }

  private reset() {
    // this.arrayOfPermissions = [];
    console.log('---------------Reset all----------------');
    this.arrayOfPermissions.forEach((arr) => (arr.added = false));
    console.log(this.arrayOfPermissions);
  }
}
