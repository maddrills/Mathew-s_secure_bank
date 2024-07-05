import { Component } from '@angular/core';
import { AssignedApplicationsComponent } from '../../employee-logged-in/assigned-applications/assigned-applications.component';
import { EmployeeManagementComponent } from '../employee-management.component';

@Component({
  selector: 'app-sub-employees',
  standalone: true,
  imports: [AssignedApplicationsComponent, EmployeeManagementComponent],
  templateUrl: './sub-employees.component.html',
  styleUrl: './sub-employees.component.css',
})
export class SubEmployeesComponent {}
