import { Component } from '@angular/core';
import { NavBarGoldService } from '../../../service/navBarService';
import { NavBarComponent } from '../../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../../top-down/footer-section/footer-section.component';
import { RefreshDataFetcherService } from '../../../service/dataRefresh';

@Component({
  selector: 'app-branch-management',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent],
  templateUrl: './branch-management.component.html',
  styleUrl: './branch-management.component.css',
})
export class BranchManagementComponent {
  constructor(
    private navBarGoldService: NavBarGoldService,
    private userDataRefreshUpDate: RefreshDataFetcherService
  ) {
    this.navBarGoldService.resetAll();
    this.navBarGoldService.bankManagement.next(true);
    userDataRefreshUpDate.checkIfEmployeeDataAvailable();
  }
}
