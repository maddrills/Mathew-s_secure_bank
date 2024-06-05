package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;

//extended by empRepo and userRepo
public interface User_emp_commonRepo {

    public Role findRoleByRoleName(String name);

    public Branch findBranchByName(String name);

    public Savings getSavingsAccountByNumber(int accountNumber);

    public Branch getABranchById(final int BranchId);

}
