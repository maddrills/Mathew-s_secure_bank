export class UserAccountModel {
  constructor(
    public userAccountID: number = 0,
    public savingsID: number = 0,
    public checkingID: number = 0,
    public buildUpID: number = 0,
    public jointAccountsID: number = 0,
    public frozen: boolean = false
  ) {}
}
