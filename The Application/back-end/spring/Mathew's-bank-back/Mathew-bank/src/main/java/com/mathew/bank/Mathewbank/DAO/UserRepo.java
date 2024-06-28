package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserRepo extends User_emp_commonRepo{

    public void applyForBankAccount(final int BranchId,final UserApplication userApplication);

    public List<Branch> getBranchesByCountryAndName(String country, String state);

    public UserApplication getUserApplicationDetailsByPhoneNumber(String phoneNumber);
    public UserApplication getUserApplicationDetailsByEmail(String email);

    public User getUserFromDb(int usedId);

    boolean transferMoneyFromUserAccountToAnother(int accountNumberFrom, int accountNumberTo, int amount, int userId, int accountID);
}
