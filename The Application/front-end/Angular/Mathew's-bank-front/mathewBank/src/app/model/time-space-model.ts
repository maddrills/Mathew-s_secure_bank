export class AccountTimeSpace {
  constructor(
    public accountType: string,
    public second: number = 0,
    public min: number = 0,
    public hour: number = 0,
    public days: number = 0,
    public months: number = 0,
    public years: number = 0,
    public baseInterestRate: number = 0,
    public ajointAccount: boolean = false,
    public minStartingAmount: number = 0,

    public withdrawalCountLimit: number = 0,
    public moneyTransferLimit: number = 0,
    public baseLimit: number = 0,
    public monthlyDraw: number = 0,
    public dailyDraw: number = 0,
    public hourlyDraw: number = 0,
    public minutesDraw: number = 0,
    public periodic: boolean = false
  ) {}
}
