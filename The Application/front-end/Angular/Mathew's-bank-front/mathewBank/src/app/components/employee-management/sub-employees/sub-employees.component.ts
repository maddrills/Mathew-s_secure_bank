import { Component } from '@angular/core';
import { AssignedApplicationsComponent } from '../../employee-logged-in/assigned-applications/assigned-applications.component';
import { EmployeeManagementComponent } from '../employee-management.component';
import { NavBarGoldService } from '../../../service/navBarService';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { Router, RouterOutlet } from '@angular/router';
import { RefreshDataFetcherService } from '../../../service/dataRefresh';
import { EmployeeDataModel } from '../../../model/employee-model';
import { BehaviorSubject } from 'rxjs';
import { BankApplicationsComponent } from '../../employee-logged-in/bank-applications/bank-applications.component';
import { PermissionSetComponent } from '../permission-set/permission-set.component';

@Component({
  selector: 'app-sub-employees',
  standalone: true,
  imports: [
    AssignedApplicationsComponent,
    RouterOutlet,
    BankApplicationsComponent,
    PermissionSetComponent,
  ],
  templateUrl: './sub-employees.component.html',
  styleUrl: './sub-employees.component.css',
})
export class SubEmployeesComponent {
  //fetch data from this point
  employeeData: EmployeeDataModel | null = null;

  //array of all sub employees
  //subEmployees = new BehaviorSubject<EmployeeDataModel[] | null>(null);
  subEmployees: EmployeeDataModel[] | null = null;

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
    this.listOutAllSubEmployees();
    window.scrollTo(0, 0);
  }

  goBack() {
    this.route.navigate(['/employee-welcome/emp-management/all-employees']);
  }

  listOutAllSubEmployees() {
    //TODO optimise
    //only check if employee data available
    if (this.employeeData) {
      this.employeeService
        .getEmployeesUnderEmployee(this.employeeData.empId)
        .subscribe({
          next: (employee) => {
            this.navBarGoldService.empManagement.next(true);
            //this.subEmployees.next(employee.body);
            this.subEmployees = employee.body;
            this.employeeService.rolesToBeRemovedFromBackend.next(null);
            this.employeeService.authViewActive.next(true);
            console.log('Sub EMP id is' + this.employeeData?.empId);
            this.employeeService.employeeSelected.next(
              this.employeeData?.empId!
            );
            //specif data transfer
            this.employeeService.employeeById.next(this.employeeData);

            const employeePermissionSet = this.employeeData?.rolesName;
            if (employeePermissionSet) {
              this.employeeService.rolesToBeRemovedFromBackend.next(
                employeePermissionSet
              );
            }

            //checking
            this.employeeService.employeeById.subscribe((emp) => {
              console.log(emp);
              if (emp != null) {
                localStorage.setItem('selectedEmployee', JSON.stringify(emp));
              }
              this.employeeData = JSON.parse(
                localStorage.getItem('selectedEmployee')!
              );
            });
          },
          error: (err) =>
            console.log(err, 'while getting an employee by id from backend'),
        });
    } else {
      alert('No employee set');
      console.log('No employee set');
    }

    console.log('click');
    //this.subEmployees.subscribe((data) => console.log(data));
    console.log(this.subEmployees);
  }

  public personId(id: number) {
    console.log(id);

    this.employeeService.getEmployeeById(id);
    //find employee by this id/
    this.employeeService.employeeById.subscribe((employee) => {
      console.log(employee);
      this.employeeData = employee;
    });

    //get sub employees
    this.listOutAllSubEmployees();
  }

  refreshPermissions() {}
}
