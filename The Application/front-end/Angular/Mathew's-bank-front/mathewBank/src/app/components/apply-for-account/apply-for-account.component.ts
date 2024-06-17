import { Component } from '@angular/core';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';

@Component({
  selector: 'app-apply-for-account',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent],
  templateUrl: './apply-for-account.component.html',
  styleUrl: './apply-for-account.component.css',
})
export class ApplyForAccountComponent {}
