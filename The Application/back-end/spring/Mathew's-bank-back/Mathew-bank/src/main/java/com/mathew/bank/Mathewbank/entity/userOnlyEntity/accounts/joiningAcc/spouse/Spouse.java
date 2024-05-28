package com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.spouse;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.accounts.joiningAcc.JointAccounts;
import jakarta.persistence.*;

@Entity
@Table(name = "spouse")
final public class Spouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int id;

    // TODO link all by relationships
    @OneToOne
    @JoinColumn(name = "husband_joint_account")
    private JointAccounts husbandJointAccount;

    @OneToOne
    @JoinColumn(name = "wife_joint_account")
    private JointAccounts wifeJointAccount;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "sa_ac_no")
    private SpouseAccount spouseAccount;

    public Spouse(JointAccounts husbandJointAccount, JointAccounts wifeJointAccount, SpouseAccount spouseAccount) {
        this.husbandJointAccount = husbandJointAccount;
        this.wifeJointAccount = wifeJointAccount;
        this.spouseAccount = spouseAccount;
    }

    public int getId() {
        return id;
    }

    public JointAccounts getHusbandJointAccount() {
        return husbandJointAccount;
    }

    public void setHusbandJointAccount(JointAccounts husbandJointAccount) {
        this.husbandJointAccount = husbandJointAccount;
    }

    public JointAccounts getWifeJointAccount() {
        return wifeJointAccount;
    }

    public void setWifeJointAccount(JointAccounts wifeJointAccount) {
        this.wifeJointAccount = wifeJointAccount;
    }

    public SpouseAccount getSpouseAccount() {
        return spouseAccount;
    }

    public void setSpouseAccount(SpouseAccount spouseAccount) {
        this.spouseAccount = spouseAccount;
    }
}
