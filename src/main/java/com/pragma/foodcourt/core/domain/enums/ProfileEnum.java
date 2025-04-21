package com.pragma.foodcourt.core.domain.enums;

public enum ProfileEnum {

    ADMINISTRADOR(1,"Administrador"),
    PROPIETARIO(2,"Propietario"),
    EMPLEADO(3,"Empleado"),
    CLIENTE(4,"Cliente");

    private final int id;
    private final String name;

    ProfileEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ProfileEnum fromId(int id){
        for (ProfileEnum profileEnum : ProfileEnum.values()){
            if(profileEnum.id == id){
                return profileEnum;
            }
        }
        throw new IllegalArgumentException("Invalid profile id");
    }

    public static ProfileEnum fromName(String name){
        for (ProfileEnum profileEnum : ProfileEnum.values()){
            if(profileEnum.name.equalsIgnoreCase(name)){
                return  profileEnum;
            }
        }
        throw new IllegalArgumentException("Invalid profile name");
    }


}
