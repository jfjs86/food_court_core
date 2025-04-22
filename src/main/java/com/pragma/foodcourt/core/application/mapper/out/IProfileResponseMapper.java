package com.pragma.foodcourt.core.application.mapper.out;

import com.pragma.foodcourt.core.application.dto.out.ProfiletResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Profile;

@Mapper
public interface IProfileResponseMapper {

    IProfileResponseMapper INSTANCE = Mappers.getMapper(IProfileResponseMapper.class);

    ProfiletResponseDto toProfileDto(Profile profile);

}
