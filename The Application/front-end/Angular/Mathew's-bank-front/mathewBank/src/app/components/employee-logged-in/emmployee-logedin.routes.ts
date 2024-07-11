import { Route } from '@angular/router';
import { EmployeeLoggedInComponent } from './employee-logged-in.component';
import { EmployeeManagementComponent } from '../employee-management/employee-management.component';
import { BranchManagementComponent } from './branch-management/branch-management.component';
import { SubEmployeesComponent } from '../employee-management/sub-employees/sub-employees.component';
import { EmployeeManagementShellComponent } from '../employee-management/employee-management-shell/employee-management-shell.component';
import { LogInPageComponent } from '../log-in-page/log-in-page.component';
import { AddNewEmployeeComponent } from '../employee-management/add-new-employee/add-new-employee.component';
import { BranchEditComponent } from './branch-management/branch-edit/branch-edit.component';

export const EMPLOYEE_LOGGED_IN: Route[] = [
  { path: '', component: EmployeeLoggedInComponent },
  {
    path: 'emp-management',
    component: EmployeeManagementShellComponent,
    children: [
      {
        path: 'all-employees',
        component: EmployeeManagementComponent,
        children: [
          {
            path: 'add-employee',
            component: AddNewEmployeeComponent,
          },
        ],
      },
      {
        path: 'sub-employee',
        component: SubEmployeesComponent,
      },
    ],
  },
  { path: 'bank-management', component: BranchManagementComponent },
  { path: 'branch-edit-component', component: BranchEditComponent },
  // { path: 'sub-employee', component: SubEmployeesComponent },
];
