import { Component } from '@angular/core';
import { EmployeeManagementComponent } from '../../../employee-management/employee-management.component';
import { BranchModel } from '../../../../model/branch-model';
import { BankService } from '../../../../service/bank.service';
import { EmployeeDataModel } from '../../../../model/employee-model';
import { NavBarGoldService } from '../../../../service/navBarService';
import { RefreshDataFetcherService } from '../../../../service/dataRefresh';

@Component({
  selector: 'app-branch-edit',
  standalone: true,
  imports: [EmployeeManagementComponent],
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
    this.bankManager = null;
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
}
