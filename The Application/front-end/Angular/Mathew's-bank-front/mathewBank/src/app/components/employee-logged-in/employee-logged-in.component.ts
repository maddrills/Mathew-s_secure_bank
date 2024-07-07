import { Component } from '@angular/core';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { BankApplicationsComponent } from './bank-applications/bank-applications.component';
import { AssignedApplicationsComponent } from './assigned-applications/assigned-applications.component';
import { EmployeeManagementComponent } from '../employee-management/employee-management.component';
import { BranchManagementComponent } from './branch-management/branch-management.component';
import { BranchEditComponent } from './branch-management/branch-edit/branch-edit.component';
import { UnRegService } from '../../service/unRegService';
import { EmployeeDataModel } from '../../model/employee-model';
import { RefreshDataFetcherService } from '../../service/dataRefresh';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-logged-in',
  standalone: true,
  imports: [
    NavBarComponent,
    FooterSectionComponent,
    BankApplicationsComponent,
    AssignedApplicationsComponent,
    EmployeeManagementComponent,
    BranchManagementComponent,
    BranchEditComponent,
    CommonModule,
  ],
  templateUrl: './employee-logged-in.component.html',
  styleUrl: './employee-logged-in.component.css',
})
export class EmployeeLoggedInComponent {
  public employeeDetails: EmployeeDataModel | null = null;
  public permissionLen: number | undefined = 0;
  constructor(
    private userService: UnRegService,
    private userDataRefreshUpDate: RefreshDataFetcherService
  ) {
    console.log('Employee logged in');
    this.runUserService();

    userDataRefreshUpDate.checkIfEmployeeDataAvailable();
  }

  runUserService() {
    this.userService.employeeData.subscribe((data) => {
      this.employeeDetails = data;
      this.permissionLen = data?.rolesName?.length;
    });

    const empData: EmployeeDataModel | null = JSON.parse(
      localStorage.getItem('employeeData')!
    );

    console.log(empData);
  }
}
