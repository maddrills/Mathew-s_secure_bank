package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.business.BusinessAccount;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.spouse.Spouse;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.spouse.SpouseAccount;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "joint_accounts")
final public class JointAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "joint_account_id")
    private int id;

    @Column(name = "active")
    private boolean active;


    // map by for SpouseAccount
    // joint account will have all the business accounts
    @OneToMany(
            mappedBy = "jointAccounts",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Collection<BusinessAccount> businessAccounts;

    // joint account will have only one spouse account
    @OneToOne(
            mappedBy = "jointAccountId",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private SpouseAccount spouseAccount;


    //map By for Spouse
    @OneToOne(mappedBy = "husbandJointAccount")
    @JoinColumn(name = "husband_joint_account")
    private Spouse spouseHusband;


    @OneToOne(mappedBy = "wifeJointAccount")
    @JoinColumn(name = "wife_joint_account")
    private Spouse spouseWife;




    public JointAccounts(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Spouse getSpouseWife() {
        return spouseWife;
    }

    public void setSpouseWife(Spouse spouseWife) {
        this.spouseWife = spouseWife;
    }

    public Spouse getSpouseHusband() {
        return spouseHusband;
    }

    public void setSpouseHusband(Spouse spouseHusband) {
        this.spouseHusband = spouseHusband;
    }

    public SpouseAccount getSpouseAccount() {
        return spouseAccount;
    }

    public void setSpouseAccount(SpouseAccount spouseAccount) {
        this.spouseAccount = spouseAccount;
    }

    public Collection<BusinessAccount> getBusinessAccounts() {
        return businessAccounts;
    }

    //convenience method for map by
    @Deprecated(since = "It is a map by use with caution")
    public void addAListOfBusinessAccounts(BusinessAccount businessAccount){
        if(this.businessAccounts == null){
            this.businessAccounts =  new HashSet<>();
        }
        this.businessAccounts.add(businessAccount);
    }
}
