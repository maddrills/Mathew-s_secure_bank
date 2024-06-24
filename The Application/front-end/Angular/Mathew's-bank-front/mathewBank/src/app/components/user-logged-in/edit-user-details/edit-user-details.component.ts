import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Route, Router } from '@angular/router';

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

  constructor(private router: Router, private route: ActivatedRoute) {
    //gets the router param number to reveal the Ui
    // console.log(router.url);
    // console.log(this.route.snapshot.queryParams['openDetailsUi']);

    const paramNumber = Number(
      this.route.snapshot.queryParams['openDetailsUi']
    );

    this.openPartOdUi(paramNumber);
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
