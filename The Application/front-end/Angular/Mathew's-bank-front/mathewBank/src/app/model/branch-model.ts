export class RolesModel {
  constructor(
    public branchId = 0,
    public branchName = '',
    public state = '',
    public country = '',
    public open = false,
    public branchManagerId = 0
  ) {}
}
