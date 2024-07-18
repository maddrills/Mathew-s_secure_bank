import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from '../model/user-model';
import { ApplicationHttpRoutes } from '../constants/http-routes';
import { UserAccountDeepModel } from '../model/user-account-deep-model';
import { AccountTimeSpace } from '../model/time-space-model';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UserService {
  public selectedAccountByUser =
    new BehaviorSubject<UserAccountDeepModel | null>(null);

  constructor(private http: HttpClient) {}

  public getAllBankBranches() {
    return this.http.get<UserModel>(ApplicationHttpRoutes.LIST_ALL_BRANCHES, {
      observe: 'response',
      withCredentials: true,
    });
  }

  public getAllUserBankAccounts() {
    return this.http.get<UserAccountDeepModel[]>(
      ApplicationHttpRoutes.GET_ALL_USER_ACCOUNTS,
      {
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  //GET_ACCOUNT_SETTINGS
  public getAccountSettingsByAccountName(accountName: string) {
    return this.http.get<AccountTimeSpace>(
      ApplicationHttpRoutes.GET_ACCOUNT_SETTINGS,
      {
        params: new HttpParams().set('accountType', accountName),
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  public getAllAccountSetting() {
    return this.http.get<AccountTimeSpace[]>(
      ApplicationHttpRoutes.GET_ALL_ACCOUNT_SETTINGS,
      {
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  //TRANSFER_MONEY_PATCH
  public moneyTransfer(from: number, to: number, amount: number) {
    return this.http.patch<boolean>(
      ApplicationHttpRoutes.TRANSFER_MONEY_PATCH,
      null,
      {
        params: new HttpParams()
          .set('accountNumberFrom', from)
          .set('accountNumberTo', to)
          .set('amount', amount),
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  public createABankAccount(accountName: string, initialAmount: number) {
    return this.http.put<boolean>(ApplicationHttpRoutes.TRANSFER_MONEY_PATCH, {
      params: new HttpParams()
        .set('accountName', accountName)
        .set('initialAmount', initialAmount),
      observe: 'response',
      withCredentials: true,
    });
  }
}
