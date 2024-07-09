import { Route } from '@angular/router';
import { EmployeeLoggedInComponent } from './employee-logged-in.component';
import { EmployeeManagementComponent } from '../employee-management/employee-management.component';
import { BranchManagementComponent } from './branch-management/branch-management.component';
import { SubEmployeesComponent } from '../employee-management/sub-employees/sub-employees.component';
import { EmployeeManagementShellComponent } from '../employee-management/employee-management-shell/employee-management-shell.component';

export const EMPLOYEE_LOGGED_IN: Route[] = [
  { path: '', component: EmployeeLoggedInComponent },
  {
    path: 'emp-management',
    component: EmployeeManagementShellComponent,
    children: [
      {
        path: 'all-employees',
        component: EmployeeManagementComponent,
      },
      {
        path: 'sub-employee',
        component: SubEmployeesComponent,
      },
    ],
  },
  { path: 'bank-management', component: BranchManagementComponent },
  // { path: 'sub-employee', component: SubEmployeesComponent },
];
