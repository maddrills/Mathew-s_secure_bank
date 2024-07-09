import { Component } from '@angular/core';
import { AssignedApplicationsComponent } from '../../employee-logged-in/assigned-applications/assigned-applications.component';
import { EmployeeManagementComponent } from '../employee-management.component';
import { NavBarGoldService } from '../../../service/navBarService';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { Router } from '@angular/router';
import { RefreshDataFetcherService } from '../../../service/dataRefresh';
import { EmployeeDataModel } from '../../../model/employee-model';

@Component({
  selector: 'app-sub-employees',
  standalone: true,
  imports: [AssignedApplicationsComponent],
  templateUrl: './sub-employees.component.html',
  styleUrl: './sub-employees.component.css',
})
export class SubEmployeesComponent {
  //fetch data from this point
  employeeData: EmployeeDataModel | null = null;
  constructor(
    private navBarGoldService: NavBarGoldService,
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private employeeService: EmployeeService,
    private route: Router
  ) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.empManagement.next(true);
    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();

    const storage = localStorage.getItem('selectedEmployee');
    if (storage == null) {
      this.employeeService.employeeById.subscribe((emp) => {
        console.log(emp);
        if (emp != null) {
          localStorage.setItem('selectedEmployee', JSON.stringify(emp));
        }
        this.employeeData = JSON.parse(
          localStorage.getItem('selectedEmployee')!
        );
      });
    } else {
      this.employeeData = JSON.parse(localStorage.getItem('selectedEmployee')!);
    }

    console.log('Indi emp is');
    console.log(this.employeeData);
    window.scrollTo(0, 0);
  }

  goBack() {
    this.route.navigate(['/employee-welcome/emp-management/all-employees']);
  }
}
