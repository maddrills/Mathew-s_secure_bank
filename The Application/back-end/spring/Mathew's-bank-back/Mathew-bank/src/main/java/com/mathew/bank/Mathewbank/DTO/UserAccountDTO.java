package com.mathew.bank.Mathewbank.DTO;

import java.util.Collection;

public class UserAccountDTO {

    private int userAccountID;

    private Collection<UserDeepAccountDTO> allUserAccounts;

    private boolean frozen;


    public UserAccountDTO(int userAccountID, Collection<UserDeepAccountDTO> allUserAccounts, boolean frozen) {
        this.userAccountID = userAccountID;
        this.allUserAccounts = allUserAccounts;
        this.frozen = frozen;
    }


    public Collection<UserDeepAccountDTO> getAllUserAccounts() {
        return allUserAccounts;
    }

    public void setAllUserAccounts(Collection<UserDeepAccountDTO> allUserAccounts) {
        this.allUserAccounts = allUserAccounts;
    }

    public int getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}
