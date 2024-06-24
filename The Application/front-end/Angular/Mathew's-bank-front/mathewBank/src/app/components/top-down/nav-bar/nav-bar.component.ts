import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UnRegService } from '../../../service/unRegService';
import { CommonModule } from '@angular/common';

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

  // user login control
  public loginSelected: boolean = false;
  public loginUserHome: boolean = false;

  constructor(
    private router: Router,
    private unRegService: UnRegService,
    private activatedRoute: ActivatedRoute
  ) {
    unRegService.userIsLoggedIn.subscribe(
      (userIn) => (this.loginActive = userIn)
    );
    console.log(`nav bar ${this.loginActive}`);

    const url = this.router.url;

    console.log(url);
    // if (url === '/log-in') {
    //   this.loginSelected = true;
    // } else if (url === '/welcome') {
    //   this.mathewBackHome = true;
    // }

    this.urlToStyleResolver(url);
  }

  private urlToStyleResolver(path: string) {
    switch (path) {
      case '/log-in':
        this.loginSelected = true;
        break;
      case '/welcome':
        this.mathewBackHome = true;
        break;
      case '/user-welcome':
        this.loginUserHome = true;
        break;
    }
  }

  userHomePage() {
    this.router.navigate(['user-welcome']);
  }

  loginPage() {
    this.router.navigate(['log-in']);
  }

  homePageRedirect() {
    this.router.navigate(['welcome']);
  }

  logOutPage() {
    //emit a logout that user is no longer longed in
    this.unRegService.userIsLoggedIn.next(false);
    this.loginActive = false;
    this.router.navigate(['welcome']);
  }
}
