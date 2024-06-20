import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';

@Component({
  selector: 'app-user-welcome',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent],
  templateUrl: './user-welcome.component.html',
  styleUrl: './user-welcome.component.css',
})
export class UserWelcomeComponent {
  userName = 'Mathew Francis';
}
