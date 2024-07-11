import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { rolesModel } from '../../../model/roles-model';
import { EmployeeService } from '../../../service/employee-post-login.service';

@Component({
  selector: 'app-permission-set',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './permission-set.component.html',
  styleUrl: './permission-set.component.css',
})
export class PermissionSetComponent {
  public openUp: boolean = false;
  public permissionCount: number = 0;
  //map that hold the position and array ref
  public permissions: Map<number, rolesModel> = new Map();
  public arrayOfPermissions: rolesModel[] = [];

  constructor(private employeeService: EmployeeService) {
    this.employeeService.getAllOfficeRoles();

    this.employeeService.rolesFromBackend.subscribe((roles) => {
      console.log(roles);
      if (roles != null) {
        console.log('Subed roles');
        console.log(roles);
        this.arrayOfPermissions = roles;
      }
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
    console.log(role);
    console.log('option click');
    this.openUp = !this.openUp;
    this.permissions.set(this.permissionCount++, role);
    role.added = true;

    //export all the selected roles
    this.permissions.forEach((value, key, fullArray) => {
      console.log('Keys');
      console.log(key);
      console.log(value);
      console.log(fullArray);
    });
    this.employeeService.rolesToBackend.next(this.permissions);
  }

  click() {
    console.log('option click click');
  }

  remove(valueInMap: number) {
    //set the array reference in the map to become false if permission removed from map
    const permissions = this.permissions.get(valueInMap);

    if (permissions != null) {
      permissions.added = false;
      this.permissions.delete(valueInMap);
    }
  }
}
