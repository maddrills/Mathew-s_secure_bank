import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UnRegService } from '../../../service/unRegService';
import { CommonModule } from '@angular/common';
import { NavBarGoldService } from '../../../service/navBarService';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
})
export class NavBarComponent {
  public loginActive: boolean = false;

  public mathewBackHome: boolean = false;

  // user nav login control
  public userIsLoggedIn: boolean = false;
  public loginSelected: boolean = false;
  public loginUserHome: boolean = false;

  // employee nav login control
  public employeeHomeSelected: boolean = false;

  //employee logged in
  public employeeLoggedIn: boolean = false;
  //employee access level
  public isAdmin: boolean = false;

  public url = this.router.url;

  constructor(
    private router: Router,
    private unRegService: UnRegService,
    private activatedRoute: ActivatedRoute,
    private navBarComponent: NavBarGoldService
  ) {
    //check for login
    unRegService.logInDetected.subscribe(
      (userIn) => (this.loginActive = userIn)
    );

    //bank user presence
    unRegService.userDetails.subscribe((data) => {
      this.userIsLoggedIn = data != null;
    });

    //employee
    unRegService.employeeIsLoggedIn.subscribe(
      (employee) => (this.employeeLoggedIn = employee)
    );
    console.log('nav Constructor called');

    this.navBarComponent.inEmployeeHomeComponent.subscribe((mathewBank) => {
      console.log('Home is -' + mathewBank);
      this.employeeHomeSelected = mathewBank;
    });
    this.navBarComponent.onMathewsBank.subscribe((home) => {
      console.log('Home is -' + home);
      this.mathewBackHome = home;
    });
    //this.mathewBackHome = this.navBarComponent.onMathewsBank;
    console.log(this.employeeHomeSelected);
    console.log(this.mathewBackHome);
  }

  // private falseFire() {
  //   this.loginSelected = false;
  //   this.mathewBackHome = false;
  //   this.mathewBackHome = false;
  //   this.employeeHomeSelected = false;
  // }

  // private urlToStyleResolver(path: string) {
  //   console.log('ROUTE NAME -----' + path);
  //   switch (path) {
  //     case '/log-in':
  //       this.loginSelected = true;
  //       break;
  //     case '/welcome':
  //       this.mathewBackHome = true;
  //       break;
  //     case '/user-welcome':
  //       this.mathewBackHome = true;
  //       break;
  //     case '/employee-welcome':
  //       console.log('ROUTE WELCOME NAME -----' + path);
  //       this.employeeHomeSelected = true;
  //       break;
  //   }
  // }

  userHomePage() {
    this.router.navigate(['user-welcome']);
  }
  empHome() {
    this.router.navigate(['employee-welcome']);
    console.log(`nav bar ${this.loginActive}`);
    this.employeeHomeSelected = true;
  }

  loginPage() {
    this.router.navigate(['log-in']);
  }

  homePageRedirect() {
    this.router.navigate(['welcome']);
  }

  logOutPage() {
    //emit a logout that user is no longer longed in
    this.unRegService.logInDetected.next(false);
    this.loginActive = false;
    this.router.navigate(['welcome']);
  }
}
