package com.pragma.foodcourt.core.domain.model;

import java.math.BigDecimal;

public class Dish {

    private String dishName;
    private BigDecimal dishPrice;
    private String dishDescription;
    private String urlImage;
    private int dishCategory;
    private Restaurant dishRestaurant;
    private boolean isActive;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public BigDecimal getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(BigDecimal dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(int dishCategory) {
        this.dishCategory = dishCategory;
    }

    public Restaurant getDishRestaurant() {
        return dishRestaurant;
    }

    public void setDishRestaurant(Restaurant dishRestaurant) {
        this.dishRestaurant = dishRestaurant;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
