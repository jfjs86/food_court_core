package com.pragma.foodcourt.core.infrastructure.configuration;

import com.pragma.foodcourt.core.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.core.domain.api.IUserServicePort;
import com.pragma.foodcourt.core.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.core.domain.usercase.RestaurantUserCase;
import com.pragma.foodcourt.core.domain.usercase.UserUseCase;
import com.pragma.foodcourt.core.infrastructure.output.feign.adapter.UserClientAdapter;
import com.pragma.foodcourt.core.infrastructure.output.feign.client.IUserApiClient;
import com.pragma.foodcourt.core.infrastructure.output.jpa.adapter.RestauratJpaAdapter;
import com.pragma.foodcourt.core.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserApiClient userApiClient;
    private final IRestaurantRepository restaurantRepository;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserClientAdapter(userApiClient);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestauratJpaAdapter(restaurantRepository, userApiClient);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUserCase(restaurantPersistencePort());
    }
}
