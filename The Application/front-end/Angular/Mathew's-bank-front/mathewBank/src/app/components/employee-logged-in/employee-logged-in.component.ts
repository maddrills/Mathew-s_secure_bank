import { Component, OnDestroy } from '@angular/core';
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
import { NavBarGoldService } from '../../service/navBarService';
import { EmployeeService } from '../../service/employee-post-login.service';
import { BankService } from '../../service/bank.service';
import { EmployeePermissionService } from '../../service/employee-permission.service';

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
  constructor(
    private userService: UnRegService,
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private navBarGoldService: NavBarGoldService,
    private employeeService: EmployeeService,
    private bankService: BankService,
    private employeePermissionService: EmployeePermissionService
  ) {
    console.log('Employee logged in');
    this.runUserService();

    //nav bar reset/
    this.navBarGoldService.resetAll();
    this.navBarGoldService.inEmployeeHomeComponent.next(true);
    this.employeeService.authViewActive.next(false);

    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();
    this.bankService.bankBranchViewData.next(null);

    // private employeePermissionService: EmployeePermissionService
    this.employeePermissionService.checkUserAuth();
  }
  runUserService() {
    this.userService.employeeData.subscribe((data) => {
      this.employeeDetails = data;
    });
  }
}
