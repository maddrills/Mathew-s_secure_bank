import { Component } from '@angular/core';
import { EmployeeViewComponent } from './employee-view/employee-view.component';
import { AssignedApplicationsComponent } from '../employee-logged-in/assigned-applications/assigned-applications.component';

@Component({
  selector: 'app-employee-management',
  standalone: true,
  imports: [EmployeeViewComponent, AssignedApplicationsComponent],
  templateUrl: './employee-management.component.html',
  styleUrl: './employee-management.component.css',
})
export class EmployeeManagementComponent {}
