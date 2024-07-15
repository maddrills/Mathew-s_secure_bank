import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, OnDestroy } from '@angular/core';
import { rolesModel } from '../../../model/roles-model';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-permission-set',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './permission-set.component.html',
  styleUrl: './permission-set.component.css',
})
export class PermissionSetComponent implements OnChanges, OnDestroy {
  public openUp: boolean = false;
  public permissionCount: number = 0;
  //map that hold the position and array ref
  public permissions: Map<number, rolesModel> = new Map();
  public arrayOfPermissions: rolesModel[] = [];
  public subEmployee: boolean = false;
  public clickedReset: boolean = false;

  //sub tracker
  private subs: Subscription[] = [];

  ngOnChanges() {
    console.log('Change detected');
  }

  constructor(private employeeService: EmployeeService) {
    console.log('----Change detected Constructor--------');
    //reset
    this.employeeService.rolesFromBackend.next(null);
    //gets all valid roles from db
    this.employeeService.getAllOfficeRoles();

    this.subs.push(
      this.employeeService.authViewActive.subscribe({
        next: (condition) => {
          console.log('CHANGE 1');
          if (condition) {
            this.subEmployee = condition;
            //RESET to allow for a new permission set
            this.reset();
            //get roles to be removed
            this.employeeService.rolesToBeRemovedFromBackend.subscribe({
              next: (rolesToBERemoved) => {
                this.employeeService.rolesFromBackend.subscribe((roles) => {
                  console.log('CHANGE 2');
                  console.log(rolesToBERemoved);
                  console.log(roles);
                  if (roles != null) {
                    this.arrayOfPermissions = roles;
                    console.log('CHANGE 3');
                    //after that loop through roles and roles to be removed
                    rolesToBERemoved?.forEach((removeRole, index) => {
                      //set the counter to the users permission len
                      this.permissionCount = rolesToBERemoved.length;
                      this.arrayOfPermissions.forEach((allValidRoles) => {
                        //if roles match roles to be removed then update the field in the array object to true
                        if (removeRole.roleName == allValidRoles.roleName) {
                          allValidRoles.added = true;
                          this.permissions.set(index, allValidRoles);
                        }
                      });
                    });
                  }
                  this.employeeService.rolesToBackend.next(this.permissions);
                });
              },
            });
          } else {
            //new employee permissions
            // this.employeeService.getAllOfficeRoles();
            this.employeeService.rolesFromBackend.subscribe((roles) => {
              if (roles != null) {
                this.arrayOfPermissions = roles;
              }
            });
          }
        },
      })
    );
  }
  ngOnDestroy(): void {
    this.subs.forEach((s) => s.unsubscribe());
  }

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
    this.permissions.clear();
  }
}
