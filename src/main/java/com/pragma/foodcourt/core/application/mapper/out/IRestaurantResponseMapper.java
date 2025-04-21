package com.pragma.foodcourt.core.application.mapper.out;

import com.pragma.foodcourt.core.application.dto.out.RestaurantResponseDto;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IRestaurantResponseMapper {

    IRestaurantResponseMapper INSTANCE = Mappers.getMapper(IRestaurantResponseMapper.class);

    RestaurantResponseDto toRestaurantDto(Restaurant restaurant);

    Restaurant toRestaurantModel(RestaurantResponseDto restaurantResponseDto);
}
