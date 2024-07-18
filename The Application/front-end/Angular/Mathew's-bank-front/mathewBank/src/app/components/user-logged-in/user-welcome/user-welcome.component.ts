import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';
import { UserModel } from '../../../model/user-model';
import { UserAccountDeepModel } from '../../../model/user-account-deep-model';

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

  constructor(
    private router: Router,
    private userService: UserService,
    private activatedRoute: ActivatedRoute
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
    localStorage.removeItem('selectedAccount');
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
    this.router.navigate(['details'], {
      queryParams: { openDetailsUi: 2 },
      relativeTo: this.activatedRoute,
    });
  }

  editDetails(pos: number) {
    console.log(pos);
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
}
