package com.pragma.foodcourt.core.application.handler.impl;

import com.pragma.foodcourt.core.application.dto.in.RestaurantRequestDto;
import com.pragma.foodcourt.core.application.dto.out.RestaurantResponseDto;
import com.pragma.foodcourt.core.application.handler.IRestaurantHandler;
import com.pragma.foodcourt.core.application.mapper.in.IRestaurantRequestMapper;
import com.pragma.foodcourt.core.application.mapper.out.IRestaurantResponseMapper;
import com.pragma.foodcourt.core.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.core.domain.api.IUserServicePort;
import com.pragma.foodcourt.core.domain.enums.ProfileEnum;
import com.pragma.foodcourt.core.domain.exception.ErrorMessages;
import com.pragma.foodcourt.core.domain.exception.InvalidUserException;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IUserServicePort userServicePort;

    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        User adminUser = userServicePort.getUserByIdentity(restaurantRequestDto.getRestaurantUser().getUserIdentityType()
                ,restaurantRequestDto.getRestaurantUser().getUserIdentityNumber());

        if(adminUser == null || !isUserAdministrator(adminUser)){
            throw new InvalidUserException(ErrorMessages.INVALID_USER_ADMINISTRATOR);
        }

        Restaurant restaurant = restaurantServicePort.createRestaurant(IRestaurantRequestMapper.INSTANCE.toRestaurantModel(restaurantRequestDto));
        return IRestaurantResponseMapper.INSTANCE.toRestaurantDto(restaurant);
    }

    boolean isUserAdministrator(User user) {
        if (user != null && user.getUserProfiles() != null) {
            return user.getUserProfiles().stream()
                    .anyMatch(profileId -> profileId.compareTo(ProfileEnum.ADMINISTRADOR.getId())==0);
        }
        return false;
    }
}
