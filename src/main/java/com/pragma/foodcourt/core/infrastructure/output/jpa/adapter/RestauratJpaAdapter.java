package com.pragma.foodcourt.core.infrastructure.output.jpa.adapter;

import com.pragma.foodcourt.core.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.core.application.mapper.out.IUserResponseMapper;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.core.infrastructure.output.feign.client.IUserApiClient;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.ApiResponse;
import com.pragma.foodcourt.core.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.foodcourt.core.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.foodcourt.core.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauratJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IUserApiClient userApiClient;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(IRestaurantEntityMapper.INSTANCE.toRestaurantEntity(restaurant));
        return IRestaurantEntityMapper.INSTANCE.toRestaurantModel(restaurantEntity);
    }

    @Override
    public User getUserByIdentity(int identityType, String identityNumber) {
        ApiResponse<UserResponseDto> userResponseDto = userApiClient.getUserByIdentity(identityType, identityNumber);
        return IUserResponseMapper.INSTANCE.toUserModel(userResponseDto.getBody());
    }

}
