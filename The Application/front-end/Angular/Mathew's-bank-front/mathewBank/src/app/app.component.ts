import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ApplyForAccountComponent } from './components/apply-for-account/apply-for-account.component';
import { LogInPageComponent } from './components/log-in-page/log-in-page.component';
import { CommonModule } from '@angular/common';
import { EmployeeLoggedInComponent } from './components/employee-logged-in/employee-logged-in.component';

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
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'mathewBank';
}
