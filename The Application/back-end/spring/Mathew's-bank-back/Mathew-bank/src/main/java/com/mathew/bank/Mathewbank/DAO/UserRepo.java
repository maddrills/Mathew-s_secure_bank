package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;

public interface UserRepo extends User_emp_commonRepo{

    public void createAUserInBank(User user, String branch);
    public void applyForBankAccount(final int BranchId,final UserApplication userApplication);
}
