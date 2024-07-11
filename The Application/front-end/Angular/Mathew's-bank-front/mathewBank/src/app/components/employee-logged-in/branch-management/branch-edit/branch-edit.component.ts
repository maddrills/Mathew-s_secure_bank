import { Component } from '@angular/core';
import { EmployeeManagementComponent } from '../../../employee-management/employee-management.component';
import { BranchModel } from '../../../../model/branch-model';
import { BankService } from '../../../../service/bank.service';
import { EmployeeDataModel } from '../../../../model/employee-model';
import { NavBarGoldService } from '../../../../service/navBarService';
import { RefreshDataFetcherService } from '../../../../service/dataRefresh';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-branch-edit',
  standalone: true,
  imports: [EmployeeManagementComponent, FormsModule],
  templateUrl: './branch-edit.component.html',
  styleUrl: './branch-edit.component.css',
})
export class BranchEditComponent {
  selectedBrach: BranchModel | null = null;
  allEmployees: EmployeeDataModel[] = [];
  bankManager: EmployeeDataModel | null = null;

  constructor(
    private bankService: BankService,
    private navBarGoldService: NavBarGoldService,
    private userDataRefreshUpDate: RefreshDataFetcherService
  ) {
    //refresh statefullness
    this.navBarGoldService.resetAll();
    this.navBarGoldService.bankManagement.next(true);
    this.userDataRefreshUpDate.checkIfEmployeeDataAvailable();
    this.selectedBrach = JSON.parse(localStorage.getItem('selectedBank')!);
    console.log(this.selectedBrach);

    this.bankService.findEmployeeById(this.selectedBrach!.branchManagerId);
    this.bankService.findAllEmployeesUnderBranch(this.selectedBrach!.branchId);

    this.bankService.allEmployeesUnderBranch.subscribe({
      next: (employees) => {
        this.allEmployees = employees!;
      },
    });

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
      this.bankService.removeEmployeeFrommBranch(employeeID);
    } else {
      console.log('Removing clerk');
      this.bankService
        .removeAClerkFromABranch(employeeID!, this.selectedBrach?.branchId!)
        .subscribe({
          next: (n) => {
            console.log('REMOVE_EMPLOYEE_FROM_BRANCH_Admin');
            console.log(n.body);
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
          ngForm.resetForm();
        },
        error: (e) => console.log(e),
      });
  }
}
