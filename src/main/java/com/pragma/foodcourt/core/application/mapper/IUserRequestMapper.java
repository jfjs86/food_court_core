package com.pragma.foodcourt.core.application.mapper;


import com.pragma.foodcourt.core.application.dto.UserRequestDto;
import com.pragma.foodcourt.core.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserRequestMapper {

    IUserRequestMapper INSTANCE = Mappers.getMapper(IUserRequestMapper.class);

    User toUserModel(UserRequestDto userRequestDto);

    UserRequestDto toUserRequestDto(User user);

}
