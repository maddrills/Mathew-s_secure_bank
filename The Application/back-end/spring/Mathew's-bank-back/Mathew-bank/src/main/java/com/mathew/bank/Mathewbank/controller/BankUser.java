package com.mathew.bank.Mathewbank.controller;

import com.mathew.bank.Mathewbank.DAO.UserRepository;
import com.mathew.bank.Mathewbank.DTO.*;
import com.mathew.bank.Mathewbank.service.EmployeeService;
import com.mathew.bank.Mathewbank.service.UserInBankService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/bankUser")
public class BankUser {

    @Autowired
    private UserInBankService userInBankService;

    @Autowired
    private EmployeeService employeeService;

    //view profile details
    // get them by user_id from JWT
    // https://stackoverflow.com/questions/54909509/accessing-jwt-token-from-a-spring-boot-rest-controller
    @GetMapping("/user-details")
    public UserAndDetailsDTO getUserDetails(Authentication authentication, HttpServletResponse response) {
        //for now ill be using 2
        System.out.println(authentication.getName());
        return userInBankService.getUserAndUserDetailsFromService(authentication, response);
    }


    @GetMapping("/get_all_accounts")
    public List<UserDeepAccountDTO> getAllUsersAccounts(HttpServletResponse response, Authentication authentication){

        return this.userInBankService.getAllUserAccounts(response, authentication);
    }

    @GetMapping("/get_all_account_settings")
    public List<TimeSpaceDTO> getAllAccountSettings(){
        return this.userInBankService.getAllAccountTypes();
    }

    @GetMapping("/get_account_settings")
    public TimeSpaceDTO getAccountSettings(@RequestParam String accountType, Authentication authentication){
        return this.userInBankService.getAccountSettingsByAccountType(accountType, authentication);
    }


    //create savings account
    @PutMapping("/user-add-savings-account")
    public boolean createASavingAccount(Authentication authentication,HttpServletResponse response) {

        System.out.println(authentication.getName());

        return this.userInBankService.createASavingsAccount(authentication.getName(), response);
    }

    //TODO will be implemented after UI
    //create checking account
    public boolean createACheckingAccount(@RequestParam int userId, HttpServletResponse response) {
        return false;
    }

    //create buildup account
    public boolean createABuildUpAccount(@RequestParam int userId, HttpServletResponse response) {
        return false;
    }

    @PatchMapping("/send-money-via-account-number")
    public boolean transferMoneyByAccountNumber(@RequestParam int accountNumberFrom,@RequestParam int accountNumberTo, @RequestParam double amount, Authentication authentication, HttpServletResponse response){

        System.out.println("From -> "+accountNumberFrom+" To -> "+accountNumberTo+" amount -> "+amount);

        return this.userInBankService.transferMoneyAndUpdateBothAccounts(accountNumberFrom, accountNumberTo, amount, authentication, response);
    }

    @PutMapping("/createNewSavingsAccount")
    public boolean createUserBankAccount(@RequestParam String accountName,@RequestParam double initialAmount, Authentication authentication, HttpServletResponse httpServletResponse){

        System.out.println(authentication.getName()+"-----"+accountName+"----"+initialAmount);

        return this.userInBankService.createABankAccount(accountName, initialAmount, authentication, httpServletResponse);
    }

    @GetMapping("/get_all_user_transactions")
    public Collection<TransactionsDTO> getTransactionsForAUser(Authentication authentication, HttpServletResponse httpServletResponse){

        try{
            return this.userInBankService.getAllUserTransactions(authentication, httpServletResponse);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }

    @GetMapping("/getUserBankAccounts")
    public Collection<UserDeepAccountDTO> getAllUserAccountsByThereAuths(Authentication authentication, HttpServletResponse httpServletResponse){

        try{
            this.userInBankService.getAllUserAccountsByUserAuth(authentication, httpServletResponse).forEach(System.out::println);
            return this.userInBankService.getAllUserAccountsByUserAuth(authentication, httpServletResponse);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

    }


    //returns two types of outputs either of type UserAndDetailsDTO or EmployeeDTO
    // the generic <T extends AllowedLoginOutputGeneric> is used to enforce the above condition
    @SuppressWarnings("unchecked")
    @GetMapping("/login")
    public <T extends AllowedLoginOutputGeneric> T loginUser(HttpServletResponse response, Authentication authentication) {

        String nameOrID = authentication.getName().split(",")[0];

        int uId = 0;
        try {
            uId = Integer.parseInt(authentication.getName().split(",")[1]);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(authentication.getName());

        boolean proceedWithUser = true;
        int employeeId = 0;
        try {
            employeeId = Integer.parseInt(nameOrID);
            proceedWithUser = false;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (proceedWithUser) {
            return (T) this.userInBankService.getUserAndUserDetailsFromService(nameOrID, response);
        }
        System.out.println("Get emp by id");
        return (T) this.employeeService.getEmployeeDetailsById(employeeId, response);
    }
}
