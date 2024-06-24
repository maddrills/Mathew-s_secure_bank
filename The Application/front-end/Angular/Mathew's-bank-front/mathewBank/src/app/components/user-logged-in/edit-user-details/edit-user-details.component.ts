import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-user-details',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent, CommonModule],
  templateUrl: './edit-user-details.component.html',
  styleUrl: './edit-user-details.component.css',
})
export class EditUserDetailsComponent {
  constructor(private router: Router) {}

  private startPoint: boolean = false;

  moneyTransferActive: boolean = this.startPoint;
  transactions: boolean = this.startPoint;
  editSavingsAccount: boolean = this.startPoint;
  inactiveAccount: boolean = this.startPoint;
  closeAccount: boolean = this.startPoint;

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
