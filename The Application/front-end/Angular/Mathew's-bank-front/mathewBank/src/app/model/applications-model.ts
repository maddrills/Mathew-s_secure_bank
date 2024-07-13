export class applicationsModel {
  constructor(
    public age: number = 0,
    public applicationId: number = 0,
    public appliedOn: string,
    public branchId: number = 0,
    public dateOfBirth: string,
    public email: string = '',
    public fullName: string = '',
    public phoneNumber: string = '',
    public rejected: boolean = false,
    public status: boolean = false,
    public assignedTo: number = 0
  ) {}
}
