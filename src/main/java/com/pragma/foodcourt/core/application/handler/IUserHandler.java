package com.pragma.foodcourt.core.application.handler;

import com.pragma.foodcourt.core.application.dto.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.UserResponseDto;

public interface IUserHandler {

    UserResponseDto createOwnerUser(UserRequestDto userRequestDto);

}
