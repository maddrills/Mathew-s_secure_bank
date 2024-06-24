import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-welcome',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent, CommonModule],
  templateUrl: './user-welcome.component.html',
  styleUrl: './user-welcome.component.css',
})
export class UserWelcomeComponent {
  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}
  userName = 'Mathew Francis';

  savingsVisible: boolean = false;
  checkingVisible: boolean = false;
  buildUpVisible: boolean = false;

  visibilityInverter(arg0: boolean) {
    arg0 = !arg0;
  }

  transferFund(accNumber: string) {
    console.log(accNumber);
    this.router.navigate(['details'], {
      queryParams: { openDetailsUi: 1 },
      relativeTo: this.activatedRoute,
    });
  }

  transactions($accNumber: any) {
    console.log($accNumber);
    this.router.navigate(['details'], {
      queryParams: { openDetailsUi: 2 },
      relativeTo: this.activatedRoute,
    });
  }

  editDetails($accNumber: any) {
    console.log($accNumber);
    this.router.navigate(['details'], {
      queryParams: { openDetailsUi: 3 },
      relativeTo: this.activatedRoute,
    });
  }
}
