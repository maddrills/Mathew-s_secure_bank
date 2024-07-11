import { Component } from '@angular/core';
import { NavBarComponent } from '../top-down/nav-bar/nav-bar.component';
import { FooterSectionComponent } from '../top-down/footer-section/footer-section.component';
import { FormsModule, NgForm } from '@angular/forms';
import { UnRegService } from '../../service/unRegService';
import { BranchModel } from '../../model/branch-model';

@Component({
  selector: 'app-apply-for-account',
  standalone: true,
  imports: [FooterSectionComponent, NavBarComponent, FormsModule],
  templateUrl: './apply-for-account.component.html',
  styleUrl: './apply-for-account.component.css',
})
export class ApplyForAccountComponent {
  //Array List
  countryNameArray: string[] = [];
  stateNameArray: string[] = [];
  branchNameArray: BranchModel[] = [];
  branchArrayPosition: number = -1;

  //UI conditional
  countryActive: boolean = false;
  stateActive: boolean = false;
  branchActive: boolean = false;

  //selections
  countryName: string = '';
  stateName: string = '';
  branchName: string = '';

  constructor(private unRegService: UnRegService) {
    unRegService.getCountryNames().subscribe({
      next: (nextIng) => {
        console.log(nextIng.body);
        this.countryNameArray = nextIng.body!;
      },
      error: (errorIng) => console.log(errorIng, 'Error Logging out'),
    });
  }

  //FORM submission
  onSubmitApplication(fullForm: NgForm) {
    const formData = fullForm.form.value;
    const age = 50;

    console.log('Branch Position is', this.branchArrayPosition);

    if (this.branchArrayPosition < 0) return;

    console.log(formData, this.branchNameArray[this.branchArrayPosition]);

    this.unRegService
      .applyForAnAccount(
        this.branchNameArray[this.branchArrayPosition].branchId,
        formData?.firstName + ' ' + formData?.lastName,
        formData?.phoneNumber,
        formData?.dateOfBirth,
        formData?.email,
        age
      )
      .subscribe({
        next: (n) => {
          console.log(n);
          fullForm.resetForm();
        },
        error: (e) => console.log(e),
      });

    //this.unRegService.applyForAnAccount();
  }

  country(countryName: string) {
    if (this.countryName != countryName) {
      this.stateName = '';
      this.branchName = '';

      //get state name
      this.unRegService.getStateNames(countryName).subscribe({
        next: (nextIng) => {
          console.log(nextIng.body);
          this.stateNameArray = nextIng.body!;
        },
        error: (errorIng) => console.log(errorIng, 'Error Logging out'),
      });
    }
    this.countryName = countryName;
    console.log(countryName);
    this.countryActive = false;
  }

  state(stateName: string) {
    if (this.stateName != stateName) {
      this.branchName = '';
    }
    //get branch
    this.unRegService
      .getBranchByCountryAndState(this.countryName, stateName)
      .subscribe({
        next: (nextIng) => {
          console.log(nextIng.body);
          this.branchNameArray = nextIng.body!;
        },
        error: (errorIng) => console.log(errorIng, 'Error Logging out'),
      });
    this.stateName = stateName;
    console.log(stateName);
    this.stateActive = false;
  }

  // UI setting a branch
  branch(branchName: string, branchIdNumber: number) {
    this.branchName = branchName;
    console.log(branchName, branchIdNumber);
    this.branchArrayPosition = branchIdNumber;
    this.branchActive = false;
  }
}
