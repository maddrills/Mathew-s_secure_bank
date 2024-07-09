import { Component } from '@angular/core';
import { AssignedApplicationsComponent } from '../../employee-logged-in/assigned-applications/assigned-applications.component';
import { EmployeeManagementComponent } from '../employee-management.component';
import { NavBarGoldService } from '../../../service/navBarService';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { Router } from '@angular/router';
import { RefreshDataFetcherService } from '../../../service/dataRefresh';

@Component({
  selector: 'app-sub-employees',
  standalone: true,
  imports: [],
  templateUrl: './sub-employees.component.html',
  styleUrl: './sub-employees.component.css',
})
export class SubEmployeesComponent {
  constructor(
    private navBarGoldService: NavBarGoldService,
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private employeeService: EmployeeService,
    private route: Router
  ) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.empManagement.next(true);
    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();
  }
}
