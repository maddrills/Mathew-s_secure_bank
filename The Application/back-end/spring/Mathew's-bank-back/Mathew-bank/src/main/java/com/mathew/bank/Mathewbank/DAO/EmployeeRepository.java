package com.mathew.bank.Mathewbank.DAO;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.Employee;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.employees.EmployeeDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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
    public void  addAnEmployeeAndThereDetails(EmployeeDetails employeeDetails, Collection<String> roleNames) {

        //first checks if role is available only then does it persist else it will throw an exception and not persist
        roleNames.forEach(role -> employeeDetails.getEmployee().setARole(this.findRoleByRoleName(role)));

        entityManager.persist(employeeDetails);
    }


    //add a role by admin only
    @Override
    public void addARole(Role role) {
        this.entityManager.persist(role);
    }

    @Override
    public Role findRoleByRoleName(String name) {

        TypedQuery<Role> query = this.entityManager.createQuery("SELECT R FROM Role as R WHERE R.role = :theId",Role.class);

        query.setParameter("theId","ROLE_"+name);

        return query.getSingleResult();
    }
}
