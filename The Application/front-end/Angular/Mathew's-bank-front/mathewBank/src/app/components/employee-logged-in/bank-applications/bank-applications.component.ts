import { Component } from '@angular/core';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { applicationsModel } from '../../../model/applications-model';
import { EmployeeDataModel } from '../../../model/employee-model';
import { BankService } from '../../../service/bank.service';
import { BranchModel } from '../../../model/branch-model';

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

  //bank
  bankDataView: BranchModel | null = null;

  constructor(
    private employeeService: EmployeeService,
    private bankService: BankService
  ) {
    // this.employeeService.authViewActive.next(true);//

    employeeService.authViewActive.subscribe({
      next: (active) => (this.subEmployeeActive = active),
    });

    this.employeeService.employeeSelected.subscribe({
      next: (employeeId) => (this.employeeUnderView = employeeId),
    });

    // this.bankService.getAllApplicationsUnderAnyBranch();

    this.bankService.bankBranchViewData.subscribe({
      next: (bankBranch) => {
        this.bankDataView = bankBranch;
      },
    });

    this.employeeData = JSON.parse(localStorage.getItem('selectedEmployee')!);
  }

  getData() {
    //get only sub branches
    if (this.subEmployeeActive && !this.getAllApplications) {
      console.log(this.employeeUnderView);
      //if user has no branch
      if (!this.employeeData?.branchId) return;

      //under employee view
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

    console.log(this.bankDataView);
    //under branch view
    if (this.bankDataView != null) {
      this.employeeService
        .getAllApplicationsUnderAnyBranch(this.bankDataView.branchId)
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

  acceptApplicationMethod(applicationId: number, pos: number) {
    if (applicationId == undefined || applicationId < 0) {
      throw new Error('Method not implemented.');
    }

    const activeUser: EmployeeDataModel = JSON.parse(
      localStorage.getItem('employeeData')!
    );

    this.employeeService.acceptApplication(applicationId).subscribe({
      next: (n) => {
        console.log(n.body);
        if (n.body) {
          //then change dom
          this.applications[pos].status = true;
          this.applications[pos].approvedBy = activeUser.empId;
          alert('User Accepting');
        } else {
          alert('Error Accepting User');
        }
      },
      error: (e) => console.log(e),
    });
  }

  rejectApplicationMethod(applicationId: number, pos: number) {
    if (applicationId == undefined || applicationId < 0) {
      throw new Error('Method not implemented.');
    }

    const activeUser: EmployeeDataModel = JSON.parse(
      localStorage.getItem('employeeData')!
    );

    this.employeeService.rejectApplication(applicationId).subscribe({
      next: (n) => {
        console.log(n.body);
        if (n.body) {
          //then change dom
          this.applications[pos].rejected = true;
          this.applications[pos].approvedBy = activeUser.empId;
          alert('Rejection Successful');
        } else {
          alert('Rejection failed');
        }
      },
      error: (e) => console.log(e),
    });
  }
}
