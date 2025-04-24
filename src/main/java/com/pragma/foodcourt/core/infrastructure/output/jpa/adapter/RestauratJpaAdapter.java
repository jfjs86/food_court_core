package com.pragma.foodcourt.core.infrastructure.output.jpa.adapter;

import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.core.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.foodcourt.core.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.foodcourt.core.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauratJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(IRestaurantEntityMapper.INSTANCE.toRestaurantEntity(restaurant));
        return IRestaurantEntityMapper.INSTANCE.toRestaurantModel(restaurantEntity);
    }

}
