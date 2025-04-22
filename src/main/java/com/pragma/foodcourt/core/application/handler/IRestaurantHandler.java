package com.pragma.foodcourt.core.application.handler;

import com.pragma.foodcourt.core.application.dto.in.RestaurantRequestDto;
import com.pragma.foodcourt.core.application.dto.out.RestaurantResponseDto;

public interface IRestaurantHandler {

    RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto);

}
