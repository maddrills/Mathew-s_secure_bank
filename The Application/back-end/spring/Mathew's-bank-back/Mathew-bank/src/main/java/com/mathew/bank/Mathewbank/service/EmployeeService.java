package com.mathew.bank.Mathewbank.service;

import com.mathew.bank.Mathewbank.DAO.EmployeeRepository;
import com.mathew.bank.Mathewbank.DTO.EmployeeDTO;
import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.DTO.UserApplicationDTO;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    //get all user applications
    public List<UserApplicationDTO> getAllUserApplications(HttpServletResponse response){

        final List<UserApplicationDTO> userApplicationDTOS = new LinkedList<>();

        List<UserApplication> userApplications;

        try{
            userApplications = this.employeeRepository.getAllUserApplications();
        }catch (Exception e){
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
                                userApplication.isRejected()
                        )
                )
        );

        return userApplicationDTOS;
    }

    public UserApplicationDTO getApplicationByIdNumber(int idNumber, HttpServletResponse response){

        if(idNumber < 0){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        UserApplication userApplication;

        try {
            userApplication = this.employeeRepository.getApplicationByIdNumber(idNumber);
        }catch (Exception e){
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
                userApplication.isRejected()
        );
    }



    //turns the status field to true and after that relays the application data to user table
    public boolean acceptAnApplicationNyId(int applicationNumber,int employeeId, HttpServletResponse servletResponse) {

        if(applicationNumber <= 0){
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        try{
            return this.employeeRepository.acceptUserApplication(applicationNumber,employeeId);
        }catch (Exception e){
            System.out.println(e);
            servletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
    }

    public boolean rejectApplication(int applicationNumber, int employeeId, HttpServletResponse servletResponse){

        if(applicationNumber <= 0){
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        try{
            this.employeeRepository.rejectUserApplication(applicationNumber, employeeId);
        }catch (Exception e){
            System.out.println(e);
            servletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
            return false;
        }
        return true;
    }

    public EmployeeDTO getEmployeeDetailsById(int nameOrID, HttpServletResponse response) {

        try{
            Employee employee = this.employeeRepository.getEmployeeById(nameOrID);

            // get the roles from employee
            final List<RolesDto> rolesDtos = new LinkedList<>();

            employee.getRoles().forEach( role -> rolesDtos.add(new RolesDto(role.getRole(),true)));

            return new EmployeeDTO(
                    employee.getId(),
                    employee.getDetails().getPhone_number(),
                    employee.getDetails().getFullName(),
                    employee.getDetails().getEmail(),
                    employee.getDetails().getDateOfBirth(),
                    employee.getDetails().getSalary(),
                    null,
                    rolesDtos);

        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e.toString());
            return null;
        }
    }
}
