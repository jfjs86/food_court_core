package com.pragma.foodcourt.core.infrastructure.output.feign.adapter;

import com.pragma.foodcourt.core.application.dto.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.UserResponseDto;
import com.pragma.foodcourt.core.application.mapper.IUserRequestMapper;
import com.pragma.foodcourt.core.application.mapper.IUserResponseMapper;
import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.core.infrastructure.output.feign.client.IUserApiClient;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserClientAdapter implements IUserPersistencePort {

    private final IUserApiClient userApiClient;

    @Override
    public User createOwnerUser(User user) {
        UserRequestDto userRequestDto = IUserRequestMapper.INSTANCE.toUserRequestDto(user);
        ApiResponse<UserResponseDto> userResponseDto = userApiClient.createOwnerUser(userRequestDto);
        return IUserResponseMapper.INSTANCE.toUserModel(userResponseDto.getBody());
    }
}
