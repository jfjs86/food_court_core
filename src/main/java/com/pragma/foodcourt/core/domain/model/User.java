package com.pragma.foodcourt.core.domain.model;

public class User {
    private int userIdentityType;
    private String userIdentityNumber;

    public int getUserIdentityType() {
        return userIdentityType;
    }

    public void setUserIdentityType(int userIdentityType) {
        this.userIdentityType = userIdentityType;
    }

    public String getUserIdentityNumber() {
        return userIdentityNumber;
    }

    public void setUserIdentityNumber(String userIdentityNumber) {
        this.userIdentityNumber = userIdentityNumber;
    }
}
