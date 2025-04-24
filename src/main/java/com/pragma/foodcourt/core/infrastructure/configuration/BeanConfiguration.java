package com.pragma.foodcourt.core.infrastructure.configuration;

import com.pragma.foodcourt.core.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.core.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.core.domain.usercase.RestaurantUserCase;
import com.pragma.foodcourt.core.infrastructure.output.jpa.adapter.RestauratJpaAdapter;
import com.pragma.foodcourt.core.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestauratJpaAdapter(restaurantRepository);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUserCase(restaurantPersistencePort());
    }
}
