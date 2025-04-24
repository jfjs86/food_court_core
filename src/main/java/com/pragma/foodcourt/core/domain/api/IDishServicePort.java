package com.pragma.foodcourt.core.domain.api;

import com.pragma.foodcourt.core.domain.model.Dish;

public interface IDishServicePort {

    Dish createDish(Dish dish);

}
