package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.UserRepository;
import com.mathew.bank.Mathewbank.DTO.*;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.Transactions;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserInBankService {

    @Autowired
    private UserRepository userRepository;

    public UserAndDetailsDTO getUserAndUserDetailsFromService(String userName, HttpServletResponse response) {

        try {
            User user = this.userRepository.getUserDetailsByUserName(userName);

            UserDetails userDetails = user.getUserDetails();

            final Collection<RolesDto> rolesDtos = new HashSet<>();

            UserAccounts userAccounts = user.getUserAccountId();

            //assign roles to the RolesDto collection
            userDetails.getUserId().getRoles().forEach(role -> rolesDtos.add(new RolesDto(role.getRole(), true)));

            //create a collection of AccountDeapDTO
            //final Collection<UserDeepAccountDTO> userDeepAccountDTOS = new LinkedList<>();

            //transfer each value to a DTO
//            userAccounts.getAllUserAccounts().forEach(
//                    account -> {
//
//                        System.out.println(account.getAmount() - 50);
//
//                        userDeepAccountDTOS.add(new UserDeepAccountDTO(
//                                account.getId(),
//                                account.isHold(),
//                                account.isActive(),
//                                account.getAmount(),
//                                account.getNextInterestOn(),
//                                account.getCreatedOn(),
//                                account.isFrozen(),
//                                account.isJointAccount(),
//                                account.getAccountType()
//                        ));
//                    });

            /*
            *         this.userId = userId;
        this.userName = userName;
        this.userAccountsNumber = userAccountNumber;
        this.branchId = branchId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
            * */

            return new UserAndDetailsDTO(
                    userDetails.getId(),
                    user.getUserName(),
                    null,
                    user.getBranchId().getId(),
                    userDetails.getFullName(),
                    userDetails.getPhoneNumber(),
                    userDetails.getDateOfBerth(),
                    userDetails.getAge(),
                    userDetails.getEmail(),
                    rolesDtos,
                    userAccounts.getId()
                    );
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e);
            return null;
        }
    }


    public UserAndDetailsDTO getUserAndUserDetailsFromService(Authentication authentication, HttpServletResponse response) {

        UserAuthDecodedValues userAuthDecodedValues = this.authenticatedUserDecoder(authentication.getName());

        User user;
        UserDetails userDetails;
        UserAccounts userAccounts;

        try {
            user = this.userRepository.getUserFromDb(userAuthDecodedValues.getUserId());
            userDetails = user.getUserDetails();
            //get the account details like savings account build up etc etc
            userAccounts = user.getUserAccountId();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        //sanity check
        if (userDetails == null) return null;

        //create a collection of AccountDeapDTO
        final Collection<UserDeepAccountDTO> userDeepAccountDTOS = new LinkedList<>();

        //transfer each value to a DTO
        userAccounts.getAllUserAccounts().forEach(
                account -> userDeepAccountDTOS.add(new UserDeepAccountDTO(
                        account.getId(),
                        account.isHold(),
                        account.isActive(),
                        account.getAmount(),
                        account.getNextInterestOn(),
                        account.getCreatedOn(),
                        account.isFrozen(),
                        account.isJointAccount(),
                        account.getAccountType()
                ))
        );


        //now get the credentials from entity to TDO
        return new UserAndDetailsDTO(
                userDetails.getId(),
                userDetails.getFullName(),
                null,
                0,
                userDetails.getFullName(),
                userDetails.getPhoneNumber(),
                userDetails.getDateOfBerth(),
                userDetails.getAge(),
                userDetails.getEmail(),
                new UserAccountDTO(
                        userAccounts.getId(),
                        userDeepAccountDTOS,
                        userAccounts.isFrozen()
                )
        );
    }

    public boolean createASavingsAccount(String userAuth, HttpServletResponse response) {

        UserAuthDecodedValues userAuthDecodedValues = this.authenticatedUserDecoder(userAuth);
        System.out.println(userAuthDecodedValues);

        //sanity check unnecessary sanity check
        if (userAuthDecodedValues.userId <= 0 || userAuthDecodedValues.accountID <= 0) {

            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return false;
        }

        //create a users bank account
        try {
            return this.userRepository.createASavingsAccountForUser(userAuthDecodedValues.userId, userAuthDecodedValues.accountID, response);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean transferMoneyAndUpdateBothAccounts(int accountNumberFrom, int accountNumberTo, double amount, Authentication authentication, HttpServletResponse response) {

        UserAuthDecodedValues userAuthDecodedValues = this.authenticatedUserDecoder(authentication.getName());
        System.out.println(userAuthDecodedValues);

        //sanity check
        if (accountNumberFrom <= 0 || accountNumberTo <= 0 || amount <= 0) {

            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return false;
        }

        if(accountNumberFrom == accountNumberTo){
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return false;
        }

        String amountInnStringForm = Double.toString(amount);
        //System.out.println(amountInnStringForm);
        int decimalPlace = amountInnStringForm.indexOf(".");
        //System.out.println(amountInnStringForm.substring(decimalPlace));
        int numberOfDecimalPlaces = amountInnStringForm.substring(decimalPlace).length();
        //System.out.println(numberOfDecimalPlaces);
        if(numberOfDecimalPlaces > 3){
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return false;
        }

        //create a users bank account
        try {
            return this.userRepository.transferMoneyFromUserAccountToAnother(accountNumberFrom, accountNumberTo, amount, userAuthDecodedValues.userId, userAuthDecodedValues.accountID);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean createABankAccount(String accountName, double initialAmount, Authentication authentication, HttpServletResponse httpServletResponse) {

        //sanity checks

        //check if initial amount is positive
        if(initialAmount <= 0){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        //check if account name is a string
        try{
            Integer.parseInt(accountName);
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        catch (Exception e){
            System.out.println(e);
        }

        UserAuthDecodedValues userAuthDecodedValues = this.authenticatedUserDecoder(authentication.getName());

        try{
            return this.userRepository.addBankAccountToUser(accountName, initialAmount, userAuthDecodedValues.getUserId(),
                    userAuthDecodedValues.getAccountID(), userAuthDecodedValues.getUserName());

        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    public List<UserDeepAccountDTO> getAllUserAccounts(HttpServletResponse response, Authentication authentication) {

        // decode auth values
        UserAuthDecodedValues userAuthDecodedValues = this.authenticatedUserDecoder(authentication.getName());

        //List of UserAccountDTO
        List<UserDeepAccountDTO> userAccountDTOS = new LinkedList<>();
        UserAccounts userAccounts = this.userRepository.getAllUserAccounts(userAuthDecodedValues.getAccountID());


        /*
        *
        *         this.id = id;
        this.hold = hold;
        this.active = active;
        this.amount = amount;
        this.nextInterestOn = nextInterestOn;
        this.createdOn = createdOn;
        this.frozen = frozen;
        this.jointAccount = jointAccount;
        this.accountType = accountType;
        *
        * */

        userAccounts.getAllUserAccounts().forEach(account -> {
            userAccountDTOS.add(new UserDeepAccountDTO(
                    account.getId(),
                    account.isActive(),
                    account.isHold(),
                    account.getAmount(),
                    account.getNextInterestOn(),
                    account.getCreatedOn(),
                    account.isFrozen(),
                    account.isJointAccount(),
                    account.getAccountType().getAccountType()
            ));
        });

        return userAccountDTOS;
    }

    public TimeSpaceDTO getAccountSettingsByAccountType(String accountType, Authentication authentication) {

        TimeSpace timeSpace = this.userRepository.getAccountTypeByName(accountType);

        //data conversion
        //String accountType, int second, int min, int hour,
        // int days, int months, int years, double baseInterestRate,
        // boolean isAJointAccount, double minStartingAmount,
        // double withdrawalCountLimit, double moneyTransferLimit,
        // int baseLimit, int monthlyDraw, int dailyDraw, int hourlyDraw, int minutesDraw
        try{
            return new TimeSpaceDTO(
                    timeSpace.getAccountType(),
                    timeSpace.getSecond(),
                    timeSpace.getMin(),
                    timeSpace.getHour(),
                    timeSpace.getDays(),
                    timeSpace.getMonths(),
                    timeSpace.getYears(),
                    timeSpace.getBaseInterestRate(),
                    timeSpace.isAJointAccount(),
                    timeSpace.getMinStartingAmount(),
                    timeSpace.getWithdrawalCountLimit(),
                    timeSpace.getMoneyTransferLimit(),
                    timeSpace.getBaseLimit(),
                    timeSpace.getMonthlyDraw(),
                    timeSpace.getDailyDraw(),
                    timeSpace.getHourlyDraw(),
                    timeSpace.getMinutesDraw()
            );

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Collection<TransactionsDTO> getAllUserTransactions(Authentication authentication, HttpServletResponse httpServletResponse) {

       UserAuthDecodedValues userAuthDecodedValues = this.authenticatedUserDecoder(authentication.getName());

       final Collection<TransactionsDTO> transactionsDTOS = new LinkedList<>();
       //get all user transactions
        //first get userAccount which has a join to transactions
        UserAccounts userAccounts = this.userRepository.getAllUserAccounts(userAuthDecodedValues.accountID);

        //from accounts get transactions
        userAccounts.getTransactions().forEach(transactions -> {
            transactionsDTOS.add(new TransactionsDTO(
                    transactions.getId(),
                    transactions.getTransactionDescription(),
                    transactions.getToAccountNumber(),
                    transactions.getFromAccountNumber(),
                    transactions.getTransactionDate(),
                    transactions.isDeposited(),
                    transactions.getAmount(),
                    transactions.getRemainingAmount()
            ));
        });

        return transactionsDTOS;
    }

    //this class is used to access decoded values
    private class UserAuthDecodedValues {

        private String userName;

        private int userId;

        private int accountID;

        public UserAuthDecodedValues(String userName, int userId, int accountID) {
            this.userName = userName;
            this.userId = userId;
            this.accountID = accountID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAccountID() {
            return accountID;
        }

        public void setAccountID(int accountID) {
            this.accountID = accountID;
        }

        @Override
        public String toString() {
            return "UserAuthDecodedValues{" +
                    "userName='" + userName + '\'' +
                    ", userId=" + userId +
                    ", accountID=" + accountID +
                    '}';
        }
    }

    private UserAuthDecodedValues authenticatedUserDecoder(String auth) {
        // [0] -> username  [1] -> userId  [2] -> accountId

        String[] authDecode = auth.split(",");

        return new UserAuthDecodedValues(
                authDecode[0],
                Integer.parseInt(authDecode[1]),
                Integer.parseInt(authDecode[2])
        );
    }
}
