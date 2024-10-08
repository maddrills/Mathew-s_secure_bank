import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';
import { UserAccountDeepModel } from '../../../model/user-account-deep-model';
import { UserModel } from '../../../model/user-model';
import { AccountTimeSpace } from '../../../model/time-space-model';
import { FormsModule, NgForm } from '@angular/forms';
import { TransactionsModel } from '../../../model/transactions-model';
import { UnRegService } from '../../../service/unRegService';

@Component({
  selector: 'app-edit-user-details',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent, CommonModule, FormsModule],
  templateUrl: './edit-user-details.component.html',
  styleUrl: './edit-user-details.component.css',
})
export class EditUserDetailsComponent {
  private startPoint: boolean = false;

  moneyTransferActive: boolean = this.startPoint;
  transactions: boolean = this.startPoint;
  editSavingsAccount: boolean = this.startPoint;
  inactiveAccount: boolean = this.startPoint;
  closeAccount: boolean = this.startPoint;

  activeUser: UserModel | null = null;
  fullName = '';
  selectedAllAccount: UserAccountDeepModel | null = null;
  accountSettings: AccountTimeSpace | null = null;

  //Transactions
  allUserTransactions: TransactionsModel[] | null = null;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private UnRegService: UnRegService
  ) {
    //gets the router param number to reveal the Ui
    // console.log(router.url);
    // console.log(this.route.snapshot.queryParams['openDetailsUi']);

    const paramNumber = Number(
      this.route.snapshot.queryParams['openDetailsUi']
    );

    this.openPartOdUi(paramNumber);

    //get user info from local storage
    this.activeUser = JSON.parse(localStorage.getItem('activeUser')!);
    this.fullName = this.activeUser?.fullName!;

    //account refresh
    this.selectedAllAccount = JSON.parse(
      localStorage.getItem('selectedAccount')!
    );
    // this.userService.selectedAccountByUser.subscribe({
    //   next: (account) => {
    //     this.selectedAllAccount = account;
    //   },
    // });
    //get interest rate using account type
    this.userService
      .getAccountSettingsByAccountName(
        this.selectedAllAccount?.accountTypeName!
      )
      .subscribe({
        next: (accountSettings) => {
          this.accountSettings = accountSettings.body;
        },
      });

    //check if transactions is open
    // if (this.transactions) {
    //   this.getAllUserTransactions();
    // }

    //private UnRegService: UnRegService,
    //import { UnRegService } from '../../../service/unRegService';
    UnRegService.checkIfUserIsLoggedIn();
    if (this.selectedAllAccount) {
      this.updateUserAccountDetails(this.selectedAllAccount.id);
    }
  }

  // check the query param and opens up a ui element accordingly
  private openPartOdUi(num: Number) {
    console.log(Number(num));
    switch (num) {
      case 1:
        this.moneyTransferActive = true;
        break;
      case 2:
        this.transactions = true;
        this.getAllUserTransactions();
        break;
      case 3:
        this.editSavingsAccount = true;
        break;
      default:
        console.log('error');
        break;
    }
  }

  editSavingsAccountLogic() {
    this.editSavingsAccount = !this.editSavingsAccount;
    if (!this.editSavingsAccount) {
      this.inactiveAccount = false;
      this.closeAccount = false;
    }
  }

  backToUserHome() {
    this.router.navigate(['user-welcome']);
  }

  transferCash(fullForm: NgForm) {
    const toData: { accountNumber: number; amount: number } =
      fullForm.form.value;

    console.log(toData);

    if (
      !this.selectedAllAccount?.id ||
      !toData.accountNumber ||
      !toData.amount
    ) {
      return;
    }
    this.userService
      .moneyTransfer(
        this.selectedAllAccount?.id,
        toData.accountNumber,
        toData.amount
      )
      .subscribe({
        next: (n) => {
          console.log(n);
          //reset the balance value
          let updateValue = this.selectedAllAccount?.amount;
          updateValue = updateValue! - toData.amount;

          const updateAccount: UserAccountDeepModel = JSON.parse(
            localStorage.getItem('selectedAccount')!
          );

          //update the UI
          updateAccount.amount = updateValue;
          this.selectedAllAccount = updateAccount;
          //then the storage
          const values = JSON.stringify(updateAccount);
          localStorage.setItem('selectedAccount', values);
          fullForm.resetForm();
        },
        error: (e) => console.log(e),
      });
  }

  private gitData = false;
  getAllUserTransactions() {
    if (this.transactions && this.gitData) {
      this.transactions = !this.transactions;
      return;
    }
    this.userService.getAllTransactions().subscribe({
      next: (n) => {
        console.log(n.body);
        this.allUserTransactions = n.body;
        this.gitData = true;
      },
      error: (er) => {},
    });
    this.transactions = true;
  }

  //get updated user details
  updateUserAccountDetails(accountNumber: number) {
    this.userService.getUserData().subscribe({
      next: (n) => {
        console.log('Activated account');
        console.log(n.body);

        //then the storage
        const values = JSON.stringify(n.body);
        localStorage.setItem('activeUser', values);
      },
      error: (e) => console.log(e),
    });

    console.log(accountNumber);
    this.userService.getAccountByAccountNumber(accountNumber).subscribe({
      next: (n) => {
        console.log('Selected account');
        console.log(n.body);
        console.log(`end`);

        //then the storage
        const values = JSON.stringify(n.body);
        localStorage.setItem('selectedAccount', values);
      },
      error: (e) => console.log(e),
    });
  }
}
