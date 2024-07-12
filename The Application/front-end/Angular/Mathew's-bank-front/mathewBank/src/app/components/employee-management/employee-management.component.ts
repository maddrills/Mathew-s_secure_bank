import { Component } from '@angular/core';
import { AssignedApplicationsComponent } from '../employee-logged-in/assigned-applications/assigned-applications.component';
import { SubEmployeesComponent } from './sub-employees/sub-employees.component';
import { NavBarGoldService } from '../../service/navBarService';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { UnRegService } from '../../service/unRegService';
import { RefreshDataFetcherService } from '../../service/dataRefresh';
import { EmployeeService } from '../../service/employee-post-login.service';
import { EmployeeDataModel } from '../../model/employee-model';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-employee-management',
  standalone: true,
  imports: [
    FooterSectionComponent,
    NavBarComponent,
    AssignedApplicationsComponent,
    SubEmployeesComponent,
    RouterOutlet,
  ],
  templateUrl: './employee-management.component.html',
  styleUrl: './employee-management.component.css',
})
export class EmployeeManagementComponent {
  //all employees
  public allEmployeesCalledByAdmin: EmployeeDataModel[] | null = null;

  constructor(
    private navBarGoldService: NavBarGoldService,
    private userService: UnRegService,
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private employeeService: EmployeeService,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.empManagement.next(true);
    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();

    this.allUsersDataRefresh();

    // this.employeeService.allEmployeeData.subscribe({
    //   next: (theData) => {
    //     console.log('Displayable data');
    //     this.allEmployeesCalledByAdmin = theData;
    //     console.log(this.allEmployeesCalledByAdmin);
    //   },
    //   error: (e) =>
    //     console.log('something went wrong while fetching all employees'),
    // });
  }

  public personId(id: number, selectedIndex: number) {
    console.log(id);
    localStorage.removeItem('selectedEmployee');

    //specific employee assigned applications logic
    this.employeeService.authViewActive.next(true);
    console.log('Sub EMP id is' + id);
    this.employeeService.employeeSelected.next(id);

    if (this.allEmployeesCalledByAdmin != null) {
      this.employeeService.employeeById.next(
        this.allEmployeesCalledByAdmin[selectedIndex]
      );
    } else return;
    //reroute to sub employee
    this.route.navigate(
      [
        `/employee-welcome/emp-management/sub-employee`,
        { EMPIndex: selectedIndex, employeeId: id },
      ],
      {
        relativeTo: this.activatedRoute,
      }
    );
  }

  private allUsersDataRefresh() {
    this.employeeService.getAllEmployees().subscribe({
      next: (allUserData) =>
        (this.allEmployeesCalledByAdmin = allUserData.body),
      error: (errorOut) =>
        console.log(`all employees Data refresh error ${errorOut}`),
    });
  }
}
