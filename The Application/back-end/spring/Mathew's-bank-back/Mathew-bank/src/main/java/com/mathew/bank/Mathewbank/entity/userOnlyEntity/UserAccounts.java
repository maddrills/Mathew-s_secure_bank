package com.mathew.bank.Mathewbank.entity.userOnlyEntity;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.BuildUp;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Checking;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.Account;
import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.LinkedList;

@Entity
@Table(name = "user_accounts")
final public class UserAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_acc_id")
    private int id;

    @OneToMany(mappedBy = "userAccounts", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<Account> account;

    @OneToMany(mappedBy = "userAccounts", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<Transactions> transactions;

    @Column(name = "frozen")
    private boolean frozen;


    public UserAccounts() {
    }

    public UserAccounts(Collection<Account> account, boolean frozen) {
        this.account = account;
        this.frozen = frozen;
    }

    //basic account constructor
    public UserAccounts(boolean frozen) {
        this(null, frozen);
    }

    public int getId() {
        return id;
    }

    //convenience method
    public Collection<Account> getAllUserAccounts(){
        return this.account;
    }

    public void addAnAccountToUserAccounts(Account account){
        if(this.account == null){
            this.account = new LinkedList<>();
        }
        this.account.add(account);
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public Collection<Transactions> getTransactions() {
        return transactions;
    }

    public void setATransaction(Transactions transaction) {
        if(this.transactions == null){
            this.transactions = new LinkedList<>();
        }
        this.transactions.add(transaction);
    }
}
