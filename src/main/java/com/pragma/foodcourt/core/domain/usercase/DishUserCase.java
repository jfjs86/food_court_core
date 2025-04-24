package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.api.IDishServicePort;
import com.pragma.foodcourt.core.domain.exception.ErrorMessages;
import com.pragma.foodcourt.core.domain.exception.InvalidDishException;
import com.pragma.foodcourt.core.domain.model.Dish;
import com.pragma.foodcourt.core.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.core.domain.util.DomainUtils;

import java.util.ArrayList;
import java.util.List;

public class DishUserCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUserCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public Dish createDish(Dish dish) {

        List<String> errors = validateDish(dish);

        if(!errors.isEmpty()){
            throw new InvalidDishException(ErrorMessages.INVALID_DISH_DATA+ String.join(", ", errors));
        }

        dish.setActive(true);
        return dishPersistencePort.createDish(dish);
    }

    private List<String> validateDish(Dish dish){
        List<String> errors = new ArrayList<>();

        if(dish == null){
            errors.add(ErrorMessages.DISH_DATA_NULL);
            return errors;
        }

        if(dish.getDishRestaurant() == null){
            errors.add(ErrorMessages.INVALID_DISH_RESTAURANT);
        }

        if(!DomainUtils.isIntegerPositive(dish.getDishPrice())){
            errors.add(ErrorMessages.INVALID_DISH_PRICE);
        }
        return errors;
    }

}
