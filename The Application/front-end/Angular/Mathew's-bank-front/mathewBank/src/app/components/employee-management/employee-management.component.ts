import { Component } from '@angular/core';
import { EmployeeViewComponent } from './employee-view/employee-view.component';
import { AssignedApplicationsComponent } from '../employee-logged-in/assigned-applications/assigned-applications.component';
import { SubEmployeesComponent } from './sub-employees/sub-employees.component';

@Component({
  selector: 'app-employee-management',
  standalone: true,
  imports: [
    EmployeeViewComponent,
    AssignedApplicationsComponent,
    SubEmployeesComponent,
  ],
  templateUrl: './employee-management.component.html',
  styleUrl: './employee-management.component.css',
})
export class EmployeeManagementComponent {}
