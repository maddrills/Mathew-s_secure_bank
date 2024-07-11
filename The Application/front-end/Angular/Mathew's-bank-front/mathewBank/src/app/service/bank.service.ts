import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BranchModel } from '../model/branch-model';
import { ApplicationHttpRoutes } from '../constants/http-routes';

@Injectable({ providedIn: 'root' })
export class BankService {
  constructor(private http: HttpClient) {}

  public getAllBankBranches() {
    return this.http.get<BranchModel[]>(
      ApplicationHttpRoutes.LIST_ALL_BRANCHES,
      {
        observe: 'response',
        withCredentials: true,
      }
    );
  }

  public creaateAbranch(branch: BranchModel) {
    return this.http.post<any>(
      ApplicationHttpRoutes.ADD_A_BRANCH_WITH_OR_WITHOUT_A_MANAGER,
      branch,
      {
        observe: 'response',
        withCredentials: true,
      }
    );
  }
}
