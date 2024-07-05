import { Component } from '@angular/core';
import { EmployeeManagementComponent } from '../employee-management.component';
import { AssignedApplicationsComponent } from '../../employee-logged-in/assigned-applications/assigned-applications.component';

@Component({
  selector: 'app-employee-view',
  standalone: true,
  imports: [AssignedApplicationsComponent, EmployeeManagementComponent],
  templateUrl: './employee-view.component.html',
  styleUrl: './employee-view.component.css',
})
export class EmployeeViewComponent {}
