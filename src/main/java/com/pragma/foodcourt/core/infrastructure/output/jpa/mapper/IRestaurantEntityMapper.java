package com.pragma.foodcourt.core.infrastructure.output.jpa.mapper;

import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.infrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IRestaurantEntityMapper {

    IRestaurantEntityMapper INSTANCE = Mappers.getMapper(IRestaurantEntityMapper.class);

    Restaurant toRestaurantModel(RestaurantEntity restaurantEntity);

    RestaurantEntity toRestaurantEntity(Restaurant restaurant);
}
