package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmpRepo;
import com.mathew.bank.Mathewbank.DAO.UserRepo;
import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.TimeSpace;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public String addAnyEmployee(EmployeeDTO employeeDTO, HttpServletResponse response){

        //a collection of unique roles
        Set<String> allowedRoles = new LinkedHashSet<>();

        // sanity check
        if(employeeDTO == null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "error null entry";
        }

        //check if someone put admin in the request
        for(var role : employeeDTO.getRolesName()){
            if(!role.getRoleName().equals("admin")){
                allowedRoles.add(role.getRoleName());
            }else{
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return "ROLE Admin not allowed";
            }
        }

        if( this.addEmployeeAndDetails(
                employeeDTO.getPhone_number(),
                employeeDTO.getFull_name(),
                employeeDTO.getEmail(),
                employeeDTO.getDateOfBirth(),
                employeeDTO.getSalary(),
                employeeDTO.getPassword(),
                allowedRoles
        ).equals("error")){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Error";
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return "Success";
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
                salary,
                null
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


    public List<RolesDto> AddARoleToDb(List<RolesDto> rolesDto, HttpServletResponse response){

        //holds the response depending on the Success of the persistence
        List<RolesDto> respRoles = new LinkedList<>();

        //sanity check
        if(rolesDto == null || rolesDto.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //an empty response
            respRoles.add(new RolesDto());
            return respRoles;
        }

        for(RolesDto role : rolesDto){
            System.out.println(role);

            // return here because of illegal data in List
            if(role.getRoleName().isEmpty()){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                //send back the empty role
                respRoles.add(role);
                return respRoles;
            }

            //add each role to db
            try{
                this.empRepo.addARole(new Role("ROLE_"+role.getRoleName()));
                role.setAdded(true);
                respRoles.add(role);
            }
            catch (DataIntegrityViolationException b){
                System.out.println(b);
                role.setAdded(false);
                respRoles.add(role);
                }
        }
        //return a success list and response
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        return respRoles;
    }


    //change employee role
    public boolean changeEmployeePermission(int empId, List<RolesDto> roles,HttpServletResponse response){

        for(var role : roles){
            try{
                this.empRepo.removeRoleFromEmployee(empId, role.getRoleName());

            }catch (Exception e){
                return false;
            }
        }
        return true;
    }

















    /**
     * BELLOW is admin setup*/
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
                543234.50,
                null
        );

        defaultBranch.setBranchManager(employee);
        employeeDetails.setEmployee(employee);

        // TODO catch individual exceptions
        try{
            empRepo.addAnEmployeeAndThereDetails(employeeDetails, new HashSet<>(Set.of("admin")));
        }catch (Exception e){
            System.out.println(e);
        }

        Savings savings = new Savings(
                false,
                true,
                1000000000.11,
                LocalDateTime.now().plusMonths(1),
                false,
                new TimeSpace("savings",0,0,0,0,1,0,0.07),
                LocalDateTime.now()
        );


        UserAccounts AdminAccount = new UserAccounts(
                savings,
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

        //add back account to first employee ADMIN
        try{
            this.empRepo.addOeUpdateEmployeeBankAccount(1000001,102000001);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    /*ABOVE is admin setup*/
}
