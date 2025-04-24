package com.pragma.foodcourt.core.application.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {

    private String restaurantName;
    private String restaurantIdentity;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantLogo;

}
