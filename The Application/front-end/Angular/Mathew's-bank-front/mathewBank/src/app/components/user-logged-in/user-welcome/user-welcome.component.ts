import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-welcome',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent, CommonModule],
  templateUrl: './user-welcome.component.html',
  styleUrl: './user-welcome.component.css',
})
export class UserWelcomeComponent {
  userName = 'Mathew Francis';

  savingsVisible: boolean = false;
  checkingVisible: boolean = false;
  buildUpVisible: boolean = false;

  visibilityInverter(arg0: boolean) {
    arg0 = !arg0;
  }
}
