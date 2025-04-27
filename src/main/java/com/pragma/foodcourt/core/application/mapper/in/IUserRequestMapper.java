package com.pragma.foodcourt.core.application.mapper.in;

import com.pragma.foodcourt.core.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.core.domain.model.Profile;
import com.pragma.foodcourt.core.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface IUserRequestMapper {

    IUserRequestMapper INSTANCE = Mappers.getMapper(IUserRequestMapper.class);

    @Mapping(source = "userProfiles", target = "userProfiles", qualifiedByName = "listToSetProfile")
    User toUserModel(UserRequestDto userRequestDto);

    @Named("listToSetProfile")
    default Set<Profile> listToSetProfile(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptySet();
        }

        return ids.stream()
                .map(id -> {
                    Profile profile = new Profile();
                    profile.setProfileId(id);
                    return profile;
                })
                .collect(Collectors.toSet());
    }

    default Set<Integer> profilesToIntegers(Set<Profile> profiles) {
        if (profiles == null || profiles.isEmpty()) {
            return Collections.emptySet();
        }

        return profiles.stream()
                .map(Profile::getProfileId)
                .collect(Collectors.toSet());
    }
}