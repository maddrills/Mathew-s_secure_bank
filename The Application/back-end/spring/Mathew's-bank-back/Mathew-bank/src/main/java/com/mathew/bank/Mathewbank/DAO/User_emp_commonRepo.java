package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.DTO.UserAndDetailsDTO;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

//extended by empRepo and userRepo
public interface User_emp_commonRepo {

    public Role findRoleByRoleName(String name);

    public Branch findBranchByName(String name);

    public Savings getSavingsAccountByNumber(int accountNumber);

    public Branch getABranchById(final int branchId);

    public List<Branch> getAllBranchFromDB();

    boolean createASavingsAccountForUser(int userId, int accountId, HttpServletResponse response);

    User getUserDetailsByUserName(String userName);
}
