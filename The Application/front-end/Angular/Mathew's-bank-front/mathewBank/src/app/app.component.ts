import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ApplyForAccountComponent } from './components/apply-for-account/apply-for-account.component';
import { LogInPageComponent } from './components/log-in-page/log-in-page.component';
import { CommonModule } from '@angular/common';
import { EmployeeLoggedInComponent } from './components/employee-logged-in/employee-logged-in.component';
import { FooterSectionComponent } from './components/top-down/footer-section/footer-section.component';
import { NavBarComponent } from './components/top-down/nav-bar/nav-bar.component';
import { EmployeePermissionService } from './service/employee-permission.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    CommonModule,
    WelcomePageComponent,
    ApplyForAccountComponent,
    LogInPageComponent,
    EmployeeLoggedInComponent,
    NavBarComponent,
    FooterSectionComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  constructor(private employeePermissionService: EmployeePermissionService) {
    // private employeePermissionService: EmployeePermissionService
    this.employeePermissionService.checkUserAuth();
  }
  title = 'mathewBank';
}
