import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';
import { UserModel } from '../../../model/user-model';
import { UserAccountDeepModel } from '../../../model/user-account-deep-model';
import { AccountTimeSpace } from '../../../model/time-space-model';
import { UnRegService } from '../../../service/unRegService';

@Component({
  selector: 'app-user-welcome',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent, CommonModule],
  templateUrl: './user-welcome.component.html',
  styleUrl: './user-welcome.component.css',
})
export class UserWelcomeComponent {
  activeUser: UserModel | null = null;
  fullName = '';
  allAccounts: UserAccountDeepModel[] | null = null;

  //ui
  selectAnAccount: boolean = false;
  openCreateAccount: boolean = false;
  accountSettings: AccountTimeSpace[] | null = null;
  accountSet: AccountTimeSpace | null = null;

  constructor(
    private router: Router,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private UnRegService: UnRegService,
  ) {
    //get user info from local storage
    this.activeUser = JSON.parse(localStorage.getItem('activeUser')!);
    this.fullName = this.activeUser?.fullName!;

    //get all account related info
    this.userService.getAllUserBankAccounts().subscribe({
      next: (allBankAccounts) => {
        console.log(allBankAccounts.body);
        this.allAccounts = allBankAccounts.body;
      },
    });

    //remove last local storage
    //localStorage.removeItem('selectedAccount');
    //private UnRegService: UnRegService,
    UnRegService.checkIfUserIsLoggedIn();
  }

  savingsVisible: boolean = false;
  checkingVisible: boolean = false;
  buildUpVisible: boolean = false;

  visibilityInverter(arg0: boolean) {
    arg0 = !arg0;
  }

  transferFund(pos: number) {
    console.log(pos);
    this.privateSelectedAccount(pos);
    this.router.navigate(['details'], {
      queryParams: { openDetailsUi: 1 },
      relativeTo: this.activatedRoute,
    });
  }

  transactions(pos: number) {
    console.log(pos);
    this.privateSelectedAccount(pos);
    this.router.navigate(['details'], {
      queryParams: { openDetailsUi: 2 },
      relativeTo: this.activatedRoute,
    });
  }

  editDetails(pos: number) {
    console.log(pos);
    this.privateSelectedAccount(pos);
    this.router.navigate(['details'], {
      queryParams: { openDetailsUi: 3 },
      relativeTo: this.activatedRoute,
    });
  }

  privateSelectedAccount(accountArrayPosition: number) {
    this.userService.selectedAccountByUser.next(
      this.allAccounts![accountArrayPosition]
    );
    localStorage.setItem(
      'selectedAccount',
      JSON.stringify(this.allAccounts![accountArrayPosition])
    );
  }

  public displayAllAccountSettings() {
    console.log('Inverter');
    let invert = false;
    //get all user accounts
    if (this.selectAnAccount) {
      this.selectAnAccount = invert;
      console.log('return');
      return;
    }
    this.userService.getAllAccountSetting().subscribe({
      next: (allAccountSettings) => {
        console.log(allAccountSettings.body);
        this.accountSettings = allAccountSettings.body;
        this.selectAnAccount = !invert;
      },
    });
  }

  selectThisAccount(pos: number) {
    console.log(pos);
    this.selectAnAccount = !this.selectAnAccount;
    this.accountSet = this.accountSettings![pos];
  }

  openUpAccountCreateSection() {
    this.openCreateAccount = !this.openCreateAccount;
    this.selectAnAccount = false;
    this.accountSet = null;
  }

  createABankAccount() {
    //if null
    if (!this.accountSet) {
      throw new Error('Method not implemented.');
    }

    this.userService
      .createABankAccount(
        this.accountSet.accountType,
        this.accountSet.minStartingAmount
      )
      .subscribe({
        next: (n) => location.reload(),
        error: (er) => console.log(er),
      });
  }
}
