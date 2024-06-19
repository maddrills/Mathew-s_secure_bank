import { rolesModel } from './roles-model';
import { UserAccountModel } from './user-account-model';

export class UserModel {
  constructor(
    public userId: number = 0,
    public userName: string = '',
    public branchId: number = 0,
    public fullName: string = '',
    public phoneNumber: string = '',
    public dateOfBerth: string = '',
    public age: number = 0,
    public email: string = '',
    public userAccountDTO: UserAccountModel | null = null,
    public rolesDto: rolesModel[] | null = null
  ) {}
}
