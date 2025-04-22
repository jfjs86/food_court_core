package com.pragma.foodcourt.core.infrastructure.output.jpa.repository;

import com.pragma.foodcourt.core.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
}
