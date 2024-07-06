import { rolesModel } from './roles-model';
import { UserAccountModel } from './user-account-model';

export class EmployeeDataModel {
  constructor(
    public empId: number = 0,
    public reportsTo: number = 0,
    public branchId: number = 0,
    public empDetailsId: number = 0,
    public phoneNumber: string = '',
    public empName: string = '',
    public email: string = '',
    public dob: string = '',
    public salary: number = 0,
    public salaryAccount: number = 0,
    public rolesDTO: rolesModel[] | null = null
  ) {}
}
