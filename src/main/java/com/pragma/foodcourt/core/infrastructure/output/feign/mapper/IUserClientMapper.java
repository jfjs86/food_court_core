package com.pragma.foodcourt.core.infrastructure.output.feign.mapper;

import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.UserClientRequest;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.UserClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserClientMapper {

    IUserClientMapper INSTANCE = Mappers.getMapper(IUserClientMapper.class);

    UserClientRequest toUserClientRequest(User user);

    User toUserModelFromResponse(UserClientResponse userClientResponse);

}
