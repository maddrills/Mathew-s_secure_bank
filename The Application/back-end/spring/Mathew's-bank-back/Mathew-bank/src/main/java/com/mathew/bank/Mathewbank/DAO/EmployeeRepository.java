package com.mathew.bank.Mathewbank.DAO;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository implements EmpRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean addedAnyEmployee(Employee employee) {

        try{
            this.entityManager.persist(employee);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void  addAnEmployeeAndThereDetails(Employee employee, EmployeeDetails employeeDetails) {
        entityManager.persist(employee);
        entityManager.persist(employeeDetails);
    }


    //add a role by admin only
    @Override
    public void addARole(Role role) {
        this.entityManager.persist(role);
    }
}
