import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
})
export class NavBarComponent {
  loginPage() {
    this.router.navigate(['log-in']);
  }
  constructor(private router: Router) {}

  homePageRedirect() {
    this.router.navigate(['welcome']);
  }
}
