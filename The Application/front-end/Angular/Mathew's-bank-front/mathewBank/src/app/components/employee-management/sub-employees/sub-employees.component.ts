import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { AssignedApplicationsComponent } from '../../employee-logged-in/assigned-applications/assigned-applications.component';
import { EmployeeManagementComponent } from '../employee-management.component';
import { NavBarGoldService } from '../../../service/navBarService';
import { EmployeeService } from '../../../service/employee-post-login.service';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { RefreshDataFetcherService } from '../../../service/dataRefresh';
import { EmployeeDataModel } from '../../../model/employee-model';
import { BehaviorSubject } from 'rxjs';
import { BankApplicationsComponent } from '../../employee-logged-in/bank-applications/bank-applications.component';
import { PermissionSetComponent } from '../permission-set/permission-set.component';
import { rolesModel } from '../../../model/roles-model';
import { Location } from '@angular/common';
import { BankService } from '../../../service/bank.service';

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
export class SubEmployeesComponent implements OnDestroy {
  //fetch data from this point
  employeeData: EmployeeDataModel | null = null;

  //array of all sub employees
  //subEmployees = new BehaviorSubject<EmployeeDataModel[] | null>(null);
  subEmployees: EmployeeDataModel[] | null = null;

  constructor(
    private navBarGoldService: NavBarGoldService,
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private employeeService: EmployeeService,
    private bankService: BankService,
    private route: Router,
    private _location: Location,
    private urlRoute: ActivatedRoute
  ) {
    console.log('selectedEmployee');
    //a full reset
    this.navBarGoldService.resetAll();
    this.navBarGoldService.empManagement.next(true);
    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();

    const storage = localStorage.getItem('selectedEmployee');
    console.log('Local storage is ' + storage);
    //if local storage has no employee
    if (storage == null) {
      console.log('Null entry point');

      //get if from url
      // const empId: number = +this.urlRoute.snapshot.paramMap.get('employeeId')!;
      // this.employeeService.getEmployeeById(empId);
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
  ngOnDestroy(): void {
    location.reload();
  }

  goBack() {
    // this.route.navigate([
    //   '/employee-welcome/emp-management/all-employees/add-employee',
    // ]);

    this._location.back();
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
            this.employeeService.rolesFromBackend.next(null);
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
      // alert('No employee set');
      console.log('No employee set yet');
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

    location.reload();
  }

  changeEmployeePermissionsSet() {
    this.employeeService
      .changeEmployeePermissions(this.employeeData?.empId!)
      .subscribe({
        next: (n) => {
          console.log('Return Result');
          const employeePermissionSet: rolesModel[] | null = n.body;
          console.log(employeePermissionSet);
          employeePermissionSet?.forEach((changeInPermissions) => {
            changeInPermissions.added = true;
          });
          console.log(employeePermissionSet);
          this.employeeService.rolesToBeRemovedFromBackend.next(
            employeePermissionSet
          );
          const activeData: EmployeeDataModel = JSON.parse(
            localStorage.getItem('selectedEmployee')!
          );
          this.employeeService.getEmployeeById(activeData.empId);
          this.listOutAllSubEmployees();
          location.reload();
        },
        error: (er) => console.log(er),
      });
  }

  public openBranch(selectedBranchId: number) {
    console.log(selectedBranchId);

    //getBranchByBranchId(bankId: number)
    if (selectedBranchId) {
      localStorage.removeItem('selectedBank');
      this.bankService.managerSubject.next(null);
      this.bankService.getBranchByBranchId(selectedBranchId).subscribe({
        next: (branchInfo) => {
          localStorage.setItem('selectedBank', JSON.stringify(branchInfo.body));
          this.route.navigate(['/employee-welcome/branch-edit-component']);
        },
        error: (er) => console.log(er),
      });

      // console.log('findAllEmployeesUnderBranch');
      // this.bankService.findEmployeeById(chosenBranch.branchManagerId);
      // this.bankService.findAllEmployeesUnderBranch(chosenBranch.branchId);
    } else {
      alert('Not a valid Branch id');
    }
  }
  refreshPermissions() {}
}
