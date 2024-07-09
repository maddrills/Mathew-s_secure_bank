import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-employee-management-shell',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './employee-management-shell.component.html',
  styleUrl: './employee-management-shell.component.css',
})
export class EmployeeManagementShellComponent {}
