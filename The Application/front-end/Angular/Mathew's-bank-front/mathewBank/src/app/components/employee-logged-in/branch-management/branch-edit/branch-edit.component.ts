import { Component, OnInit } from '@angular/core';
import { EmployeeManagementComponent } from '../../../employee-management/employee-management.component';
import { BranchModel } from '../../../../model/branch-model';
import { BankService } from '../../../../service/bank.service';
import { EmployeeDataModel } from '../../../../model/employee-model';
import { NavBarGoldService } from '../../../../service/navBarService';
import { RefreshDataFetcherService } from '../../../../service/dataRefresh';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BranchManagementComponent } from '../branch-management.component';
import { AssignedApplicationsComponent } from '../../assigned-applications/assigned-applications.component';
import { BankApplicationsComponent } from '../../bank-applications/bank-applications.component';
import { EmployeeService } from '../../../../service/employee-post-login.service';
import { rolesModel } from '../../../../model/roles-model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-branch-edit',
  standalone: true,
  imports: [
    EmployeeManagementComponent,
    FormsModule,
    CommonModule,
    BankApplicationsComponent,
    AssignedApplicationsComponent,
    BranchManagementComponent,
  ],
  templateUrl: './branch-edit.component.html',
  styleUrl: './branch-edit.component.css',
})
export class BranchEditComponent implements OnInit {
  selectedBrach: BranchModel | null = null;
  allEmployees: Map<number, EmployeeDataModel> = new Map();
  bankManager: EmployeeDataModel | null = null;

  ngOnInit(): void {
    this.allEmployees.clear();
  }

  constructor(
    private bankService: BankService,
    private navBarGoldService: NavBarGoldService,
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private employeeService: EmployeeService,
    private router: Router
  ) {
    //refresh statefullness
    this.navBarGoldService.resetAll();
    this.navBarGoldService.bankManagement.next(true);
    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();
    this.selectedBrach = JSON.parse(localStorage.getItem('selectedBank')!);
    console.log(this.selectedBrach);

    this.bankService.findEmployeeById(this.selectedBrach!.branchManagerId);
    this.bankService.findAllEmployeesUnderBranch(this.selectedBrach!.branchId);

    //clear map before populating it
    this.bankService.allEmployeesUnderBranch.subscribe({
      next: (employees) => {
        if (employees) {
          employees.forEach((employee, pos, full) =>
            this.allEmployees.set(employee.empId, employee)
          );
        }
      },
    });

    //for bank applications
    this.employeeService.authViewActive.next(false);
    this.bankService.bankBranchViewData.next(this.selectedBrach);

    this.bankService.managerSubject.subscribe({
      next: (manager) => {
        this.bankManager = manager!;
      },
    });

    console.log('All employees');
    console.log(this.allEmployees);
    console.log(this.userDataRefreshUpDate);
  }

  //remove employee
  remove(employeeID: number | undefined) {
    console.log('Removing employee ' + employeeID);

    //sanity check
    if (!employeeID) return;
    //check if manager
    if (employeeID == this.bankManager?.empId) {
      console.log('Removing manager');
      //remove manager
      this.bankService.removeManagerFrommBranch(employeeID).subscribe({
        next: (n) => {
          console.log('REMOVE_MANAGER_FROM_BRANCH');
          console.log(n.body);
          //remove from map and local storage
          this.allEmployees.delete(employeeID);
          this.bankService.managerSubject.next(null);
          this.bankManager == null;
          //this.findEmployeeByThereId(employeeID);
          //this.findBankBranchById(this.selectedBrach?.branchId!);
          const branch: BranchModel = JSON.parse(
            localStorage.getItem('selectedBank')!
          );
          if (branch == null) {
            throw 'Branch cant be null';
          }
          branch.branchManagerId = 0;
          localStorage.setItem('selectedBank', JSON.stringify(branch));
          this.selectedBrach = JSON.parse(
            localStorage.getItem('selectedBank')!
          );
        },
        error: (e) => console.log(e),
      });
    } else {
      //remove clerks
      console.log('Removing clerk');
      this.bankService
        .removeAClerkFromABranch(employeeID!, this.selectedBrach?.branchId!)
        .subscribe({
          next: (n) => {
            console.log('REMOVE_EMPLOYEE_FROM_BRANCH_Admin');
            console.log(n.body);
            //remove local store and manager storage
            this.allEmployees.delete(employeeID);
          },
          error: (e) => console.log(e),
        });
    }
  }

