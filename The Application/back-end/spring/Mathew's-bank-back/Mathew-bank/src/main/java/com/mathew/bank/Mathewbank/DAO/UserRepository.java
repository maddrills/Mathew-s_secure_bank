package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements UserRepo{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findRoleByRoleName(String name) {
        return null;
    }

    @Override
    public Branch findBranchByName(String name) {

        TypedQuery<Branch> query = this.entityManager.createQuery("SELECT B FROM Branch B WHERE B.branchName = :name",Branch.class);

        query.setParameter("name",name);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void createAUserInBank(User user, String branchName) {

        //gets the branch the user will be added to
        Branch branch = this.findBranchByName(branchName);
        user.setBranchId(branch);
        this.entityManager.merge(user);
    }

    @Override
    public Savings getSavingsAccountByNumber(int accountNumber) {
        return null;
    }

}
