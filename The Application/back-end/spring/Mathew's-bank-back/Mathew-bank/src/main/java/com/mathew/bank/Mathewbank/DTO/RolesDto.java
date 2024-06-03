package com.mathew.bank.Mathewbank.DTO;

public class RolesDto {

    private String roleName;

    private boolean added;

    public RolesDto() {
    }

    public RolesDto(String roleName, boolean added) {
        this.roleName = roleName;
        this.added = added;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    @Override
    public String toString() {
        return "RolesDto{" +
                "roleName='" + roleName + '\'' +
                ", added=" + added +
                '}';
    }
}
