import { Component } from '@angular/core';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';

@Component({
  selector: 'app-log-in-page',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent],
  templateUrl: './log-in-page.component.html',
  styleUrl: './log-in-page.component.css',
})
export class LogInPageComponent {}
