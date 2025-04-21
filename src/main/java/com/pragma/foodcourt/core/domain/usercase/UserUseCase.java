package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.api.IUserServicePort;
import com.pragma.foodcourt.core.domain.enums.ProfileEnum;
import com.pragma.foodcourt.core.domain.exception.ErrorMessages;
import com.pragma.foodcourt.core.domain.exception.InvalidUserException;
import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.core.domain.util.DomainUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserUseCase implements IUserServicePort{

    private final IUserPersistencePort userPersistencePort;

    private static final int MAX_PHONE_LENGTH = 13;
    private static final int AGE =18;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User createOwnerUser(User user) {
        List<String> errors = validateUser(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException(ErrorMessages.INVALID_USER_DATA + String.join(", ", errors));
        }
        int profile = ProfileEnum.PROPIETARIO.getId();
        Set<Integer> profiles = new HashSet<>();
        profiles.add(profile);
        user.setUserProfiles(profiles);
        return this.userPersistencePort.createOwnerUser(user);
    }

    private List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        if (user == null) {
            errors.add(ErrorMessages.USER_DATA_NULL);
            return errors;
        }

        if (!DomainUtils.isAdult(user.getUserBirthdate(), AGE)) {
            errors.add(String.format(ErrorMessages.USER_NOT_ADULT, AGE));
        }

        if (DomainUtils.isNullOrEmpty(user.getUserEmail()) ||
                (!DomainUtils.validateEmail(user.getUserEmail())) ) {
            errors.add(ErrorMessages.INVALID_EMAIL_FORMAT);
        }

        if (!DomainUtils.isValidPhone(user.getUserPhone(), MAX_PHONE_LENGTH)) {
            errors.add(String.format(ErrorMessages.INVALID_PHONE_FORMAT, MAX_PHONE_LENGTH));
        }

        return errors;
    }

}
