import { Route } from '@angular/router';
import { EmployeeLoggedInComponent } from './employee-logged-in.component';
import { EmployeeManagementComponent } from '../employee-management/employee-management.component';
import { BranchManagementComponent } from './branch-management/branch-management.component';

export const EMPLOYEE_LOGGED_IN: Route[] = [
  { path: '', component: EmployeeLoggedInComponent },
  { path: 'emp-management', component: EmployeeManagementComponent },
  { path: 'bank-management', component: BranchManagementComponent },
];
