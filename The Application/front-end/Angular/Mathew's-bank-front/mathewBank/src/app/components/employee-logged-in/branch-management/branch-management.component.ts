import { Component } from '@angular/core';
import { NavBarGoldService } from '../../../service/navBarService';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';

@Component({
  selector: 'app-branch-management',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent],
  templateUrl: './branch-management.component.html',
  styleUrl: './branch-management.component.css',
})
export class BranchManagementComponent {
  constructor(private navBarGoldService: NavBarGoldService) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.bankManagement.next(true);
  }
}
