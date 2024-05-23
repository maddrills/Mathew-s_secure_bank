package com.mathew.bank.Mathewbank.DAO;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
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


}
