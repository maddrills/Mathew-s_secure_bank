import { Component } from '@angular/core';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { UnRegService } from '../../service/unRegService';

@Component({
  selector: 'app-log-in-page',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent, CommonModule, FormsModule],
  templateUrl: './log-in-page.component.html',
  styleUrl: './log-in-page.component.css',
})
export class LogInPageComponent {
  constructor(private unRegService: UnRegService) {}

  onSubmit(formSettings: NgForm) {
    const formDataFields = formSettings.form.value;
    // empty credentials input
    if (
      formDataFields.UserNameOrEmp == '' ||
      formDataFields.UserPassword == ''
    ) {
      console.log('Null');
      return;
    }

    this.unRegService.commonUserLogin(
      formDataFields.UserNameOrEmp,
      formDataFields.UserPassword
    );
  }
}
