package com.pragma.foodcourt.core.application.handler.impl;


import com.pragma.foodcourt.core.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.core.application.handler.IUserHandler;
import com.pragma.foodcourt.core.application.mapper.in.IUserRequestMapper;
import com.pragma.foodcourt.core.application.mapper.out.IUserResponseMapper;
import com.pragma.foodcourt.core.domain.api.IUserServicePort;
import com.pragma.foodcourt.core.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;

    @Override
    public UserResponseDto createOwnerUser(UserRequestDto userRequestDto) {
        User user = userServicePort.createOwnerUser(IUserRequestMapper.INSTANCE.toUserModel(userRequestDto));
        return IUserResponseMapper.INSTANCE.toUserDto(user);
    }
}
