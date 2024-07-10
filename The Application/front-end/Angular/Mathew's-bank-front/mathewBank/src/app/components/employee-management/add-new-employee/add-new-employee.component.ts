import { Component } from '@angular/core';
import { PermissionSetComponent } from '../permission-set/permission-set.component';
import { RefreshDataFetcherService } from '../../../service/dataRefresh';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-new-employee',
  standalone: true,
  imports: [PermissionSetComponent, FormsModule],
  templateUrl: './add-new-employee.component.html',
  styleUrl: './add-new-employee.component.css',
})
export class AddNewEmployeeComponent {
  constructor(
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private employeeService: EmployeeService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();
  }

  public createAnEmployee(fullForm: NgForm) {
    const formData = fullForm.form.value;
    console.log('Form is');
    console.log(formData);

    const phone_number = formData.phoneNumber;
    const full_name = formData.fullName;
    const email = formData.email;
    const dateOfBirth = formData.dob;
    const salary = formData.salary;
    const password = formData.password;

    this.employeeService
      .createAnEmployee(
        phone_number,
        full_name,
        email,
        dateOfBirth,
        salary,
        password
      )
      .subscribe({
        next: (roles) => {
          console.log(roles.body);
        },
        error: (er) => {},
      });
  }
}
