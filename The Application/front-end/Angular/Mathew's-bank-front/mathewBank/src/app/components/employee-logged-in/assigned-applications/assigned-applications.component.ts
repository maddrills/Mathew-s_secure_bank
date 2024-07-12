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
  public applicationsUnderMe: applicationsModel[] = [];

  constructor(private employeeService: EmployeeService) {}

  getData() {
    console.log('Get data');
    this.employeeService.getAllApplicationsUnderMe().subscribe({
      next: (roles) => {
        console.log(roles.body);
        this.applicationsUnderMe = roles.body!;
      },
      error: (er) => {},
    });
  }
}
