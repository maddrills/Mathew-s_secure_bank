package com.mathew.bank.Mathewbank.DTO;

import com.mathew.bank.Mathewbank.entity.userOnlyEntity.UserAccounts;

import java.time.LocalDate;
import java.util.Collection;

public class UserAndDetailsDTO implements AllowedLoginOutputGeneric{

    private int userId;

    private String userName;

    private UserAccounts userAccountId;

    private int branchId;

    private String fullName;

    private String phoneNumber;

    private LocalDate dateOfBerth;

    private int age;

    private String email;

    private UserAccountDTO userAccountDTO;

    private int userAccountsNumber;

    private Collection<RolesDto> rolesDto;

    public UserAndDetailsDTO(int userId, String userName, UserAccounts userAccountId, int branchId,
                             String fullName, String phoneNumber, LocalDate dateOfBerth, int age, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userAccountId = userAccountId;
        this.branchId = branchId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
    }

    public UserAndDetailsDTO(int userId, String userName, UserAccounts userAccountId, int branchId,
                             String fullName, String phoneNumber, LocalDate dateOfBerth, int age, String email, Collection<RolesDto> rolesDto, int userAccountsNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userAccountId = userAccountId;
        this.branchId = branchId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
        this.rolesDto = rolesDto;
        this.userAccountsNumber = userAccountsNumber;
    }



    public UserAndDetailsDTO(int userId, String userName, int userAccountNumber, int branchId,
                             String fullName, String phoneNumber, LocalDate dateOfBerth, int age, String email) {
        this.userId = userId;
        this.userName = userName;
        this.userAccountsNumber = userAccountNumber;
        this.branchId = branchId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
    }

    public UserAndDetailsDTO(int userId, String userName, UserAccounts userAccountId, int branchId,
                             String fullName, String phoneNumber, LocalDate dateOfBerth, int age, String email, UserAccountDTO userAccountDTO) {
        this.userId = userId;
        this.userName = userName;
        this.userAccountId = userAccountId;
        this.branchId = branchId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
        this.userAccountDTO = userAccountDTO;
    }

    public UserAndDetailsDTO(int userId, String userName, UserAccounts userAccountId, int branchId,
                             String fullName, String phoneNumber, LocalDate dateOfBerth,
                             int age, String email, UserAccountDTO userAccountDTO, Collection<RolesDto> rolesDto) {
        this.userId = userId;
        this.userName = userName;
        this.userAccountId = userAccountId;
        this.branchId = branchId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBerth = dateOfBerth;
        this.age = age;
        this.email = email;
        this.userAccountDTO = userAccountDTO;
        this.rolesDto = rolesDto;
    }

    public int getUserAccountsNumber() {
        return userAccountsNumber;
    }

    public void setUserAccountsNumber(int userAccountNumber) {
        this.userAccountsNumber = userAccountNumber;
    }

    public Collection<RolesDto> getRolesDto() {
        return rolesDto;
    }

    public void setRolesDto(Collection<RolesDto> rolesDto) {
        this.rolesDto = rolesDto;
    }

    public UserAccountDTO getUserAccountDTO() {
        return userAccountDTO;
    }

    public void setUserAccountDTO(UserAccountDTO userAccountDTO) {
        this.userAccountDTO = userAccountDTO;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserAccounts getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(UserAccounts userAccountId) {
        this.userAccountId = userAccountId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBerth() {
        return dateOfBerth;
    }

    public void setDateOfBerth(LocalDate dateOfBerth) {
        this.dateOfBerth = dateOfBerth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
