package com.pragma.foodcourt.core.domain.model;

import java.util.Set;

public class User {

    private int userIdentityType;
    private String userIdentityNumber;
    private Set<Profile> userProfiles;

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

    public Set<Profile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<Profile> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
