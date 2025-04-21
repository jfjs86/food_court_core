package com.pragma.foodcourt.core.application.dto.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {

    private String restaurantName;
    private String restaurantIdentity;
    private String restaurantAddress;
    private String restaurantPhone;
    private String restaurantLogo;
}
