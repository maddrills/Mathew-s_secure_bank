import { Component } from '@angular/core';
import { PermissionSetComponent } from '../permission-set/permission-set.component';

@Component({
  selector: 'app-add-new-employee',
  standalone: true,
  imports: [PermissionSetComponent],
  templateUrl: './add-new-employee.component.html',
  styleUrl: './add-new-employee.component.css',
})
export class AddNewEmployeeComponent {}
