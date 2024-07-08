import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class NavBarGoldService {
  public onMathewsBank = new BehaviorSubject<boolean>(false);
  public inEmployeeHomeComponent = new BehaviorSubject<boolean>(false);
  public logIn = new BehaviorSubject<boolean>(false);

  public empManagement = new BehaviorSubject<boolean>(false);
  public bankManagement = new BehaviorSubject<boolean>(false);

  public resetAll() {
    this.onMathewsBank.next(false);
    this.inEmployeeHomeComponent.next(false);
    this.logIn.next(false);
    this.empManagement.next(false);
    this.bankManagement.next(false);
  }
}
