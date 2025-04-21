package com.pragma.foodcourt.core.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "t_restaurant")
@Getter
@Setter
public class RestaurantEntity {

    @Id
    @Column(name = "restaurant_id",unique = true, columnDefinition = "UUID DEFAULT gen_random_uuid()")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID restaurantId;
    @Column(name = "restaurant_name", length = 100, nullable = false)
    private String restaurantName;
    @Column(name = "restaurant_identity", length = 50, nullable = false)
    private String restaurantIdentity;
    @Column(name = "restaurant_address", length = 50)
    private String restaurantAddress;
    @Column(name = "restaurant_phone", length = 13, nullable = false)
    private String restaurantPhone;
    @Column(name = "restaurant_logo", length = 250)
    private String restaurantLogo;
    @Column(name = "restaurant_creation_user")
    private UUID restaurantCreationUser;
}
