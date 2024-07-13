import { Component } from '@angular/core';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { applicationsModel } from '../../../model/applications-model';

@Component({
  selector: 'app-bank-applications',
  standalone: true,
  imports: [],
  templateUrl: './bank-applications.component.html',
  styleUrl: './bank-applications.component.css',
})
export class BankApplicationsComponent {
  applications: applicationsModel[] = [];

  constructor(private employeeService: EmployeeService) {
    // this.employeeService.authViewActive.next(true);
  }

  getData() {
    console.log('Get data');
    this.employeeService.fetchAllUserApplications().subscribe({
      next: (roles) => {
        console.log(roles.body);
        this.applications = roles.body!;
      },
      error: (er) => {},
    });
  }
}
