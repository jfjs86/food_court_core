package com.pragma.foodcourt.core.domain.model;

public class Restaurant {

    private String restaurantName;
    private String restaurantIdentity;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantLogo;
    private User restaurantUser;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantIdentity() {
        return restaurantIdentity;
    }

    public void setRestaurantIdentity(String restaurantIdentity) {
        this.restaurantIdentity = restaurantIdentity;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantLogo() {
        return restaurantLogo;
    }

    public void setRestaurantLogo(String restaurantLogo) {
        this.restaurantLogo = restaurantLogo;
    }

    public User getRestaurantUser() {
        return restaurantUser;
    }

    public void setRestaurantUser(User restaurantUser) {
        this.restaurantUser = restaurantUser;
    }
}
