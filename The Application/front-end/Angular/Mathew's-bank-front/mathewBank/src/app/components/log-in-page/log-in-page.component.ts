import { Component } from '@angular/core';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { UnRegService } from '../../service/unRegService';
import { Router } from '@angular/router';
import { NavBarGoldService } from '../../service/navBarService';
import { EmployeeDataModel } from '../../model/employee-model';

@Component({
  selector: 'app-log-in-page',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent, CommonModule, FormsModule],
  templateUrl: './log-in-page.component.html',
  styleUrl: './log-in-page.component.css',
})
export class LogInPageComponent {
  constructor(
    private unRegService: UnRegService,
    private router: Router,
    private navBarGoldService: NavBarGoldService
  ) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.logIn.next(true);
  }

  onSubmit(formSettings: NgForm) {
    const formDataFields = formSettings.form.value;
    // empty credentials input
    if (
      formDataFields.UserNameOrEmp == '' ||
      formDataFields.UserPassword == ''
    ) {
      console.log('Null');
      return;
    }

    //send details to backend
    this.unRegService.commonUserLogin(
      formDataFields.UserNameOrEmp,
      formDataFields.UserPassword
    );

    //first time submit ***
    const empData: EmployeeDataModel | null = JSON.parse(
      localStorage.getItem('employeeData')!
    );

    if (empData != null) {
      this.unRegService.logInDetected.next(true);
      this.unRegService.employeeIsLoggedIn.next(true);
    }

    console.log('------------------------------', empData);
    this.unRegService.employeeData.next(
      new EmployeeDataModel(
        empData?.empId,
        empData?.reportsTo,
        empData?.branchId,
        empData?.empDetailsId,
        empData?.phone_number,
        empData?.full_name,
        empData?.email,
        empData?.dateOfBirth,
        empData?.salary,
        empData?.salaryAccount,
        empData?.branchName,
        empData?.rolesName
      )
    );
    // ***

    // this.unRegService.bankUserLoggedIn.subscribe((isUser) => {
    //   console.log(isUser);

    //   if (isUser) {
    //     this.router.navigate(['user-welcome']);
    //   }
    // });

    // this.unRegService.employeeIsLoggedIn.subscribe((isEmployee) => {
    //   console.log('Employee is ' + isEmployee);

    //   if (isEmployee) {
    //     this.router.navigate(['employee-welcome']);
    //   }
    // });
  }
}
