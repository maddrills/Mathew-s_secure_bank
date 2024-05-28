package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.business;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "business_people")
public class BusinessPeople {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id")
    private int id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "user_joint_account")
    private Collection<JointAccounts> userJointAccount;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "business_ac_no")
    private BusinessAccount businessAccountNumbers;

    public int getId() {
        return id;
    }

    public BusinessAccount getBusinessAccountNumbers() {
        return businessAccountNumbers;
    }

    public void setBusinessAccountNumbers(BusinessAccount businessAccountNumbers) {
        this.businessAccountNumbers = businessAccountNumbers;
    }

    public Collection<JointAccounts> getUserJointAccount() {
        return userJointAccount;
    }


    //convenience method to add many joint accounts
    public void addUsesJointAccounts(JointAccounts jointAccounts){

        if(this.userJointAccount == null){
            this.userJointAccount = new HashSet<>();
        }
        this.userJointAccount.add(jointAccounts);
    }
}
