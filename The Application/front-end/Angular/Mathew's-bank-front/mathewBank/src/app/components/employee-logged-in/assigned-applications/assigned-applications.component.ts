import { Component } from '@angular/core';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { applicationsModel } from '../../../model/applications-model';

@Component({
  selector: 'app-assigned-applications',
  standalone: true,
  imports: [],
  templateUrl: './assigned-applications.component.html',
  styleUrl: './assigned-applications.component.css',
})
export class AssignedApplicationsComponent {
  authViewActive: boolean = false;
  employeeUnderView: number = 0;
  expand: number = -1;
  public applicationsUnderMe: applicationsModel[] = [];

  assignedApplication: boolean = false;

  constructor(private employeeService: EmployeeService) {
    this.employeeService.authViewActive.subscribe({
      next: (n) => {
        console.log('Auth under watch active');
        console.log(n);
        this.authViewActive = n;
      },
    });
    this.employeeService.employeeSelected.subscribe({
      next: (employeeId) => (this.employeeUnderView = employeeId),
    });
  }

  getData() {
    this.assignedApplication = !this.assignedApplication;

    console.log('Get data');
    if (this.authViewActive) {
      console.log('Auth view');
      this.employeeService
        .getAllApplicationsUnderAnyEmployee(this.employeeUnderView)
        .subscribe({
          next: (roles) => {
            console.log(roles.body);
            this.applicationsUnderMe = roles.body!;
          },
          error: (er) => {},
        });
    } else {
      this.employeeService.getAllApplicationsUnderMe().subscribe({
        next: (roles) => {
          console.log(roles.body);
          this.applicationsUnderMe = roles.body!;
        },
        error: (er) => {},
      });
    }
  }

  expandAssignedArea(expander: number) {
    this.expand = expander;
  }

  rejectApplication(applicationId: number) {
    if (!applicationId) {
      throw new Error('Method not implemented.');
    }
    this.employeeService.rejectApplication(applicationId).subscribe({
      next: (n) => {
        console.log(n.body);
        if (n.body) {
          //then change dom
          alert('Rejection Successful');
        } else {
          alert('Rejection failed');
        }
      },
      error: (e) => console.log(e),
    });
  }
  acceptApplication(applicationId: number) {
    if (!applicationId) {
      throw new Error('Method not implemented.');
    }
    this.employeeService.acceptApplication(applicationId).subscribe({
      next: (n) => {
        console.log(n.body);
        if (n.body) {
          //then change dom
          alert('User Accepting');
        } else {
          alert('Error Accepting User');
        }
      },
      error: (e) => console.log(e),
    });
  }
}
