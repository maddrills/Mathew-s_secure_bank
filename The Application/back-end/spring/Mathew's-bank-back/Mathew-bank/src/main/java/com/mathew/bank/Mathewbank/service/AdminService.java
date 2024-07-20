package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmpRepo;
import com.mathew.bank.Mathewbank.DAO.UserRepo;
import com.mathew.bank.Mathewbank.DTO.BranchDTO;
import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.DTO.UserAndDetailsDTO;
import com.mathew.bank.Mathewbank.cachCountryState.CountryCache;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Account;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AdminService {

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CountryCache countryCache;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<EmployeeDTO> getEmployeesUnderAnEmployee(int employeeNumber, HttpServletResponse httpServletResponse){

        try{

            final List<Employee> employees = this.empRepo.getEmployeesUnderManager(employeeNumber);

            final List<EmployeeDTO> employeeDTOS = new LinkedList<>();

            employees.forEach(employee -> {

                //per user roles to rolesDto
                final Set<RolesDto> rolesDtos = new LinkedHashSet<>();
                employee.getRoles().forEach(role -> {
                    rolesDtos.add(new RolesDto(
                            role.getRole() ,
                            true
                    ));
                });


                employeeDTOS.add(new EmployeeDTO(
                        employee.getId(),
                        employee.getDetails().getPhone_number(),
                        employee.getDetails().getFullName(),
                        employee.getDetails().getEmail(),
                        employee.getDetails().getDateOfBirth(),
                        employee.getDetails().getSalary(),
                        null,
                        employee.getBankBranch() == null ? 0 : employee.getBankBranch().getId(),
                        rolesDtos,
                        employee.getManager() == null ? 0 : employee.getManager().getId(),
                        employee.getBankBranch() == null ? "No Branch" : employee.getBankBranch().getBranchName()
                ));
            });

            return employeeDTOS;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    private int accessLevelAuthCheck(Authentication authentication) {

        int accessLevel = 0;

        authentication.getAuthorities().forEach(System.out::println);

        // cycle through permissions and set an access level
        for (GrantedAuthority empRoles : authentication.getAuthorities()) {

            if (empRoles.toString().equals("ROLE_admin")) {
                accessLevel = 4;
            } else if (empRoles.toString().equals("ROLE_manager") && accessLevel != 4) {
                accessLevel = 3;
            }
        }
        return accessLevel;
    }

    //adds an employee with his or her credentials and at least one role
    public boolean addAnyEmployee(EmployeeDTO employeeDTO, HttpServletResponse response, Authentication authentication) {

        // sanity check
        if (employeeDTO == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        int accessLevel = accessLevelAuthCheck(authentication);
        //check the auth object
        System.out.println(accessLevel);
        if (accessLevel == 3) {
            // then only
            for (RolesDto role : employeeDTO.getRolesName()) {
                if (role.getRoleName().equals("admin") || role.getRoleName().equals("manager")) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            }
        }

        //a collection of unique roles
        Set<String> allowedRoles = new LinkedHashSet<>();

        boolean employeeRoleExists = false;

        //check if someone put admin in the request
        for (RolesDto role : employeeDTO.getRolesName()) {

            //forbid if someone added admin role
            if (role.getRoleName().equals("admin")) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }

            // check if employee role was added
            if (role.getRoleName().equals("employee")) {
                employeeRoleExists = true;
            }
        }

        //if employee is being added without employee role then add the role to it
        if (!employeeRoleExists) {
            employeeDTO.getRolesName().add(new RolesDto("employee", true));
        }

        for (RolesDto role : employeeDTO.getRolesName()) {
            allowedRoles.add(role.getRoleName());
        }

        if (this.addEmployeeAndDetails(
                employeeDTO.getPhone_number(),
                employeeDTO.getFull_name(),
                employeeDTO.getEmail(),
                employeeDTO.getDateOfBirth(),
                employeeDTO.getSalary(),
                employeeDTO.getPassword(),
                Integer.parseInt(authentication.getName()),
                allowedRoles
        ).equals("error")) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return true;
    }

    private String addEmployeeAndDetails(
            String phone_number, String full_name, String email, LocalDate dateOfBirth, double salary,
            String password, int adderId, Collection<String> rolesName
    ) {

        if (phone_number.isEmpty() || full_name.isEmpty() || email.isEmpty() || dateOfBirth == null || salary < 0 || password.isEmpty() || rolesName.isEmpty())
            return "error";

        if (!phone_number.matches("[0-9]+")) return "error";

        Employee employee = new Employee(
                this.passwordEncoder.encode(password),
                null,
                null,
                null
        );

        EmployeeDetails employeeDetails = new EmployeeDetails(
                phone_number,
                full_name,
                email,
                dateOfBirth,
                salary,
                null
        );

        employeeDetails.setEmployee(employee);

        // TODO catch individual exceptions
        try {
            empRepo.addAnEmployeeAndThereDetails(employeeDetails, adderId, rolesName);
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
        return "Added User";
    }


    public Role findARoleInDb(String role) {

        try {
            this.empRepo.findRoleByRoleName(role);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return this.empRepo.findRoleByRoleName(role);
    }


    public void makeAnAdminBankAccount(User user, String branch) {

        //TODO sanity check on user

        this.empRepo.createAUserInBank(user, branch);
    }

    public List<RolesDto> AddARoleToDb(List<RolesDto> rolesDto, HttpServletResponse response) {

        //holds the response depending on the Success of the persistence
        List<RolesDto> respRoles = new LinkedList<>();

        //sanity check
        if (rolesDto == null || rolesDto.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //an empty response
            respRoles.add(new RolesDto());
            return respRoles;
        }

        for (RolesDto role : rolesDto) {
            System.out.println(role);

            // return here because of illegal data in List
            if (role.getRoleName().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                //send back the empty role
                respRoles.add(role);
                return respRoles;
            }

            //add each role to db
            try {
                this.empRepo.addARole(new Role("ROLE_" + role.getRoleName()));
                role.setAdded(true);
                respRoles.add(role);
            } catch (DataIntegrityViolationException b) {
                System.out.println(b);
                role.setAdded(false);
                respRoles.add(role);
            }
        }
        //return a success list and response
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        return respRoles;
    }


    //sanity check
    private boolean SanityCheckForEmpPermission(int empId, List<RolesDto> roles) {

        if (roles == null) return false;

        //if there are 7 or more digits and its a positive number
        if (empId <= 0 || !this.employeeIdValidator(empId)) return false;

        for (var role : roles) {
            //deny the addition of admin role
            if (role.getRoleName().equals("admin") || role.getRoleName().isEmpty()) return false;
        }
        return true;
    }

    //change employee role
    public List<RolesDto> changeEmployeePermission(int empId, List<RolesDto> roles, HttpServletResponse response, Authentication authentication) {

        int authLevel = accessLevelAuthCheck(authentication);
        boolean isManager = false;

        if (authLevel == 3) {
            //then manager is adding a role
            //make sure he cant add manager as a role
            for (RolesDto rolesDto : roles) {
                if (rolesDto.getRoleName().equals("manager")) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return null;
                }
            }
            isManager = true;
        }
        // forbid any illegal
        if (!this.SanityCheckForEmpPermission(empId, roles)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        //holds the response depending on the Success of the persistence
        List<RolesDto> respRoles = new LinkedList<>();

        for (var role : roles) {
            try {
                this.empRepo.removeRoleFromEmployee(empId,isManager, role.getRoleName());
                role.setAdded(true);
                respRoles.add(role);

            } catch (Exception e) {
                role.setAdded(false);
                respRoles.add(role);
            }
        }
        return respRoles;
    }

    //UPDATE employee role
    public List<RolesDto> updateEmployeePermission(int empId, List<RolesDto> roles,  Authentication authentication, HttpServletResponse response) {

        System.out.println("Updating permissions 1");
        int authLevel = accessLevelAuthCheck(authentication);
        boolean isManager = false;

        if (authLevel == 3) {
            //then manager is adding a role
            //make sure he cant add manager as a role
            for (RolesDto rolesDto : roles) {
                if (rolesDto.getRoleName().equals("manager")) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return null;
                }
            }
            System.out.println("Updating permissions 2");
            isManager = true;
        }
        // forbid any illegal and Admin permission remove
        if (!this.SanityCheckForEmpPermission(empId, roles)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        try{
            System.out.println("Updating permissions 3");
            System.out.println(isManager);
            return this.empRepo.updateEmployeePermissions(empId,isManager, roles);
            //remove all permissions from employee

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<RolesDto> addAnEmployeeRole(int empId, List<RolesDto> roles, HttpServletResponse response, Authentication authentication) {

        int authLevel = accessLevelAuthCheck(authentication);

        if (authLevel == 3) {
            //then manager is adding a role
            //make sure he cant add manager as a role
            for (RolesDto rolesDto : roles) {
                if (rolesDto.getRoleName().equals("manager")) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return null;
                }
            }
        }

        // forbid any illegal
        if (!this.SanityCheckForEmpPermission(empId, roles)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        //holds the response depending on the Success of the persistence
        List<RolesDto> respRoles = new LinkedList<>();

        for (var role : roles) {
            try {
                this.empRepo.addARoleToAnEmployee(empId, role.getRoleName());
                role.setAdded(true);
                respRoles.add(role);

            } catch (Exception e) {
                //if permission already exists
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                role.setAdded(false);
                respRoles.add(role);
            }
        }
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        return respRoles;
    }


    private boolean employeeIdValidator(int employeeId) {
        int count = 0;
        //counts the decimal value
        while (employeeId != 0) {
            employeeId = employeeId / 10;
            count += 1;
        }
        //not a valid emp id range
        if (count < 7) return false;
        return true;
    }

    private boolean branchIdValidator(int branchId) {
        return branchId > 0;
    }


    //adds a manager to a bank can only be done by admin
    public boolean addAManagerToBranch(final int managerID, final int branchId, HttpServletResponse response) {

        //sanity check
        if (!this.employeeIdValidator(managerID) || managerID <= 0) {
            return false;
        }
        if (!this.branchIdValidator(branchId)) return false;

        //proceed with persisting
        try {
            this.empRepo.addManagerToBranch(managerID, branchId);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;

    }


    private boolean branchFieldsCheck(BranchDTO branchDTO) {
        return branchDTO.getBranchName() == null || branchDTO.getCountry() == null || branchDTO.getState() == null
                || branchDTO.getBranchName().isEmpty() || branchDTO.getCountry().isEmpty() || branchDTO.getState().isEmpty() || branchDTO.getBranchManagerId() < 0;
    }

    public boolean createABranchWithOrWithoutManager(BranchDTO branchDTO, HttpServletResponse response) {

        //standard sanity check
        if (branchDTO == null || branchFieldsCheck(branchDTO)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        if (branchDTO.getBranchManagerId() == 0) {
            //create a branch without a manager
            try {
                this.empRepo.createBranch(new Branch(
                        branchDTO.getBranchName(),
                        branchDTO.getState(),
                        branchDTO.getCountry(),
                        branchDTO.isOpen(),
                        null
                ));
            } catch (Exception e) {
                //if conflict occurs in db
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                return false;
            }
            //add country and state the cache
            this.countryCache.addACountryAndStateToCache(branchDTO.getCountry(), branchDTO.getState());
            return true;
        } else {
            if (!this.employeeIdValidator(branchDTO.getBranchManagerId())) return false;
            //create a branch with a manager included
            try {
                this.empRepo.createBranch(new Branch(
                        branchDTO.getBranchName(),
                        branchDTO.getState(),
                        branchDTO.getCountry(),
                        branchDTO.isOpen(),
                        this.empRepo.getEmployeeById(branchDTO.getBranchManagerId())
                ), branchDTO.getBranchManagerId());
            } catch (Exception e) {
                //if conflict occurs in db
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                System.out.println(e);
                return false;
            }
        }
        //add country and state the cache
        this.countryCache.addACountryAndStateToCache(branchDTO.getCountry(), branchDTO.getState());
        return true;
    }

    //get all employees from db with an option of sending back a user
    // with roles should return a list of employees
    public List<EmployeeDTO> allEmployeesOrByThereRole(String role) {
        //check if roles are given
        if (role != null) {
            //return a specific employee
            //create an DTO instance (necessary for lazy load)
            final List<EmployeeDTO> employeeDTOS = new LinkedList<>();
            empRepo.getAllUsersFromDB(role).forEach(
                    employee -> {

                        final Set<RolesDto> rolesDtos = new LinkedHashSet<>();

                        employee.getRoles().forEach(a -> rolesDtos.add(new RolesDto(a.getRole(), true)));

                        System.out.println( "Branch is ---"+employee.getBankBranch().getId());
                        employeeDTOS.add(new EmployeeDTO(
                                employee.getId(),
                                employee.getDetails().getPhone_number(),
                                employee.getDetails().getFullName(),
                                employee.getDetails().getEmail(),
                                employee.getDetails().getDateOfBirth(),
                                employee.getDetails().getSalary(),
                                null,
                                employee.getBankBranch() == null ? 0 : employee.getBankBranch().getId(),
                                rolesDtos,
                                employee.getManager() == null ? 0 : employee.getManager().getId(),
                                employee.getBankBranch() == null ? "No Branch" : employee.getBankBranch().getBranchName())


                        );
                    }
            );

            return employeeDTOS;
        }
        //return all  employees

        //create an DTO instance (necessary for lazy load)
        final List<EmployeeDTO> employeeDTOS = new LinkedList<>();
        empRepo.getAllUsersFromDB().forEach(
                employee -> {

                    final Set<RolesDto> rolesDtos = new LinkedHashSet<>();

                    employee.getRoles().forEach(a -> rolesDtos.add(new RolesDto(a.getRole(), true)));

                    employeeDTOS.add(new EmployeeDTO(
                            employee.getId(),
                            employee.getDetails().getPhone_number(),
                            employee.getDetails().getFullName(),
                            employee.getDetails().getEmail(),
                            employee.getDetails().getDateOfBirth(),
                            employee.getDetails().getSalary(),
                            null,
                            employee.getBankBranch() == null ? 0 : employee.getBankBranch().getId(),
                            rolesDtos,
                            employee.getManager() == null ? 0 : employee.getManager().getId(),
                            employee.getBankBranch() == null ? "No Branch" : employee.getBankBranch().getBranchName()));
                }
        );

        return employeeDTOS;
    }


    public List<BranchDTO> getAllBranches() {

        List<BranchDTO> branchDTOS = new LinkedList<>();

        try {
            final List<Branch> branches = this.empRepo.getAllBranchFromDB();

            branches.forEach(branch -> branchDTOS.add(
                    new BranchDTO(
                            branch.getId(),
                            branch.getBranchName(),
                            branch.getState(),
                            branch.getCountry(),
                            branch.isOpen(),
                            branch.getBranchManager() == null ? 0 : branch.getBranchManager().getId()
                    )
            ));

        } catch (Exception e) {
            return null;
        }
        return branchDTOS;

    }

    public List<UserAndDetailsDTO> getAllUsersAndThereDetails() {

        //using linked list because the number of elements returned is unpredictable
        final List<UserAndDetailsDTO> userAndDetailsDTOS = new LinkedList<>();

        //get a list of all employee details from db
//        this.empRepo.getAllUserAndThereInfo().forEach(
//                //for each employee detail add them to the DTO
//                userDetails -> userAndDetailsDTOS.add(
//                        new UserAndDetailsDTO(
//                                userDetails.getId(),
//                                userDetails.getFullName(),
//                                null,
//                                0,
//                                userDetails.getFullName(),
//                                userDetails.getPhoneNumber(),
//                                userDetails.getDateOfBerth(),
//                                userDetails.getAge(),
//                                userDetails.getEmail()
//                        )
//                )
//        );
        this.empRepo.getAllUserAndThereInfoVViaUser().forEach(
                //for each employee detail add them to the DTO
                user -> {
                    UserDetails userDetails = user.getUserDetails();

                    UserAndDetailsDTO userAndDetailsDTO = new UserAndDetailsDTO(
                            user.getId(),
                            user.getUserName(),
                            user.getUserAccountId().getId(),
                            user.getBranchId().getId(),
                            userDetails.getFullName(),
                            userDetails.getPhoneNumber(),
                            userDetails.getDateOfBerth(),
                            userDetails.getAge(),
                            userDetails.getEmail()
                    );

                    //add roles
                    final List<RolesDto> rolesDtos = new LinkedList<>();
                    user.getRoles().forEach(role -> rolesDtos.add(new RolesDto(role.getRole(), true)));
                    userAndDetailsDTO.setRolesDto(rolesDtos);

                    userAndDetailsDTOS.add(userAndDetailsDTO);
                }
        );

        return userAndDetailsDTOS;
    }

    //removes manager from bank 1) points all clerks to admin 2) only after manager is removed

    public boolean removeManagerFromBranch(int employeeId, Authentication authentication, HttpServletResponse httpServletResponse) {

        //sanity check
        if (employeeId <= 0) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        int adminId = Integer.parseInt(authentication.getName());

        // admin can not remove themselves from the bank
        if (adminId == employeeId) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        try {
            return this.empRepo.removeManagerFromBank(employeeId, Integer.parseInt(authentication.getName()));
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public boolean removeClerkFromAnyBank(int bankId, int clerkId, Authentication authentication, HttpServletResponse httpServletResponse) {

        //sanity check
        if (bankId <= 0 || clerkId <= 0) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        //check if only clerk
        if(this.highestAccessLevel(this.empRepo.getEmployeeById(clerkId)) < 3){
            //then no not clerk
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        };


        // check if clerk is bank manager
        Branch branch = this.empRepo.getABranchById(bankId);

        if(branch.getBranchManager().getId() == clerkId){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        try {
            return this.empRepo.removeClerkFromBranchAdminControl(bankId, clerkId, Integer.parseInt(authentication.getName()));
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e);
            return false;
        }

    }


    /**
     * BELLOW is admin setup
     */
    public void createTheAdminAccount() {

        Branch defaultBranch = new Branch(
                "Admin_Central",
                "Karnataka",
                "India",
                true,
                null
        );

        //password is 12345
        Employee employee = new Employee(
                this.passwordEncoder.encode("12345"),
                null,
                null,
                defaultBranch
        );

//        employee.setARole(new Role("ROLE_admin"));
//        employee.setARole(new Role("ROLE_manager"));
//        employee.setARole(new Role("ROLE_clerk"));
//        employee.setARole(new Role("ROLE_employee"));


        EmployeeDetails employeeDetails = new EmployeeDetails(
                "3343350332",
                "mathew francis",
                "mat@admin",
                LocalDate.of(1998, 7, 21),
                543234.50,
                null
        );

        defaultBranch.setBranchManager(employee);
        employeeDetails.setEmployee(employee);

        // TODO catch individual exceptions
        try {
            empRepo.addAnEmployeeAndThereDetails(employeeDetails, 0, new HashSet<>(Set.of("admin","manager","clerk","employee")));
        } catch (Exception e) {
            System.out.println(e);
        }

        Account savings = new Account(
                false,
                true,
                100_000_000.11,
                LocalDateTime.now().plusMonths(1),
                false,
                new TimeSpace(
                        "savings", 0, 0, 0, 0, 1, 0, 0.07,false,2000.00,
                        0,0,0,0,0,0,0,false
                ),
                LocalDateTime.now(),
                false
        );


        //add the savings account to admin account
        UserAccounts adminAccount = new UserAccounts(
                false
        );
        adminAccount.addAnAccountToUserAccounts(savings);
        savings.setUserAccounts(adminAccount);


        User adminBankAccount = new User(
                "Mathew Francis",
                this.passwordEncoder.encode("12345"),
                adminAccount,
                defaultBranch
        );

        adminBankAccount.setARole(new Role("ROLE_user"));

        UserDetails adminUserDetails = new UserDetails(
                "mathew francis",
                "3343350332",
                LocalDate.of(1998, 7, 21),
                30,
                "mat@admin",
                adminBankAccount
        );

        adminBankAccount.setUserDetails(adminUserDetails);

//        try{
//            this.userRepo.createAUserInBank();
//        }catch (Exception e){
//            System.out.println(e);
//        }

        try {
            this.makeAnAdminBankAccount(adminBankAccount, "Admin_Central");
        } catch (Exception e) {
            System.out.println(e);
        }

        //add back account to first employee ADMIN
        try {
            this.empRepo.addOeUpdateEmployeeBankAccount(1000001, 101000001);
        } catch (Exception e) {
            System.out.println("Last exe");
            System.out.println(e);
        }
    }
    /*ABOVE is admin setup*/


    //should put in another class for SOLID principle
    private int highestAccessLevel(Employee employee) {

        // 4 is the number of roles
        int accessLevel = 4;

        for (var level : employee.getRoles()) {
            System.out.println(level.getRole());
            if(level.getRole().equals("ROLE_admin")){
                if(accessLevel > 1){
                    accessLevel = 1;
                }
            }
            if(level.getRole().equals("ROLE_manager")){
                if(accessLevel > 2){
                    accessLevel = 2;
                }
            }
            if(level.getRole().equals("ROLE_clerk")){
                if(accessLevel > 3){
                    accessLevel = 3;
                }
            }
        }
        return accessLevel;
    }
}
