package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmpRepo;
import com.mathew.bank.Mathewbank.DAO.UserRepo;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private UserRepo userRepo;


    public boolean addAnyEmployee(Employee employee){

        // sanity check
        if(employee == null) return false;

        return this.empRepo.addedAnyEmployee(employee);
    }

    public String addEmployeeAndDetails(
            String phone_number, String full_name, String email, LocalDate dateOfBirth, double salary,
            String password, Collection<String> rolesName
    ){
        //TODO a sanity check to make sure that user enters intended credentials

        Employee employee = new Employee(
                password,
                null,
                null,
                null
        );

        EmployeeDetails employeeDetails = new EmployeeDetails(
                phone_number,
                full_name,
                email,
                dateOfBirth,
                salary
        );

        employeeDetails.setEmployee(employee);

        // TODO catch individual exceptions
        try{
            empRepo.addAnEmployeeAndThereDetails(employeeDetails, rolesName);
        }catch (Exception e){
            System.out.println(e);
            return "error";
        }
        return "Added User";
    }

    


    public Role findARoleInDb(String role){

        try{
            this.empRepo.findRoleByRoleName(role);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }

        return this.empRepo.findRoleByRoleName(role);
    }


    public void makeAnAdminBankAccount(User user,String branch){

        //TODO sanity check on user

        this.userRepo.createAUserInBank(user,branch);
    }

    public void createTheAdminAccount(){

        Branch defaultBranch = new Branch(
                "Admin_Central",
                "Karnataka",
                "India",
                true,
                null
        );

        Employee employee = new Employee(
                "12345",
                null,
                null,
                defaultBranch
        );


        EmployeeDetails employeeDetails = new EmployeeDetails(
                "3343350332",
                "mathew francis",
                "mat@admin",
                LocalDate.of(1998, 7, 21),
                543234.50
        );

        defaultBranch.setBranchManager(employee);
        employeeDetails.setEmployee(employee);

        // TODO catch individual exceptions
        try{
            empRepo.addAnEmployeeAndThereDetails(employeeDetails, new HashSet<>(Set.of("admin")));
        }catch (Exception e){
            System.out.println(e);
        }

        UserAccounts AdminAccount = new UserAccounts(
                null,
                null,
                null,
                null,
                false
        );


        User adminBankAccount = new User(
                "Mathew Francis",
                "12345",
                AdminAccount,
                null
        );

        try{
            this.makeAnAdminBankAccount(adminBankAccount, "Admin_Central");
        }catch (Exception e){
            System.out.println(e);
        }

        //update central bank as admin
//        defaultBranch.setBranchManager(employee);
//        try{
//            this.empRepo.updateBankManager(defaultBranch);
//        }catch (Exception e){
//            System.out.println(e);
//        }

        // update user to have central bank in user

    }
}
