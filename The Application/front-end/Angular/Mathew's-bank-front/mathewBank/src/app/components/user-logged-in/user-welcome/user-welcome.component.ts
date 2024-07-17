import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';
import { UserModel } from '../../../model/user-model';

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

  constructor(
    private router: Router,
    private userService: UserService,
    private activatedRoute: ActivatedRoute
  ) {
    //get user info from local storage
    this.activeUser = JSON.parse(localStorage.getItem('activeUser')!);
    this.fullName = this.activeUser?.fullName!;

    //get all account related info
  }

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
