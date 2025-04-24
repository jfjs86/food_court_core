package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.core.domain.exception.ErrorMessages;
import com.pragma.foodcourt.core.domain.exception.InvalidRestaurantException;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.core.domain.util.DomainUtils;

import java.util.ArrayList;
import java.util.List;

public class RestaurantUserCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private static final int MAX_PHONE_LENGTH = 13;

    public RestaurantUserCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }


    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {

        List<String> errors = validateRestaurant(restaurant);

        if (!errors.isEmpty()) {
            throw new InvalidRestaurantException(ErrorMessages.INVALID_RESTAURANT_DATA + String.join(", ", errors));
        }

        return restaurantPersistencePort.createRestaurant(restaurant);
    }

    private List<String> validateRestaurant(Restaurant restaurant){
        List<String> errors =new ArrayList<>();

        if(restaurant == null){
            errors.add(ErrorMessages.RESTAURANT_DATA_NULL);
            return errors;
        }

        if(!DomainUtils.isNumber(restaurant.getRestaurantIdentity())){
            errors.add(ErrorMessages.INVALID_RESTAURANT_IDENTITY);
        }

        if(!DomainUtils.isValidPhone(restaurant.getRestaurantPhone(), MAX_PHONE_LENGTH)){
            errors.add(String.format(ErrorMessages.INVALID_PHONE_FORMAT, MAX_PHONE_LENGTH));
        }

        if(DomainUtils.isNumber(restaurant.getRestaurantName())){
            errors.add(ErrorMessages.INVALID_RESTAURANT_NAME);
        }

        return errors;
    }


}
