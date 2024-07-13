package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Account;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository implements EmpRepo {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean addedAnyEmployee(Employee employee) {
        try {
            this.entityManager.persist(employee);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }


    @Override
    @Transactional
    public void addAnEmployeeAndThereDetails(EmployeeDetails employeeDetails, int managerId, Collection<String> roleNames) {

        if (managerId < 0) {
            //error occurred
            throw new BadCredentialsException("Negative contain as input");
        }
        //first checks if role is available only then does it persist else it will throw an exception and not persist
        roleNames.forEach(role -> employeeDetails.getEmployee().setARole(this.findRoleByRoleName(role)));


        if (managerId != 0) {
            //get the reporting manager by id
            Employee manager = this.getEmployeeById(managerId);
            //with reporting manager

            employeeDetails.getEmployee().setManager(manager);
            //added repointing manager to employee to be added

            entityManager.persist(employeeDetails);
        }
        //without any reporting manager
        entityManager.persist(employeeDetails);
    }


    //add a role by admin only
    @Override
    @Transactional
    public void addARole(Role role) {
        this.entityManager.persist(role);
    }


    @Override
    public Role findRoleByRoleName(String name) {

        TypedQuery<Role> query = this.entityManager.createQuery("SELECT R FROM Role as R WHERE R.role = :theId", Role.class);

        query.setParameter("theId", "ROLE_" + name);

        return query.getSingleResult();
    }

    // TODO use this to find branch for admin
    @Override
    public Branch findBranchByName(String name) {

        TypedQuery<Branch> query = this.entityManager.createQuery("SELECT b FROM Branch AS b WHERE b.branchName = :branch", Branch.class);

        query.setParameter("branch", name);

        return query.getSingleResult();
    }

    @Override
    public Account getSavingsAccountByNumber(int accountNumber) {

        TypedQuery<Account> query = this.entityManager.createQuery("SELECT S FROM Savings AS S WHERE S.id = :accountNumber", Account.class);

        query.setParameter("accountNumber", accountNumber);

        Account account = query.getSingleResult();

        System.out.println(account.getAmount() - 50);

        return account;
    }

    //TODO IF ever required
    @Override
    public Branch getABranchById(int branchId) {

        return this.entityManager.find(Branch.class, branchId);
    }


    @Override
    @Transactional
    public void updateBankManager(Branch branch) {

        this.entityManager.merge(branch);

    }

    @Override
    @Transactional
    public void addOeUpdateEmployeeBankAccount(int employeeId, int bankAccountNumber) {

        //find the employeeDetails by employee Is
        TypedQuery<EmployeeDetails> query = this.entityManager.createQuery(
                "SELECT ED FROM EmployeeDetails AS ED WHERE ED.employee IN " +
                        "(SELECT E FROM Employee AS E WHERE E.id = :empId)",
                EmployeeDetails.class
        );

        query.setParameter("empId", employeeId);

        EmployeeDetails employeeDetails = query.getSingleResult();

        System.out.println(employeeDetails.getEmail());

        //find the corresponding account number made to add employees salary
        Account account = this.entityManager.find(Account.class, bankAccountNumber);

        employeeDetails.setSavings(account);
        //finally update it
        this.entityManager.merge(employeeDetails);
    }

    @Override
    public Employee getEmployeeById(final int empId) {

        return this.entityManager.find(Employee.class, empId);
    }

    public Employee getEmployeeAndRolesById(final int empId) {

        TypedQuery<Employee> employeeTypedQuery = this.entityManager.createQuery("SELECT e FROM Employee AS e JOIN FETCH e.roles WHERE e.id = :employeeId", Employee.class);

        employeeTypedQuery.setParameter("employeeId", empId);

        return employeeTypedQuery.getSingleResult();
    }

    private Branch getBranchById(final int branchId) {

        return this.entityManager.find(Branch.class, branchId);
    }

    @Override
    @Transactional
    public void removeRoleFromEmployee(int empId, String role) {
        //get the employee
        Employee employee = getEmployeeById(empId);
        //remove the object (Role) from the list of romes
        employee.getRoles().removeIf(a -> a.getRole().equals("ROLE_" + role));
        //persist the change
        this.entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void addARoleToAnEmployee(int empId, String role) {
        //get the employee
        Employee employee = getEmployeeById(empId);
        //find role in db
        Role dbRole = findRoleByRoleName(role);
        //remove the object (Role) from the list of romes
        employee.getRoles().add(dbRole);
        //persist the change
        this.entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void addManagerToBranch(int managerId, int bankId) {

        Employee employee = getEmployeeById(managerId);
        //check if employee is manager else throw an exception
        boolean isManager = false;
        for(var role : employee.getRoles()){
            if(role.getRole().equals("ROLE_manager")){
                isManager = true;
            }
        }
        if(!isManager){
            throw new RuntimeException("Not a manager please raise permissions for the user");
        }

        //get the employee and branch entity's
        Branch branch = getBranchById(bankId);
        //check if branch already has a manager
        if (!(branch.getBranchManager() == null)) {
            throw new RuntimeException("Batch already has a manager");
        }

        //find everyone under manager
        Collection<Employee> clerks = this.findAllEmployeesWhoAreNotManagersOrAdminsInBankBranch(branch.getId());
        if (clerks != null) {
            //assign manager to all sub employees
            clerks.forEach(unassignedClerk -> unassignedClerk.setManager(employee));
        }

        //point all applications in branch that point to admin to point to manager
        //get all applications that are assigned to admin
        Collection<UserApplication> userApplicationsAdmin = this.findAllApplicationsInBranchThatPointToAdmin(bankId);
        if(userApplicationsAdmin != null){
            userApplicationsAdmin.forEach(userApplication -> userApplication.setAssignedTo(employee));
        }

        //add branch to manager and manager to branch
        employee.setBankBranch(branch);
        branch.setBranchManager(employee);

        //finally persist
        this.entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void createBranch(Branch branch) {
        this.entityManager.persist(branch);
    }

//    TODO can be modeled better
    @Override
    @Transactional
    public void createBranch(Branch branch, int manager) {

        //find the employee In DB and has manager as role
        Employee employeeManager = this.getEmployeeById(manager);
        boolean isManager = false;
        for(var role : employeeManager.getRoles()){
            if (role.getRole().equals("ROLE_manager")) {
                isManager = true;
                break;
            }
        }
        if(!isManager){
            throw new RuntimeException("Not a manager please raise permissions for the user");
        }

        //update the branch field to have the respective manager
        branch.setBranchManager(employeeManager);
        //add the new branch to db
        this.entityManager.persist(branch);
        //now update the manager to have branch
        employeeManager.setBankBranch(branch);
        this.entityManager.merge(employeeManager);
    }

    @Override
    public List<Employee> getAllUsersFromDB() {

        TypedQuery<Employee> query = this.entityManager.createQuery("SELECT E FROM Employee AS E JOIN FETCH E.details", Employee.class);

        return query.getResultList();
    }

    @Override
    public List<Employee> getAllUsersFromDB(String roleName) {

        Role role = this.findRoleByRoleName(roleName);

        /*
         * IN tests is value of single valued path expression (persistent attribute of your entity)
         * in values you provided to query (or fetched via subquery).
         *
         * MEMBER OF tests is value you provided to query (or defined with expression) member of
         * values in some collection in your entity.*/

        TypedQuery<Employee> query = this.entityManager.createQuery(
                "SELECT E FROM Employee AS E " +
                        "JOIN FETCH E.details " +
                        "JOIN FETCH E.bankBranch" +
                        "WHERE :roleEntity MEMBER OF E.roles",
                Employee.class);

        query.setParameter("roleEntity", role);

        return query.getResultList();
    }


    //returns all the bank branches with their branch managers regardless if they exist or not
    @Override
    public List<Branch> getAllBranchFromDB() {

        TypedQuery<Branch> query = this.entityManager.createQuery(
                "SELECT B FROM Branch AS B"
                , Branch.class);

        return query.getResultList();
    }

    //admin can create a savings account for a user
    @Override
    public boolean createASavingsAccountForUser(int userId, int accountId, HttpServletResponse response) {
        return false;
    }

    @Override
    public User getUserDetailsByUserName(String userName) {
        return null;
    }

    @Override
    public List<UserDetails> getAllUserAndThereInfo() {

        TypedQuery<UserDetails> userDetailsTypedQuery = this.entityManager.createQuery(
                "SELECT ud FROM UserDetails AS ud",
                UserDetails.class);

        return userDetailsTypedQuery.getResultList();
    }

    @Override
    public List<User> getAllUserAndThereInfoVViaUser(){
        TypedQuery<User> userDetailsTypedQuery = this.entityManager.createQuery(
                "SELECT u FROM User AS u",
                User.class);

        return userDetailsTypedQuery.getResultList();
    }


    //get all user application
    @Override
    public List<UserApplication> getAllUserApplications() {

        TypedQuery<UserApplication> query = this.entityManager.createQuery(
                "SELECT ua FROM UserApplication AS ua"
                , UserApplication.class);

        return query.getResultList();

    }

    @Override
    @Transactional
    public void createAUserInBank(User user, String branchName) {

        //gets the branch the user will be added to
        Branch branch = this.findBranchByName(branchName);

        System.out.println(branch.getId());
        System.out.println("User id -> "+user.getId());

//        user.setBranchId(branch);
        this.entityManager.merge(user);
    }


    @Override
    public UserApplication getApplicationByIdNumber(int number) {

        return this.entityManager.find(UserApplication.class, number);
    }


    @Transactional
    private void persistUserDetails(UserDetails userDetails, int branchId) {

        System.out.println(branchId);
        Branch branch = this.getABranchById(branchId);

        System.out.println(branch.getBranchName());
        //set the branch for user
        userDetails.getUserId().setBranchId(branch);
        this.entityManager.persist(userDetails);
    }

    //check if not admin then make sure the application and the employee branch are the same
    //make sure the UserApplication entity is open at this point
    private boolean checkIfApplicationAndEmployeeAreOfTheSameBranchAdminException(UserApplication applicationNumber, int employeeId){

        Employee employee = this.getEmployeeById(employeeId);
        int applicationBranch = applicationNumber.getBranch().getId();
        //check what roles an employee has
        boolean isAdmin = false;
        boolean isManager = false;
        for(var roles : employee.getRoles()){
            //if admin
            if(roles.getRole().equals("ROLE_admin")){
                isAdmin = true;
            }
            //if manager
            if(roles.getRole().equals("ROLE_manager")){
                isManager = true;
            }
        }
        if(isAdmin) return true;

        // if not admin any approval must be done from a branch
        int employeeBranch = employee.getBankBranch().getId();
        if(applicationBranch == employeeBranch){
            //manager can approve regardless if application is or sent assigned to manager
            if(isManager) return true;
            //else check if the application is assigned to the employee
            return employee.getId() == applicationNumber.getAssignedTo().getId();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean acceptUserApplication(int applicationNumber, int employeeId) {

        UserApplication userApplication = this.entityManager.find(UserApplication.class, applicationNumber);

        System.out.println(userApplication.getFullName());

        //check if application has been rejected
        if(userApplication.isStatus() || userApplication.isRejected()) return false;

        //create a basic account
        UserAccounts basicAccount = new UserAccounts(
                false
        );

        //check if employee and application are of the same branch
        if(!checkIfApplicationAndEmployeeAreOfTheSameBranchAdminException(userApplication, employeeId)) return false;

        //create an employee and employee details a default username of full nname and password oof 12345 will be made
        User user = new User(
                //the default username is the first name + last name with no uppercases
                userApplication.getFullName().replaceAll("\\s", "").toLowerCase(),
                this.passwordEncoder.encode("12345"),
                basicAccount,
                null
        );

        //get user role
        Role roles = this.findRoleByRoleName("user");
        user.setARole(roles);

        //set user application -> user correlation
        userApplication.setCreatedUser(user);

        //add user to user details
        UserDetails userDetails = new UserDetails(
                userApplication.getFullName(),
                userApplication.getPhoneNumber(),
                userApplication.getDateOfBirth(),
                userApplication.getAge(),
                userApplication.getEmail(),
                user
        );

        try {
            this.persistUserDetails(userDetails, userApplication.getBranch().getId());
        } catch (Exception e) {
            System.out.println(e);
            //return false;
        }
        userApplication.setStatus(true);
        userApplication.setApprovedBy(getEmployeeById(employeeId));

        //corresponding
        this.entityManager.merge(userApplication);
        return true;
    }

    @Override
    @Transactional
    public boolean rejectUserApplication(int applicationNumber, int employeeId) {
        UserApplication userApplication = this.entityManager.find(UserApplication.class, applicationNumber);

        if(!checkIfApplicationAndEmployeeAreOfTheSameBranchAdminException(userApplication, employeeId)) return false;

        //check if application has already been approved or rejected
        if(userApplication.isRejected() || userApplication.isStatus()) return false;

        userApplication.setApprovedBy(getEmployeeById(employeeId));

        userApplication.setRejected(true);

        this.entityManager.merge(userApplication);

        return true;

    }


    @Override
    public Collection<Employee> getEmployeeUnderBranch(int branchId) {

        TypedQuery<Employee> query = this.entityManager.createQuery(
                "SELECT e FROM Employee e " +
                        "JOIN FETCH e.details " +
                        "JOIN FETCH e.bankBranch " +
                        "JOIN FETCH e.bankBranch eb " +
                        "WHERE eb.id = :branchId"
                , Employee.class);

        query.setParameter("branchId", branchId);

        return query.getResultList();
    }

    @Override
    @Transactional
    public boolean setClerkIntoBank(int managerID, int clerkId) {

        //getManager
        Employee manager = this.getEmployeeById(managerID);

        //get the back branch by manager
        if (manager.getBankBranch() == null) {
            throw new NullPointerException("Bank branch not assigned to manager");
        }
        Branch managerBranch = this.getBranchById(manager.getBankBranch().getId());


        //get the employee who has clerk in roles
        Employee clark = this.getEmployeeById(clerkId);

        boolean clerkAssignable = false;

        //makes sure only two romes are available clerk and employee
        if (clark.getRoles().size() == 2) {
            for (var role : clark.getRoles()) {
                if (role.getRole().equals("ROLE_clerk") || role.getRole().equals("ROLE_employee")) {
                    clerkAssignable = true;
                } else {
                    clerkAssignable = false;
                    break;
                }
            }
        } else {
            //not a clark
            throw new DataIntegrityViolationException("Cant add employee  with more than 2 permission to a branch as a clerk");
        }

        //if clerk is assignable provide with commitment
        if (clerkAssignable) {
            // check if clerk already in branch
            if (clark.getBankBranch() != null) {
                throw new DataIntegrityViolationException("Cant add an employee who is already in branch");
            }
            //update reporting manager
            clark.setManager(manager);
            // set the manager branch to clerk branch
            clark.setBankBranch(managerBranch);
            //commit
            this.entityManager.merge(clark);
            return true;

        } else {
            throw new DataIntegrityViolationException("Employee does not have the role clerk");
        }
    }

    //admin specific operation
    @Override
    @Transactional
    public boolean removeManagerFromBank(int managerId, int adminId) {

        //get manager and admin entity by id
        Employee manager = getEmployeeById(managerId);
        Employee admin = getEmployeeById(adminId);
        // find all clerks under branch
        Collection<Employee> employees = this.findAllEmployeesWhoAreNotManagersOrAdminsInBankBranch(manager.getBankBranch().getId());
        // point all clerks to point to manager
        employees.forEach(employee -> {
            employee.setManager(admin);
        });

        //point all applications in branch that point to admin to point to manager
        //get all applications that are assigned to admin
        Collection<UserApplication> userApplicationsAdmin = manager.getUserApplications();
        if(userApplicationsAdmin != null){
            userApplicationsAdmin.forEach(userApplication -> userApplication.setAssignedTo(admin));
        }


        //remove manager from bank
        manager.getBankBranch().setBranchManager(null);
        //finally remove bank from manager
        manager.setBankBranch(null);


        employees.forEach(employee -> {
            System.out.println("Employee without manager as cred " + employee.getId());
        });

        this.entityManager.merge(manager);

        return true;
    }

    @Override
    public List<Employee> findAllEmployeesWhoAreNotManagersOrAdminsInBankBranch(int bankId) {

        //find admin role
/*        List<Role> exclusionRoles = new ArrayList<>(
                List.of(this.findRoleByRoleName("admin"),this.findRoleByRoleName("manager"))
        );*/
        Role admin = this.findRoleByRoleName("admin");
        Role manager = this.findRoleByRoleName("manager");

        System.out.println("Branch id is " + bankId);

        // does not return employees with null as associated bank
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e from Employee AS e " +
                        "JOIN FETCH e.roles " +
                        "JOIN FETCH e.bankBranch AS b " +
                        "WHERE b.id = :branchId AND " +
                        ":roleAd NOT MEMBER OF e.roles AND " +
                        ":roleMan NOT MEMBER OF e.roles "
                , Employee.class);

        query.setParameter("roleAd", admin);
        query.setParameter("roleMan", manager);
        query.setParameter("branchId", bankId);

        return query.getResultList();
    }


    @Override
    public Branch getBranchByEmployeeId(int employeeBranch) {

        return null;
    }

    @Override
    @Transactional
    public boolean removeClerkFromBranchAdminControl(int bankId, int employeeId, int adminId) {

        //get employee entity
        Employee clerk = this.getEmployeeById(employeeId);
        Employee admin = this.getEmployeeById(adminId);

        //check if clerk has a bank association
        if (clerk.getBankBranch() == null) return false;
        if (!(clerk.getBankBranch().getId() == bankId)) return false;

        //manager to admin and bank to null
        clerk.setManager(admin);
        clerk.setBankBranch(null);

        //commit the changes
        this.entityManager.merge(clerk);
        return true;
    }

    @Override
    @Transactional
    public boolean removeSubEmployeeUnderManagerInBankByManager(int subEmpId, int ManagerId) {

        //get the subEmployee
        Employee suEmployee = this.getEmployeeById(subEmpId);
        Employee admin = this.findEmployeeThatHasRoleName("admin");
        //get manager entity
        Employee manager = this.getEmployeeById(ManagerId);
        //get manager and sunEmployee associated bank
        //if they are void return false
        if (suEmployee.getBankBranch() == null || manager.getBankBranch() == null) {
            return false;
        }
        //check if he subEmp under manager bank
        if (!(suEmployee.getBankBranch().equals(manager.getBankBranch()))) {
            //if not then return error
            return false;
        }
        //else
        //only then proceed with subEmp dereferencing
        suEmployee.setBankBranch(null);
        //change manager to admin
        suEmployee.setManager(admin);

        //only then commit
        this.entityManager.merge(suEmployee);
        return true;
    }

    @Override
    public Employee findEmployeeThatHasRoleName(String roleName) {

        //find role admin
        Role adminRole = this.findRoleByRoleName("admin");

        TypedQuery<Employee> query = this.entityManager.createQuery(
                "SELECT e FROM Employee AS e " +
                        "JOIN FETCH e.roles " +
                        "WHERE :adminRole MEMBER OF e.roles"
                , Employee.class);

        query.setParameter("adminRole", adminRole);

        return query.getSingleResult();
    }

    @Override
    public Collection<UserApplication> findAllApplicationsInBranchThatPointToAdmin(int bankId) {

        //get the admin entity
        Employee employee = getEmployeeById(1000001);
        //now find bank branch
        Branch branch = getABranchById(bankId);
        //now find all applications that correspond to branch and pont to employee
        TypedQuery<UserApplication> query = this.entityManager.createQuery(
                "SELECT ua FROM UserApplication AS ua WHERE ua.branch = :branch AND ua.assignedTo = :employee"
                ,UserApplication.class);

        query.setParameter("branch",branch);
        query.setParameter("employee",employee);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void commitEmployee(UserApplication userApplication) {
        this.entityManager.merge(userApplication);
    }

    @Override
    public List<Employee> getEmployeesUnderManager(int employeeNumber) {

        Employee managerEmployee = this.getEmployeeById(employeeNumber);

        TypedQuery<Employee> query = this.entityManager.createQuery(
                "SELECT e FROM Employee AS e WHERE e.manager = :manager"
                ,Employee.class);

        query.setParameter("manager",managerEmployee);

        return query.getResultList();
    }

    @Override
    public Collection<Role> getAllRoles() {

        TypedQuery<Role> query = this.entityManager.createQuery(
                "SELECT r FROM Role AS r",
                Role.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public boolean updateEmployee(Employee clerk) {

        try{
            this.entityManager.merge(clerk);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional
    public List<RolesDto> updateEmployeePermissions(int empId, List<RolesDto> roles) {

        //holds the response depending on the Success of the persistence
        List<RolesDto> respRoles = new LinkedList<>();
        Employee employee = this.getEmployeeById(empId);

        employee.getRoles().clear();

        //only after that add the new permissions
        for (var role : roles) {
            System.out.println(role);
            //fetch the role from db
            Role matchingRoleEntity = this.findRoleByRoleName(role.getRoleName());
            System.out.println(matchingRoleEntity.getRole());
            employee.setARole(matchingRoleEntity);
            role.setAdded(true);
            respRoles.add(role);
        }
        employee.getRoles().forEach(System.out::println);
        this.updateEmployee(employee);
        return respRoles;
    }


}
