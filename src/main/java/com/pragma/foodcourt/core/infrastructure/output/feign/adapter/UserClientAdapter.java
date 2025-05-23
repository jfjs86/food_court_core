package com.pragma.foodcourt.core.infrastructure.output.feign.adapter;

import com.pragma.foodcourt.core.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.core.application.mapper.in.IUserRequestMapper;
import com.pragma.foodcourt.core.application.mapper.out.IUserResponseMapper;
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

    @Override
    public User getUserByIdentity(int identityType, String identityNumber) {
        ApiResponse<UserResponseDto> userResponseDto = userApiClient.getUserByIdentity(identityType,identityNumber);
        return IUserResponseMapper.INSTANCE.toUserModel(userResponseDto.getBody());
    }


}
