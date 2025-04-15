package com.pragma.foodcourt.core.infrastructure.configuration;

import com.pragma.foodcourt.core.domain.api.IUserServicePort;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.core.domain.usercase.UserUseCase;
import com.pragma.foodcourt.core.infrastructure.output.feign.adapter.UserClientAdapter;
import com.pragma.foodcourt.core.infrastructure.output.feign.client.IUserApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserApiClient userApiClient;


    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserClientAdapter(userApiClient);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }
}
