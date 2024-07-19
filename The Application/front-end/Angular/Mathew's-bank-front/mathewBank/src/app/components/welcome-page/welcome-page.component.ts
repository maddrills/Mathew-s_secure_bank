import { Component } from '@angular/core';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { Router } from '@angular/router';
import { NavBarGoldService } from '../../service/navBarService';
import { EmployeePermissionService } from '../../service/employee-permission.service';

@Component({
  selector: 'app-welcome-page',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent],
  templateUrl: './welcome-page.component.html',
  styleUrl: './welcome-page.component.css',
})
export class WelcomePageComponent {
  constructor(
    private router: Router,
    private navBarGoldService: NavBarGoldService
  ) //private employeePermissionService: EmployeePermissionService
  {
    //this.navBarGoldService.resetAll();
    console.log('Home Component');
    this.navBarGoldService.resetAll();
    this.navBarGoldService.onMathewsBank.next(true);
    console.log('Home Component END');
    // this.employeePermissionService.checkUserAuth();
  }

  applicationForm() {
    console.log('click');
    this.router.navigate(['application']);
  }

  scrollToElement($element: any) {
    console.log($element);
    $element.scrollIntoView({
      behavior: 'smooth',
      block: 'start',
      inline: 'nearest',
    });
  }
}
