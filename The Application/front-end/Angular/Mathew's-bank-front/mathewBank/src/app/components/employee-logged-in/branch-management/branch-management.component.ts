import { Component } from '@angular/core';
import { NavBarGoldService } from '../../../service/navBarService';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { RefreshDataFetcherService } from '../../../service/dataRefresh';
import { BankService } from '../../../service/bank.service';
import { BehaviorSubject } from 'rxjs';
import { BranchModel } from '../../../model/branch-model';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-branch-management',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent, FormsModule],
  templateUrl: './branch-management.component.html',
  styleUrl: './branch-management.component.css',
})
export class BranchManagementComponent {
  //allBankNames = new BehaviorSubject<BankService[] | null>(null);
  allBankNames: BranchModel[] | null = null;

  constructor(
    private navBarGoldService: NavBarGoldService,
    private userDataRefreshUpDate: RefreshDataFetcherService,
    private bankService: BankService,
    private router: Router
  ) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.bankManagement.next(true);
    userDataRefreshUpDate.checkIfEmployeeDataAvailable();
    this.getAllBranchesFrommDb();
  }

  public getAllBranchesFrommDb() {
    this.bankService.getAllBankBranches().subscribe({
      next: (n) => {
        console.log(n.body);
        this.allBankNames = n.body;
      },
      error: (e) => {
        console.log(e);
      },
    });
  }

  public listBranchRefresh() {
    console.log('Branch refreshing');
    this.getAllBranchesFrommDb();
  }

  public createABranch(ngForm: NgForm) {
    const bank: BranchModel = ngForm.form.value;

    bank.country = bank.country.charAt(0).toUpperCase() + bank.country.slice(1);
    bank.state = bank.state.charAt(0).toUpperCase() + bank.state.slice(1);

    console.log(bank.open);
    if (bank.open == true) {
      bank.open = false;
    }
    if (bank.open === '') {
      bank.open = true;
    }
    console.log(bank);

    this.bankService.creaateAbranch(bank).subscribe({
      next: (n) => {
        ngForm.resetForm();
        console.log(n);
      },
      error: (e) => {
        console.log(e);
      },
    });
  }

  public openBranch(selectedBranchId: number, pos: number) {
    console.log(selectedBranchId, pos);

    const chosenBranch: BranchModel | null =
      this.allBankNames == null ? null : this.allBankNames[pos];

    if (chosenBranch != null) {
      localStorage.removeItem('selectedBank');
      this.bankService.managerSubject.next(null);
      localStorage.setItem('selectedBank', JSON.stringify(chosenBranch));
      this.router.navigate(['/employee-welcome/branch-edit-component']);

      console.log('findAllEmployeesUnderBranch');
      this.bankService.findEmployeeById(chosenBranch.branchManagerId);
      this.bankService.findAllEmployeesUnderBranch(chosenBranch.branchId);
    }
  }
}
