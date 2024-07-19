import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EmployeePermissionService } from '../../../service/employee-permission.service';

@Component({
  selector: 'app-employee-management-shell',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './employee-management-shell.component.html',
  styleUrl: './employee-management-shell.component.css',
})
export class EmployeeManagementShellComponent {
  constructor(private employeePermissionService: EmployeePermissionService) {
    // private employeePermissionService: EmployeePermissionService
    // this.employeePermissionService.checkUserAuth();
  }
}
