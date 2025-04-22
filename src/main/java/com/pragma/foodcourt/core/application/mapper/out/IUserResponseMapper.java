package com.pragma.foodcourt.core.application.mapper.out;


import com.pragma.foodcourt.core.application.dto.out.ProfiletResponseDto;
import com.pragma.foodcourt.core.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.core.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface IUserResponseMapper {
    IUserResponseMapper INSTANCE = Mappers.getMapper(IUserResponseMapper.class);

    @Mapping(target = "userProfiles", source = "userProfiles")
    UserResponseDto toUserDto(User user);

    @Mapping(target = "userProfiles", source = "userProfiles")
    User toUserModel(UserResponseDto userResponseDto);

    default Set<ProfiletResponseDto> mapProfileModelToProfileDto(Set<Integer> userProfiles) {
        if (userProfiles == null) {
            return Collections.emptySet();
        }

        return userProfiles.stream()
                .map(id -> {
                    ProfiletResponseDto profile = new ProfiletResponseDto();
                    profile.setProfileId(id);
                    return profile;
                })
                .collect(Collectors.toSet());
    }

    default Set<Integer> mapProfileDtoToProfileModel(Set<ProfiletResponseDto> userProfiles){
        if (userProfiles == null) {
            return Collections.emptySet();
        }

        return userProfiles.stream()
                .map(profile ->{
                    Integer id = profile.getProfileId();
                    return id;
                })
                .collect(Collectors.toSet());
    }

}
