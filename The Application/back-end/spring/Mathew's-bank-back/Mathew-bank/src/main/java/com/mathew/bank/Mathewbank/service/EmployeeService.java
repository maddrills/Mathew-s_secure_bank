package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmployeeRepository;
import com.mathew.bank.Mathewbank.DTO.*;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    //get all user applications
    public List<UserApplicationDTO> getAllUserApplications(HttpServletResponse response) {

        final List<UserApplicationDTO> userApplicationDTOS = new LinkedList<>();

        List<UserApplication> userApplications;

        try {
            userApplications = this.employeeRepository.getAllUserApplications();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        userApplications.forEach(
                userApplication -> userApplicationDTOS.add(
                        new UserApplicationDTO(
                                userApplication.getApplication_number(),
                                userApplication.getFullName(),
                                userApplication.getPhoneNumber(),
                                userApplication.getDateOfBirth(),
                                userApplication.getAge(),
                                userApplication.getEmail(),
                                userApplication.getAppliedOn(),
                                userApplication.isStatus(),
                                userApplication.isRejected(),
                                userApplication.getBranch().getId(),
                                userApplication.getAssignedTo().getId(),
                                userApplication.getApprovedBy() == null ? 0 :userApplication.getApprovedBy().getId(),
                                userApplication.getCreatedUser() == null ? 0 :userApplication.getCreatedUser().getId())
                )
        );

        return userApplicationDTOS;
    }

    public UserApplicationDTO getApplicationByIdNumber(int idNumber, HttpServletResponse response) {

        if (idNumber < 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        UserApplication userApplication;

        try {
            userApplication = this.employeeRepository.getApplicationByIdNumber(idNumber);
        } catch (Exception e) {
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        return new UserApplicationDTO(
                userApplication.getApplication_number(),
                userApplication.getFullName(),
                userApplication.getPhoneNumber(),
                userApplication.getDateOfBirth(),
                userApplication.getAge(),
                userApplication.getEmail(),
                userApplication.getAppliedOn(),
                userApplication.isStatus(),
                userApplication.isRejected(),
                userApplication.getBranch().getId(),
                userApplication.getAssignedTo().getId(),
                userApplication.getApprovedBy() == null ? 0 :userApplication.getApprovedBy().getId(),
                userApplication.getCreatedUser() == null ? 0 :userApplication.getCreatedUser().getId()

        );
    }


    //turns the status field to true and after that relays the application data to user table
    public boolean acceptAnApplicationNyId(int applicationNumber, int employeeId, HttpServletResponse servletResponse) {

        if (applicationNumber <= 0) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        try {
            return this.employeeRepository.acceptUserApplication(applicationNumber, employeeId);
        } catch (Exception e) {
            System.out.println(e);
            servletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
    }

    public boolean rejectApplication(int applicationNumber, int employeeId, HttpServletResponse servletResponse) {

        if (applicationNumber <= 0) {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        try {
            return this.employeeRepository.rejectUserApplication(applicationNumber, employeeId);
        } catch (Exception e) {
            System.out.println(e);
            servletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
    }

    public EmployeeDTO getEmployeeDetailsById(int nameOrID, HttpServletResponse response) {

        try {
            Employee employee = this.employeeRepository.getEmployeeById(nameOrID);

            // get the roles from employee
            final Set<RolesDto> rolesDtos = new LinkedHashSet<>();

            employee.getRoles().forEach(role -> rolesDtos.add(new RolesDto(role.getRole(), true)));

            return new EmployeeDTO(
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
            );

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e.toString());
            return null;
        }
    }

    public Collection<EmployeeDTO> getEmployeesUnderABranch(int branchNNumber, HttpServletResponse httpServletResponse) {
        //sanity check
        if (branchNNumber <= 0) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        //create a dto list
        final Collection<EmployeeDTO> employeesUnderBank = new LinkedList<>();

        try {
            Collection<Employee> employees = this.employeeRepository.getEmployeeUnderBranch(branchNNumber);

            //cycle through the employee list
            employees.forEach(employee -> {
                //assign each employee to the DTO list
                //get the employee details from employee
                EmployeeDetails employeeDetails = employee.getDetails();

                //get the roles from employee
                Set<RolesDto> empRoles = new LinkedHashSet<>();

                //translate employeeRoles to RolesDTO
                employee.getRoles().forEach(role -> empRoles.add(new RolesDto(role.getRole(), true)));

                employeesUnderBank.add(
                        new EmployeeDTO(
                                employee.getId(),
                                employeeDetails.getPhone_number(),
                                employeeDetails.getFullName(),
                                employeeDetails.getEmail(),
                                employeeDetails.getDateOfBirth(),
                                employeeDetails.getSalary(),
                                null,
                                employee.getBankBranch() == null ? 0 : employee.getBankBranch().getId(),
                                empRoles,
                                employee.getManager().getId(),
                                employee.getBankBranch() == null ? "No Branch" : employee.getBankBranch().getBranchName()
                        )
                );
            });

            return employeesUnderBank;

        } catch (Exception e) {
            return null;
        }
    }


    public boolean addClarkToManagerBranch(int employeeClerk,
                                           Authentication authentication,
                                           HttpServletResponse response) {

        int authIdManager = Integer.parseInt(authentication.getName());
        //check if manager and clerk have the same if yes then return error
        if (employeeClerk == authIdManager) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        try {
            return this.employeeRepository.setClerkIntoBank(authIdManager, employeeClerk);
        } catch (Exception e) {
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
    }

    public boolean addClarkToAnyBranch(int employeeClerk,
                                           int bankBranch,
                                           HttpServletResponse response) {
        //find employee
        Employee clerk = this.employeeRepository.getEmployeeById(employeeClerk);

        //check if employee already in branch
        if(clerk.getBankBranch() != null){
            //then can remove user
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        //now check permissions
        //if 3 ten clerk
        int level = highestAccessLevel(clerk);
        if( level < 3){
            System.out.println("Level is" + level);
            clerk.getRoles().forEach(System.out::println);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        //check if manager exists in branch
        //get branch
        Branch branch = this.employeeRepository.getABranchById(bankBranch);
        //check if branch is == null
        if(branch == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        if(branch.getBranchManager() != null){
            clerk.setManager(branch.getBranchManager());
            clerk.setBankBranch(branch);
            return this.employeeRepository.updateEmployee(clerk);
        }
        //point branch to corresponding branch with retained reports to
        clerk.setBankBranch(branch);
        return this.employeeRepository.updateEmployee(clerk);
    }

    public boolean removeSubEmployeeFromBankByManager(int employeeClerk, Authentication authentication, HttpServletResponse response) {

        if (employeeClerk <= 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        int manager = Integer.parseInt(authentication.getName());
        //check if manager is trying to remove himself
        if (manager == employeeClerk) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }


        try {
            return this.employeeRepository.removeSubEmployeeUnderManagerInBankByManager(employeeClerk, manager);
        } catch (Exception e) {
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

    }

    public boolean assignOrReassignUserApplication(int employeeNumber, int applicationNumber, Authentication authentication, HttpServletResponse httpServletResponse) {
        //Sub employee logic
        Employee assignee;
        Branch assigneeBranch;
        try {
            assignee = this.employeeRepository.getEmployeeById(employeeNumber);
            assigneeBranch = assignee.getBankBranch();
            if(assigneeBranch == null) return false;
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e);
            return false;
        }

        //directer logic
        Employee managerOrAdminEmployee = this.employeeRepository.getEmployeeById(Integer.parseInt(authentication.getName()));
        //get employee branch
        Branch managerOrAdminBranch = managerOrAdminEmployee.getBankBranch();
        if(managerOrAdminBranch == null){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        //only auth admin or manager will reach here
        int level = this.highestAccessLevel(managerOrAdminEmployee);

        UserApplication userApplication = this.employeeRepository.getApplicationByIdNumber(applicationNumber);
        //if manager
        if(level == 2){
            //only allow reassignment to branch employees provided in branch
            //check if manager and employee belong to the same branch
            System.out.println("access level-> " +level);
            if(!assigneeBranch.equals(managerOrAdminBranch)){
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                System.out.println("assigneeBranch.equals(managerOrAdminBranch) FALSE");
                return false;
            }
            //now check if userApplication belongs to manager or userApplication assigned to manager
            if(userApplication.getBranch().equals(managerOrAdminBranch) || userApplication.getAssignedTo().getId() == managerOrAdminBranch.getId()){
                //assign to sub employee
                userApplication.setAssignedTo(assignee);
                try{
                    this.employeeRepository.commitEmployee(userApplication);
                    return true;
                }catch (Exception e){
                    httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    System.out.println(e);
                    return false;
                }
            }
            //
            return false;
        }
        //then allow to inter branch reassignment
        System.out.println("access level-> " +level);
        //admin can reassign to anyone
        userApplication.setAssignedTo(assignee);
        try{
            this.employeeRepository.commitEmployee(userApplication);
            return true;
        }catch (Exception e){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e);
            return false;
        }
    }

    public Collection<RolesDto> getAllRoles() {

        Collection<Role> roles = this.employeeRepository.getAllRoles();

        Collection<RolesDto> rolesDtos = new ArrayList<>(roles.size());

        roles.forEach(role -> {
            //check if user roles exists
            if (!role.getRole().equals("ROLE_user")){
                rolesDtos.add(new RolesDto(role.getRole(), false));
            }
        });

        return rolesDtos;
    }

    public BranchDTO getBankAndManager(int bankId, HttpServletResponse httpServletResponse) {

        try{
            Branch branch = this.employeeRepository.getABranchById(bankId);

            return new BranchDTO(branch.getId(),branch.getBranchName(),branch.getState(),branch.getCountry(), branch.isOpen(),branch.getBranchManager() == null ? 0 : branch.getBranchManager().getId());

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<UserApplicationDTO> getAllApplicationsAssignedToMe(HttpServletResponse response, Authentication authentication) {
        try{

            //get user from db
            Employee employee  = this.employeeRepository.getEmployeeById(Integer.parseInt(authentication.getName()));

            List<UserApplicationDTO> userApplicationsDto = new ArrayList<>(employee.getUserApplications().size());

            employee.getUserApplications().forEach(userApplication -> {
                userApplicationsDto.add(new UserApplicationDTO(
                        userApplication.getApplication_number(),
                        userApplication.getFullName(),
                        userApplication.getPhoneNumber(),
                        userApplication.getDateOfBirth(),
                        userApplication.getAge(),
                        userApplication.getEmail(),
                        userApplication.getAppliedOn(),
                        userApplication.isStatus(),
                        userApplication.isRejected(),
                        userApplication.getBranch().getId(),
                        userApplication.getAssignedTo().getId(),
                        userApplication.getApprovedBy() == null ? 0 :userApplication.getApprovedBy().getId(),
                        userApplication.getCreatedUser() == null ? 0 :userApplication.getCreatedUser().getId()

                ));
            });
            //get all corresponding applications
            return userApplicationsDto;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<UserApplicationDTO> getAllApplicationsAssignedToAnEmployee(int employeeId, HttpServletResponse response, Authentication authentication) {

        if(employeeId <= 0){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

        try{
            //get Employee information
            Employee employee = this.employeeRepository.getEmployeeById(employeeId);
            final List<UserApplicationDTO> userAndDetailsDTO = new ArrayList<>(employee.getUserApplications().size());

            employee.getUserApplications().forEach(userApplication -> {
                userAndDetailsDTO.add(new UserApplicationDTO(
                        userApplication.getApplication_number(),
                        userApplication.getFullName(),
                        userApplication.getPhoneNumber(),
                        userApplication.getDateOfBirth(),
                        userApplication.getAge(),
                        userApplication.getEmail(),
                        userApplication.getAppliedOn(),
                        userApplication.isStatus(),
                        userApplication.isRejected(),
                        userApplication.getBranch().getId(),
                        userApplication.getAssignedTo().getId(),
                        userApplication.getApprovedBy() == null ? 0 :userApplication.getApprovedBy().getId(),
                        userApplication.getCreatedUser() == null ? 0 :userApplication.getCreatedUser().getId()

                ));
            });

            return userAndDetailsDTO;

        }catch (Exception e){
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            return null;
        }
    }


    public List<UserApplicationDTO> getAllApplicationsUnderBranch(int branchId, HttpServletResponse response) {
        try{
            //get the branch with branch name
            Branch branch = this.employeeRepository.getABranchById(branchId);

            if(branch == null){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }

            final List<UserApplication> allUserApplications = branch.getUserApplications();
            if(allUserApplications == null){
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return null;
            }

            final List<UserApplicationDTO> userApplicationsUnderBranchDTO = new ArrayList<>(allUserApplications.size());

            allUserApplications.forEach(userApplication -> {
                userApplicationsUnderBranchDTO.add(new UserApplicationDTO(
                        userApplication.getApplication_number(),
                        userApplication.getFullName(),
                        userApplication.getPhoneNumber(),
                        userApplication.getDateOfBirth(),
                        userApplication.getAge(),
                        userApplication.getEmail(),
                        userApplication.getAppliedOn(),
                        userApplication.isStatus(),
                        userApplication.isRejected(),
                        userApplication.getBranch().getId(),
                        userApplication.getAssignedTo().getId(),
                        userApplication.getApprovedBy() == null ? 0 :userApplication.getApprovedBy().getId(),
                        userApplication.getCreatedUser() == null ? 0 :userApplication.getCreatedUser().getId()
                ));
            });

            return userApplicationsUnderBranchDTO;

        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e);
            return null;
        }

    }


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
