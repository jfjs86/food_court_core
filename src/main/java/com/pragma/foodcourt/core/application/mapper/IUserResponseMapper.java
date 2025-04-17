package com.pragma.foodcourt.core.application.mapper;


import com.pragma.foodcourt.core.application.dto.UserResponseDto;
import com.pragma.foodcourt.core.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserResponseMapper {
    IUserResponseMapper INSTANCE = Mappers.getMapper(IUserResponseMapper.class);

    UserResponseDto toUserDto(User user);

    User toUserModel(UserResponseDto userResponseDto);

}
