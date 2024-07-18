import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';
import { UserAccountDeepModel } from '../../../model/user-account-deep-model';
import { UserModel } from '../../../model/user-model';
import { AccountTimeSpace } from '../../../model/time-space-model';

@Component({
  selector: 'app-edit-user-details',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent, CommonModule],
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

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private activatedRoute: ActivatedRoute
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
}
