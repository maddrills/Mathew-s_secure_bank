import { Component } from '@angular/core';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { BankApplicationsComponent } from './bank-applications/bank-applications.component';
import { AssignedApplicationsComponent } from './assigned-applications/assigned-applications.component';

@Component({
  selector: 'app-employee-logged-in',
  standalone: true,
  imports: [
    NavBarComponent,
    FooterSectionComponent,
    BankApplicationsComponent,
    AssignedApplicationsComponent,
  ],
  templateUrl: './employee-logged-in.component.html',
  styleUrl: './employee-logged-in.component.css',
})
export class EmployeeLoggedInComponent {}
