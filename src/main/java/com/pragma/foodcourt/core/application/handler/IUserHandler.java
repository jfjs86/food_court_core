package com.pragma.foodcourt.core.application.handler;

import com.pragma.foodcourt.core.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.out.UserResponseDto;

public interface IUserHandler {

    UserResponseDto createOwnerUser(UserRequestDto userRequestDto);

}
