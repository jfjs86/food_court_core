package com.pragma.foodcourt.core.application.handler.impl;

import com.pragma.foodcourt.core.application.dto.in.RestaurantRequestDto;
import com.pragma.foodcourt.core.application.dto.out.RestaurantResponseDto;
import com.pragma.foodcourt.core.application.handler.IRestaurantHandler;
import com.pragma.foodcourt.core.application.mapper.in.IRestaurantRequestMapper;
import com.pragma.foodcourt.core.application.mapper.out.IRestaurantResponseMapper;
import com.pragma.foodcourt.core.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantServicePort.createRestaurant(IRestaurantRequestMapper.INSTANCE.toRestaurantModel(restaurantRequestDto));
        return IRestaurantResponseMapper.INSTANCE.toRestaurantDto(restaurant);
    }
}
