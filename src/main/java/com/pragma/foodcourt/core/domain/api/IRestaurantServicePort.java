package com.pragma.foodcourt.core.domain.api;

import com.pragma.foodcourt.core.domain.model.Restaurant;

public interface IRestaurantServicePort {

    Restaurant createRestaurant(Restaurant restaurant);

}
