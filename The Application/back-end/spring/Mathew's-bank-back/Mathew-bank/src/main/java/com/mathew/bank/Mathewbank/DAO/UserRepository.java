package com.mathew.bank.Mathewbank.DAO;

import com.mathew.bank.Mathewbank.DTO.RolesDto;
import com.mathew.bank.Mathewbank.entity.commonEntity.Role;
import com.mathew.bank.Mathewbank.entity.commonEntity.UserApplication;
import com.mathew.bank.Mathewbank.entity.employeeOnlyEntity.Branch;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Savings;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.User;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.users.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletResponse;
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
     * <span style = "color : #D7E438">WARNING :</span><span style = "color : #97a027"> this method only has two check and it checks if branch is open and if credentials are used by a bank user</span>
     * */

    //check phone number or email have bine used in user
    private boolean checkIfPhoneAndEmailExists(String phone, String email){

        //check if phone number is already used by user
        try{
            TypedQuery<UserDetails> query1 = this.entityManager.createQuery(
                    "SELECT ud.phoneNumber FROM UserApplication ud WHERE ud.phoneNumber = :phone",
                    UserDetails.class);

            query1.setParameter("phone",phone);

            if(query1.getSingleResult() == null){
                return false;
            }

        }catch (Exception e){
            System.out.println(e);
            return false;
        }

        //check if email is already used by user
        try{
            TypedQuery<UserDetails> query2 = this.entityManager.createQuery(
                    "SELECT ud.email FROM UserApplication ud WHERE ud.email = :email",
                    UserDetails.class);

            query2.setParameter("email",email);

            if(query2.getSingleResult() == null){
                return false;
            }

        }catch (Exception e){
            System.out.println(e);
            return false;
        }

        return true;
    }
    @Override
    @Transactional
    public void applyForBankAccount(final int BranchId, UserApplication userApplication) {

        System.out.println("Entering application");

        //sanity check for application
        if(checkIfPhoneAndEmailExists(userApplication.getPhoneNumber(), userApplication.getEmail())) throw new IllegalArgumentException();


        Branch branch = getABranchById(BranchId);

        //check if branch is open before persisting
        if(!branch.isOpen()){
            throw new RuntimeException("Branch is closed");
        }

        System.out.println("Entering application persistence");

        System.out.println(getABranchById(BranchId).getBranchName());

        System.out.println("Persisting time");
        //add branch to application
        userApplication.setBranch(branch);

        System.out.println(userApplication.getFullName());

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
    public UserApplication getUserApplicationDetailsByPhoneNumber(String phone) {

        //query checks for phone number match against application
        TypedQuery<UserApplication> query = this.entityManager.createQuery(
                "SELECT ua FROM UserApplication ua WHERE ua.phoneNumber = :phone",
                UserApplication.class);

        query.setParameter("phone",phone);

        return query.getSingleResult();
    }

    @Override
    public UserApplication getUserApplicationDetailsByEmail(String email) {
        //query checks for phone number match against application
        TypedQuery<UserApplication> query = this.entityManager.createQuery(
                "SELECT ua FROM UserApplication ua WHERE ua.email = :email",
                UserApplication.class);

        query.setParameter("email",email);

        return query.getSingleResult();
    }

    @Override
    public Savings getSavingsAccountByNumber(int accountNumber) {
        return null;
    }

}
