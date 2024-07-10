import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { rolesModel } from '../../../model/roles-model';

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

  //TODO: replace with api data
  public arrayOfPermissions: rolesModel[] = [
    new rolesModel('ROLE_employee', false),
    new rolesModel('ROLE_clerk', false),
    new rolesModel('ROLE_manager', false),
    new rolesModel('ROLE_admin', false),
  ];

  addPermission(role: rolesModel) {
    console.log(role);
    console.log('option click');
    this.openUp = !this.openUp;
    this.permissions.set(this.permissionCount++, role);
    role.added = true;
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
