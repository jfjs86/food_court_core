package com.pragma.foodcourt.core.domain.spi;

import com.pragma.foodcourt.core.domain.model.Dish;

public interface IDishPersistencePort {
    Dish createDish(Dish dish);
}
