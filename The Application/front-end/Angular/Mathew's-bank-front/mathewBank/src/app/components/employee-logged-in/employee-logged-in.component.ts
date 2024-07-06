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
  ],
  templateUrl: './employee-logged-in.component.html',
  styleUrl: './employee-logged-in.component.css',
})
export class EmployeeLoggedInComponent {
  constructor(private userService: UnRegService) {
    console.log('Employee logged in');
    this.printTheUserService();

    // const empData: EmployeeDataModel | null = JSON.parse(
    //   localStorage.getItem('employeeData')!
    // );

    // console.log(empData);
    // this.userService.employeeData.next(
    //   new EmployeeDataModel(
    //     empData?.empId,
    //     empData?.reportsTo,
    //     empData?.branchId,
    //     empData?.empDetailsId,
    //     empData?.phoneNumber,
    //     empData?.empName,
    //     empData?.email,
    //     empData?.dob,
    //     empData?.salary,
    //     empData?.salaryAccount,
    //     empData?.rolesDTO
    //   )
    // );
  }

  printTheUserService() {
    this.userService.employeeData.subscribe((data) => console.log(data));

    const empData: EmployeeDataModel | null = JSON.parse(
      localStorage.getItem('employeeData')!
    );

    console.log(empData);
  }
}
