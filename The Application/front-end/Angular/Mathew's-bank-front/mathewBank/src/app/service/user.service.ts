import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from '../model/user-model';
import { ApplicationHttpRoutes } from '../constants/http-routes';
import { UserAccountModel } from '../model/user-account-model';
import { UserAccountDeepModel } from '../model/user-account-deep-model';

@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) {}

  public getAllBankBranches() {
    return this.http.get<UserModel>(ApplicationHttpRoutes.LIST_ALL_BRANCHES, {
      observe: 'response',
      withCredentials: true,
    });
  }

  public getAllUserBankAccounts() {
    return this.http.get<UserAccountDeepModel>(
      ApplicationHttpRoutes.GET_ALL_USER_ACCOUNTS,
      {
        observe: 'response',
        withCredentials: true,
      }
    );
  }
}
