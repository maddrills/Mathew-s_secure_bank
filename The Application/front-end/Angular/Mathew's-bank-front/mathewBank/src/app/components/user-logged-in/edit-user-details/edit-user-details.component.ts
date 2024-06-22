import { Component } from '@angular/core';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';

@Component({
  selector: 'app-edit-user-details',
  standalone: true,
  imports: [NavBarComponent, FooterSectionComponent],
  templateUrl: './edit-user-details.component.html',
  styleUrl: './edit-user-details.component.css',
})
export class EditUserDetailsComponent {}
