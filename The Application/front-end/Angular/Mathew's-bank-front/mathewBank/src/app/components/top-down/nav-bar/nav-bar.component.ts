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
  public empManagementSelected: boolean = false;
  public bankManagementSelected: boolean = false;

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

    //nav bar stuff
    this.navBarComponent.inEmployeeHomeComponent.subscribe((mathewBank) => {
      this.employeeHomeSelected = mathewBank;
    });
    this.navBarComponent.onMathewsBank.subscribe((home) => {
      this.mathewBackHome = home;
    });
    this.navBarComponent.empManagement.subscribe((empMan) => {
      this.empManagementSelected = empMan;
    });
    this.navBarComponent.bankManagement.subscribe((bankManagement) => {
      this.bankManagementSelected = bankManagement;
    });
    //log in
    this.navBarComponent.logIn.subscribe(
      (logIn) => (this.loginSelected = logIn)
    );
    //this.mathewBackHome = this.navBarComponent.onMathewsBank;
    console.log(this.employeeHomeSelected);
    console.log(this.mathewBackHome);
  }

  userHomePage() {
    this.router.navigate(['user-welcome']);
  }
  empHome() {
    this.router.navigate(['employee-welcome']);
    console.log(`nav bar ${this.loginActive}`);
  }

  //needs improvement as it should work with activated router
  empManagement() {
    console.log(this.activatedRoute.snapshot.url);
    this.router.navigate(['employee-welcome/emp-management'], {
      //relativeTo: this.activatedRoute,
    });
  }

  bankManagement() {
    this.router.navigate(['employee-welcome/bank-management']);
  }

  loginPage() {
    this.router.navigate(['log-in']);
  }

  homePageRedirect() {
    this.router.navigate(['welcome']);
  }

  logOutPage() {
    //emit a logout that user is no longer longed in
    this.loginActive = false;
    //local storage null
    localStorage.clear();
    this.router.navigate(['welcome']);
    this.unRegService.logUserOut();
  }
}
