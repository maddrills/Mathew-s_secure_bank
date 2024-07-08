import { Component } from '@angular/core';
import { AssignedApplicationsComponent } from '../employee-logged-in/assigned-applications/assigned-applications.component';
import { SubEmployeesComponent } from './sub-employees/sub-employees.component';
import { NavBarGoldService } from '../../service/navBarService';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';

@Component({
  selector: 'app-employee-management',
  standalone: true,
  imports: [
    FooterSectionComponent,
    NavBarComponent,
    AssignedApplicationsComponent,
    SubEmployeesComponent,
  ],
  templateUrl: './employee-management.component.html',
  styleUrl: './employee-management.component.css',
})
export class EmployeeManagementComponent {
  constructor(private navBarGoldService: NavBarGoldService) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.empManagement.next(true);
  }
}
