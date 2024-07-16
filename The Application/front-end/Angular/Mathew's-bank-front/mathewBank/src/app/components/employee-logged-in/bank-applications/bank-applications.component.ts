import { Component } from '@angular/core';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { applicationsModel } from '../../../model/applications-model';
import { EmployeeDataModel } from '../../../model/employee-model';

@Component({
  selector: 'app-bank-applications',
  standalone: true,
  imports: [],
  templateUrl: './bank-applications.component.html',
  styleUrl: './bank-applications.component.css',
})
export class BankApplicationsComponent {
  applications: applicationsModel[] = [];

  expand: number = -1;
  employeeUnderView: number = -1;
  employeeData: EmployeeDataModel | null = null;
  subEmployeeActive: boolean = false;
  getAllApplications: boolean = false;
  transferError: boolean = false;
  transferDone: boolean = false;

  constructor(private employeeService: EmployeeService) {
    // this.employeeService.authViewActive.next(true);//

    employeeService.authViewActive.subscribe({
      next: (active) => (this.subEmployeeActive = active),
    });

    this.employeeService.employeeSelected.subscribe({
      next: (employeeId) => (this.employeeUnderView = employeeId),
    });

    this.employeeData = JSON.parse(localStorage.getItem('selectedEmployee')!);
  }

  getData() {
    //get only sub branches
    if (this.subEmployeeActive && !this.getAllApplications) {
      console.log(this.employeeUnderView);
      //if user has no branch
      if (!this.employeeData?.branchId) return;
      this.employeeService
        .getAllApplicationsUnderAnyBranch(this.employeeData?.branchId!)
        .subscribe({
          next: (applicationsBack) => {
            console.log(applicationsBack.body);
            this.applications = applicationsBack.body!;
            this.getAllApplications = false;
          },
          error: (er) => {
            console.log(er);
          },
        });
      return;
    }

    console.log('Get data');
    this.employeeService.fetchAllUserApplications().subscribe({
      next: (applicationsBack) => {
        console.log(applicationsBack.body);
        this.applications = applicationsBack.body!;
      },
      error: (er) => {},
    });
  }

  expandAssignedArea(expander: number) {
    this.expand = expander;
    this.transferError = false;
    this.transferDone = false;
  }

  getAllTheApplications() {
    this.getAllApplications = true;
    this.getData();
  }

  getAllTheApplicationsUnderBranch() {
    this.getAllApplications = false;
    this.getData();
  }

  reAssignTo(applicationId: number | undefined, pos: number) {
    if (applicationId == undefined || applicationId < 0) {
      throw new Error('Method not implemented.');
    }

    this.employeeService
      .reAssignApplicationToAnother(this.employeeUnderView, applicationId)
      .subscribe({
        next: (n) => {
          console.log(n.body);
          if (n.body) {
            //then change dom
            this.applications[pos].assignedTo = this.employeeUnderView;
            this.transferDone = true;
          } else {
            this.transferError = true;
          }
        },
        error: (e) => console.log(e),
      });
  }
}
