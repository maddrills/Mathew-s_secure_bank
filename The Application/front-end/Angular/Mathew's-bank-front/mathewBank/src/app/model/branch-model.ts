export class BranchModel {
  constructor(
    public branchId = 0,
    public branchName = '',
    public state = '',
    public country = '',
    public open: boolean | string = false,
    public branchManagerId = 0
  ) {}
}