  addMangerToBranch(ngForm: NgForm) {
    const formValue = ngForm.form.value;
    console.log(formValue);
    this.bankService
      .putManagerInBranch(formValue.manager, this.selectedBrach?.branchId!)
      .subscribe({
        next: (n) => {
          console.log('REMOVE_MANAGER_FROM_BRANCH');
          console.log(n.body);
          this.findBankBranchById(this.selectedBrach?.branchId!);
          //update the dom accordingly
          this.bankService.findEmployeeById(formValue.manager);
          this.bankService.findAllEmployeesUnderBranch(
            this.selectedBrach!.branchId
          );
          ngForm.resetForm();
        },
        error: (e) => console.log(e),
      });
  }

  private findBankBranchById(bankId: number) {
    console.log('-----Getting branch-----');
    this.bankService.getBranchByBranchId(bankId).subscribe({
      next: (n) => {
        console.log('GET_EMPLOYEE_BY_UNDER_BRANCH');
        console.log(n.body);
        localStorage.removeItem('selectedBank');
        localStorage.setItem('selectedBank', JSON.stringify(n.body));
        this.selectedBrach = JSON.parse(localStorage.getItem('selectedBank')!);
      },
      error: (e) => console.log(e),
    });
  }

  // private findEmployeeByThereId(id: number) {
  //   this.bankService.findEmployeeById(id).subscribe({
  //     next: (n) => {
  //       console.log('GET_ALL_EMPLOYEE_BY_UNDER_BRANCH');
  //       console.log(n.body);
  //       //set the employee in LOCAL STORAGE
  //       localStorage.removeItem('selectedBank');
  //       localStorage.setItem('selectedBank', JSON.stringify(n.body));
  //     },
  //     error: (e) => console.log(e),
  //   });
  // }

  addClerkToBranch(ngForm: NgForm) {
    const clerkId: { clerk: number } = ngForm.form.value;

    if (!clerkId) return;
    let isManager: boolean = false;
    try {
      isManager = this.checkIfManager(clerkId.clerk);
    } catch (eer) {
      console.log(eer);
      return;
    }

    if (isManager) {
      console.log('Manager not allowed here');
      return;
    }
    console.log('is Clerk');
    this.bankService
      .addAClerkToABranch(clerkId.clerk, this.selectedBrach?.branchId!)
      .subscribe({
        next: (n) => {
          console.log('REMOVE_MANAGER_FROM_BRANCH');
          console.log(n.body);
          ngForm.resetForm();
          this.bankService.findAllEmployeesUnderBranch(
            this.selectedBrach!.branchId
          );
        },
        error: (e) => console.log(e),
      });
  }

  private checkIfManager(employeeID: number | undefined): boolean {
    //sanity check
    if (!employeeID) throw 'Not a valid input';
    //check if manager
    return employeeID == this.bankManager?.empId;
  }

  public jumpToEmployee(id: number) {
    console.log(id);
    localStorage.removeItem('selectedEmployee');

    //specific employee assigned applications logic
    this.employeeService.authViewActive.next(true);
    console.log('Sub EMP id is' + id);
    this.employeeService.employeeSelected.next(id);

    //specif data transfer
    const chosenEmployee = this.employeeService
      .getEmployeeByIdWithSub(id)
      .subscribe({
        next: (employee) => {
          const employeeData: EmployeeDataModel = employee.body!;
          this.employeeService.employeeById.next(employeeData);
          //roles of selected/
          const employeePermissionMap: rolesModel[] = [];
          if (employeeData.rolesName) {
            employeeData.rolesName.forEach((role) => {
              employeePermissionMap.push(role);
            });
          }
          this.employeeService.rolesToBeRemovedFromBackend.next(
            employeePermissionMap
          );
        },
        error: (err) =>
          console.log(err, 'while getting an employee by id from backend'),
      });

    //reroute to sub employee
    this.router.navigate([
      `/employee-welcome/emp-management/sub-employee`,
      { employeeId: id },
    ]);
  }
}
