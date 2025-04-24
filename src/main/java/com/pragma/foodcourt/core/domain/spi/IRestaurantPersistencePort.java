package com.pragma.foodcourt.core.domain.spi;

import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.model.User;

public interface IRestaurantPersistencePort {

    Restaurant createRestaurant(Restaurant restaurant);

}
