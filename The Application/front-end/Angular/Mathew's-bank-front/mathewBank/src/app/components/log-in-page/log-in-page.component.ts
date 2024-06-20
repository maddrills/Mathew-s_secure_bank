import { Component } from '@angular/core';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { UnRegService } from '../../service/unRegService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-log-in-page',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent, CommonModule, FormsModule],
  templateUrl: './log-in-page.component.html',
  styleUrl: './log-in-page.component.css',
})
export class LogInPageComponent {
  constructor(private unRegService: UnRegService, private router: Router) {}

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

    //send details to backend
    this.unRegService.commonUserLogin(
      formDataFields.UserNameOrEmp,
      formDataFields.UserPassword
    );

    // this.unRegService.userDetails.subscribe((userDetails) => {
    //   console.log('From User');
    //   console.log(userDetails);
    // });

    this.unRegService.userIsLoggedIn.subscribe((isUser) => {
      console.log(isUser);

      if (isUser) {
        this.router.navigate(['user-welcome']);
      }
    });
  }
}
