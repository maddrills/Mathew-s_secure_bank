export class UserAccountDeepModel {
  constructor(
    public id: number = 0,
    public hold: boolean = false,
    public active: boolean = false,
    public amount: number = 0,
    public nextInterestOn: string = '',
    public createdOn: string = '',
    public frozen: boolean = false,
    public jointAccount: boolean = false,
    public accountTypeName: string = '',
    public lastWithdrawalDate: string = '',
    public periodicWithdrawalCount: number = 0,
    public WithdrawalCount: number = 0
  ) {}
}
