import { Route } from '@angular/router';
import { EmployeeLoggedInComponent } from './employee-logged-in.component';
import { EmployeeManagementComponent } from '../employee-management/employee-management.component';
import { BranchManagementComponent } from './branch-management/branch-management.component';
import { SubEmployeesComponent } from '../employee-management/sub-employees/sub-employees.component';
import { EmployeeManagementShellComponent } from '../employee-management/employee-management-shell/employee-management-shell.component';
import { LogInPageComponent } from '../log-in-page/log-in-page.component';
import { AddNewEmployeeComponent } from '../employee-management/add-new-employee/add-new-employee.component';
import { BranchEditComponent } from './branch-management/branch-edit/branch-edit.component';
import { AuthGuardAdminService } from '../../service/guards/authAdmin.guard';

export const EMPLOYEE_LOGGED_IN: Route[] = [
  {
    path: '',
    component: EmployeeLoggedInComponent,
    //canActivate: [AuthGuardAdminService.checkAdmin],
  },
  {
    path: 'emp-management',
    component: EmployeeManagementShellComponent,
    canActivate: [AuthGuardAdminService.checkManager],
    children: [
      {
        path: 'all-employees',
        component: EmployeeManagementComponent,
        canActivate: [AuthGuardAdminService.checkManager],
        children: [
          {
            path: 'add-employee',
            component: AddNewEmployeeComponent,
            canActivate: [AuthGuardAdminService.checkManager],
          },
        ],
      },
      {
        path: 'sub-employee',
        component: SubEmployeesComponent,
        canActivate: [AuthGuardAdminService.checkManager],
      },
    ],
  },
  {
    path: 'bank-management',
    component: BranchManagementComponent,
    canActivate: [AuthGuardAdminService.checkAdmin],
  },
  {
    path: 'branch-edit-component',
    component: BranchEditComponent,
    canActivate: [AuthGuardAdminService.checkManager],
  },
  // { path: 'sub-employee', component: SubEmployeesComponent },
];
