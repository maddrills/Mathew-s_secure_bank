package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.Transactions;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Account;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserRepository implements UserRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findRoleByRoleName(String name) {
        return null;
    }

    @Override
    public Branch findBranchByName(String name) {

        TypedQuery<Branch> query = this.entityManager.createQuery("SELECT B FROM Branch B WHERE B.branchName = :name", Branch.class);

        query.setParameter("name", name);

        return query.getSingleResult();
    }

    @Override
    public Branch getABranchById(int branchId) {

        TypedQuery<Branch> query = this.entityManager.createQuery("SELECT B FROM Branch B WHERE B.id = :theId", Branch.class);

        query.setParameter("theId", branchId);

        return query.getSingleResult();
    }

    @Override
    public List<Branch> getAllBranchFromDB() {
        TypedQuery<Branch> query = this.entityManager.createQuery(
                "SELECT B FROM Branch AS B"
                , Branch.class);

        return query.getResultList();
    }

    //crete a savings account in user
    @Override
    @Transactional
    public boolean createASavingsAccountForUser(int userId, int accountId, HttpServletResponse response) {
        //first check if user already has a savings account using there accountId
        UserAccounts userAccounts = this.entityManager.find(UserAccounts.class, accountId);
        if (userAccounts.getAllUserAccounts() != null) {
            //if yes then return false
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        //by default you will have 2000 rs
        //else create a new savings account for user and update the users account to have the savings account

        //get the time function from db
        TimeSpace timeSpace = this.entityManager.find(TimeSpace.class, "savings");
        LocalDateTime interestOn = LocalDateTime.now()
                .plusYears(timeSpace.getYears())
                .plusMonths(timeSpace.getMonths())
                .plusDays(timeSpace.getDays())
                .plusHours(timeSpace.getHour())
                .plusSeconds(timeSpace.getSecond());

        //create the account
        Account savings = new Account(false, true, 2000.00, interestOn, false, timeSpace, LocalDateTime.now(),false);

        userAccounts.addAnAccountToUserAccounts(savings);

        this.entityManager.persist(userAccounts);

        return true;
    }


    @Override
    public User getUserDetailsByUserName(String userName) {

        // find user by user name
        TypedQuery<User> query = this.entityManager.createQuery(
                "SELECT u FROM User AS u JOIN FETCH " +
                        " u.userDetails JOIN FETCH u.userAccountId " +
                        "JOIN FETCH u.roles " +
                        "WHERE u.userName = :name",
                User.class);

        query.setParameter("name", userName);

        return query.getSingleResult();
    }

    @Override
    public Employee getEmployeeById(int empId) {

        return this.entityManager.find(Employee.class, empId);
    }


    /**
     * <h4>user application </h4>
     * checks if the branch is in the db else I will throw an exception<br>
     * no two applications can be the same when it comme to phone numbers <br>
     * <span style = "color : #D7E438">WARNING :</span><span style = "color : #97a027"> this method only has two check and it checks if branch is open and if credentials are used by a bank user</span>
     */

    //check phone number or email have bine used in user
    private boolean checkIfPhoneAndEmailExists(String phone, String email) {

        //check if phone number is already used by user
        try {
            TypedQuery<UserDetails> query1 = this.entityManager.createQuery(
                    "SELECT ud.phoneNumber FROM UserApplication ud WHERE ud.phoneNumber = :phone",
                    UserDetails.class);

            query1.setParameter("phone", phone);

            if (query1.getSingleResult() == null) {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        //check if email is already used by user
        try {
            TypedQuery<UserDetails> query2 = this.entityManager.createQuery(
                    "SELECT ud.email FROM UserApplication ud WHERE ud.email = :email",
                    UserDetails.class);

            query2.setParameter("email", email);

            if (query2.getSingleResult() == null) {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public void applyForBankAccount(final int BranchId, UserApplication userApplication) {

        System.out.println("Entering application");

        //sanity check for application
        if (checkIfPhoneAndEmailExists(userApplication.getPhoneNumber(), userApplication.getEmail()))
            throw new IllegalArgumentException();


        Branch branch = getABranchById(BranchId);

        //check if branch is open before persisting
        if (!branch.isOpen()) {
            throw new RuntimeException("Branch is closed");
        }

        System.out.println("Entering application persistence");

        System.out.println(getABranchById(BranchId).getBranchName());

        System.out.println("Persisting time");
        //add branch to application
        userApplication.setBranch(branch);

        System.out.println(userApplication.getFullName());

        this.entityManager.persist(userApplication);

    }


    @Override
    public List<Branch> getBranchesByCountryAndName(String country, String state) {

        //query checks for branches that have a county and state match
        TypedQuery<Branch> query = this.entityManager.createQuery(
                "SELECT b FROM Branch b WHERE b.country = :con AND b.state = :sta",
                Branch.class);

        query.setParameter("con", country);
        query.setParameter("sta", state);

        return query.getResultList();
    }

    @Override
    public UserApplication getUserApplicationDetailsByPhoneNumber(String phone) {

        //query checks for phone number match against application
        TypedQuery<UserApplication> query = this.entityManager.createQuery(
                "SELECT ua FROM UserApplication ua WHERE ua.phoneNumber = :phone",
                UserApplication.class);

        query.setParameter("phone", phone);

        return query.getSingleResult();
    }

    @Override
    public UserApplication getUserApplicationDetailsByEmail(String email) {
        //query checks for phone number match against application
        TypedQuery<UserApplication> query = this.entityManager.createQuery(
                "SELECT ua FROM UserApplication ua WHERE ua.email = :email",
                UserApplication.class);

        query.setParameter("email", email);

        return query.getSingleResult();
    }

    //gets user information that hold users account
    @Override
    public User getUserFromDb(int usedId) {
        return this.entityManager.find(User.class, usedId);
    }

    @Override
    @Transactional
    public boolean transferMoneyFromUserAccountToAnother(int accountNumberFrom, int accountNumberTo,
                                                         double amount, int userId, int accountID) {

        //check if fromm account number is the same as auth user's account
        //first get from user object
        User fromUser = this.getUserFromDb(userId);

        //User Account entity
        Account fromAccountEntity;
        Account toAccountEntity;

        //To user Accounts
        Transactions userTransactionsFromAccount;
        Transactions userTransactionsToAccountEntity;

        //check if from account belongs to user
        boolean isUserFromAccount = false;
        //also check if to account belongs to user
        boolean isUserToAccount = false;
        for(var accounts : fromUser.getUserAccountId().getAllUserAccounts()){
            if(accounts.getId() == accountNumberFrom){
                isUserFromAccount = true;
            }
            if(accounts.getId() == accountNumberTo){
                //user transferring between users own accounts
                toAccountEntity = accounts;
                isUserToAccount = true;
            }
        }

        // if user not using his own account
        if(!isUserFromAccount){
            return false;
        }


        //get to user account
        fromAccountEntity = this.findAccountById(accountNumberFrom);
        toAccountEntity = this.findAccountById(accountNumberTo);
        //first check if money can be debited
        double remainingAmount = fromAccountEntity.getAmount() - amount;
        if(remainingAmount < 0.00){
            return false;
        }

        System.out.println(amount);
        System.out.println(remainingAmount);
        System.out.println(fromAccountEntity.getUserAccounts().getId());
        // debit from user
        fromAccountEntity.setAmount(remainingAmount);
        userTransactionsFromAccount = new Transactions(
                fromAccountEntity.getAccountType().getAccountType()+" Account Money going out",
                accountNumberTo,
                accountNumberFrom,
                LocalDateTime.now(),
                false,
                amount,
                fromAccountEntity.getUserAccounts(),
                fromAccountEntity.getAccountType(),
                remainingAmount);
        fromAccountEntity.setLastWithdrawalDate(LocalDateTime.now());
        fromAccountEntity.setWithdrawalCount(fromAccountEntity.getWithdrawalCount() + 1);
        //TODO
        // if account is has a periodic_withdrawal_count then process accordingly
        if(fromAccountEntity.getAccountType().isPeriodic()){
            //process accordingly
        }
        fromAccountEntity.getUserAccounts().setATransaction(userTransactionsFromAccount);

        //credit to user
        double creditedAmount = toAccountEntity.getAmount() + amount;
        toAccountEntity.setAmount(toAccountEntity.getAmount() + amount);
        userTransactionsToAccountEntity = new Transactions(
                fromAccountEntity.getAccountType().getAccountType()+" Account Credited",
                accountNumberFrom,
                accountNumberTo,
                LocalDateTime.now(),
                true,
                amount,
                toAccountEntity.getUserAccounts(),
                fromAccountEntity.getAccountType(),
                creditedAmount);
        toAccountEntity.getUserAccounts().setATransaction(userTransactionsToAccountEntity);


        // if destination account is part of Users from account then user is transferring between accounts
        if(isUserToAccount){
            userTransactionsFromAccount.setTransactionDescription(fromAccountEntity.getAccountType().getAccountType()+" from Between accounts");
            userTransactionsToAccountEntity.setTransactionDescription(toAccountEntity.getAccountType().getAccountType()+" to Between accounts");
        }

        //commit both transactions
        this.entityManager.merge(fromAccountEntity);
        this.entityManager.merge(toAccountEntity);

        return true;
    }

    @Override
    @Transactional
    public boolean addBankAccountToUser(String accountName, double initialAmount, int userId, int accountID, String userName) {

        //get savings account by account name
        TimeSpace accountType = this.getAccountTypeByName(accountName);

        //check if initial amount is grater or equal to min selected amount
        System.out.println(accountType.getAccountType());
        if(initialAmount < accountType.getMinStartingAmount()){
            return false;
        }

        //find user by id
        User user = this.getUserFromDb(userId);
        UserAccounts userAccounts = user.getUserAccountId();

        //create user account
        Account savings = new Account(
                false,
                true,
                initialAmount,
                LocalDateTime.now().plusYears(accountType.getYears()).
                        plusMonths(accountType.getMonths()).
                        plusDays(accountType.getDays()).
                        plusHours(accountType.getHour()).
                        plusMinutes(accountType.getMin()).
                        plusSeconds(accountType.getSecond()),
                false,
                accountType
                ,
                LocalDateTime.now(),
                accountType.isAJointAccount()
        );

        //bind account to user
        savings.setUserAccounts(userAccounts);

        this.entityManager.persist(savings);

        return true;
    }

    @Override
    public TimeSpace getAccountTypeByName(String name) {

        return this.entityManager.find(TimeSpace.class, name);
    }

    @Override
    public UserAccounts getAllUserAccounts(int accountID) {
        return this.entityManager.find(UserAccounts.class, accountID);
    }

    @Override
    public Account findAccountById(int accountNumberTo) {
        return this.entityManager.find(Account.class, accountNumberTo);
    }

    @Override
    public Account getSavingsAccountByNumber(int accountNumber) {
        return null;
    }

}
