export class TransactionsModel {
  constructor(
    public id: number,

    public transactionDescription: string = '',

    public toAccountNumber: number,

    public fromAccountNumber: number,

    public transactionDate: string = '',

    public deposited: boolean = false,

    public amount: number,

    public remainingAmount: number
  ) {}
}
