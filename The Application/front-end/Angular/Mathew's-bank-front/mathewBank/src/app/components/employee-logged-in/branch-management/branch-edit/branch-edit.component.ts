import { Component } from '@angular/core';
import { EmployeeManagementComponent } from '../../../employee-management/employee-management.component';

@Component({
  selector: 'app-branch-edit',
  standalone: true,
  imports: [EmployeeManagementComponent],
  templateUrl: './branch-edit.component.html',
  styleUrl: './branch-edit.component.css',
})
export class BranchEditComponent {}
