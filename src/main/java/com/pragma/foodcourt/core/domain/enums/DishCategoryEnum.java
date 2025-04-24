package com.pragma.foodcourt.core.domain.enums;

public enum DishCategoryEnum {

    ENTRADAS(1,"Entradas"),
    PLATO_FUERTE(2,"Plato fuerte"),
    BEBIDAS(3,"Bebidas"),
    POSTRES(4,"Postres");

    private int id;
    private String name;

    DishCategoryEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static DishCategoryEnum fromId(int id){
        for (DishCategoryEnum dishCategoryEnum : DishCategoryEnum.values()){
            if(dishCategoryEnum.id == id){
                return dishCategoryEnum;
            }
        }
        throw new IllegalArgumentException("Invalid dish category id");
    }

    public static DishCategoryEnum fromName(String name){
        for (DishCategoryEnum dishCategoryEnum : DishCategoryEnum.values()){
            if(dishCategoryEnum.name.equalsIgnoreCase(name)){
                return  dishCategoryEnum;
            }
        }
        throw new IllegalArgumentException("Invalid dish category name");
    }
}
