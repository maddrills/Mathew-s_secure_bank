package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Branch getABranchById(int branchId) {

        TypedQuery<Branch> query = this.entityManager.createQuery("SELECT B FROM Branch B WHERE B.id = :theId",Branch.class);

        query.setParameter("theId",branchId);

        return query.getSingleResult();
    }

    @Override
    public List<Branch> getAllBranchFromDB() {
        TypedQuery<Branch> query = this.entityManager.createQuery(
                "SELECT B FROM Branch AS B"
                , Branch.class);

        return query.getResultList();
    }


    /**
     * <h4>user application </h4>
     * checks if the branch is in the db else I will throw an exception<br>
     * no two applications can be the same when it comme to phone numbers <br>
     * <span style = "color : #D7E438">WARNING :</span><span style = "color : #97a027"> this method only has one check and it checks if branch is open</span>
     * */
    @Override
    @Transactional
    public void applyForBankAccount(final int BranchId, UserApplication userApplication) {

        Branch branch = getABranchById(BranchId);

        //check if branch is open before persisting
        if(!branch.isOpen()){
            throw new RuntimeException("Branch is closed");
        }

        System.out.println("Entering application persistence");

        System.out.println(getABranchById(BranchId).getBranchName());

        //add branch to application
        userApplication.setBranch(branch);

        this.entityManager.persist(userApplication);

    }

    @Override
    public List<Branch> getBranchesByCountryAndName(String country, String state) {

        //query checks for branches that have a county and state match
        TypedQuery<Branch> query = this.entityManager.createQuery(
                "SELECT b FROM Branch b WHERE b.country = :con AND b.state = :sta",
                Branch.class);

        query.setParameter("con", country);
        query.setParameter("sta", state);

        return query.getResultList();
    }

    @Override
    public Savings getSavingsAccountByNumber(int accountNumber) {
        return null;
    }

}
