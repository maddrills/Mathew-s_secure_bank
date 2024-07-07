import { rolesModel } from './roles-model';
import { UserAccountModel } from './user-account-model';

export class EmployeeDataModel {
  constructor(
    public empId: number = 0,
    public reportsTo: number = 0,
    public branchId: number = 0,
    public empDetailsId: number = 0,
    public phone_number: string = '',
    public full_name: string = '',
    public email: string = '',
    public dateOfBirth: string = '',
    public salary: number = 0,
    public salaryAccount: number = 0,
    public branchName: string = '',
    public rolesName: rolesModel[] | null = null
  ) {}
}
