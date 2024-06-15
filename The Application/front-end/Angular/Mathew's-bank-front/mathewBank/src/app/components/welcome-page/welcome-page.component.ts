import { Component } from '@angular/core';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';

@Component({
  selector: 'app-welcome-page',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent],
  templateUrl: './welcome-page.component.html',
  styleUrl: './welcome-page.component.css',
})
export class WelcomePageComponent {}
