package com.pragma.foodcourt.core.application.mapper.in;


import com.pragma.foodcourt.core.application.dto.in.RestaurantRequestDto;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IRestaurantRequestMapper {

    IRestaurantRequestMapper INSTANCE = Mappers.getMapper(IRestaurantRequestMapper.class);

    Restaurant toRestaurantModel(RestaurantRequestDto restaurantRequestDto);

    RestaurantRequestDto toRestaurantRequestDto(Restaurant restaurant);

}
