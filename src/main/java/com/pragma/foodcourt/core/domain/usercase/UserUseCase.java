package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.api.IUserServicePort;
import com.pragma.foodcourt.core.domain.exception.InvalidUserException;
import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort{

    private final IUserPersistencePort userPersistencePort;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PHONE_REGEX = "^[+0-9]+$";
    private static final int MAX_PHONE_LENGTH = 13;
    private static final int AGE =18;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User createOwnerUser(User user) {
        List<String> errors = validateUser(user);
        if (!errors.isEmpty()) {
            throw new InvalidUserException("Invalid user data: " + String.join(", ", errors));
        }
        int profile = 2;
        Set<Integer> profiles = new HashSet<>();
        profiles.add(profile);
        user.setUserProfiles(profiles);
        return this.userPersistencePort.createOwnerUser(user);
    }

    private List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        if (user == null) {
            errors.add("User data cannot be null");
            return errors;
        }

        if (!isAdult(user.getUserBirthdate())) {
            errors.add("User must be at least "+AGE+" years old");
        }

        if (isNullOrEmpty(user.getUserEmail()) || (!validateEmail(user.getUserEmail())) ) {
            errors.add("Invalid email format");
        }

        if (!isValidPhone(user.getUserPhone())) {
            errors.add("Invalid phone number format or length (max " + MAX_PHONE_LENGTH + " digits with optional '+')");
        }

        return errors;
    }

    private boolean isAdult(LocalDate birthdate) {
        LocalDate today = LocalDate.now(java.time.Clock.systemDefaultZone());
        Period period = Period.between(birthdate, today);
        return period.getYears() >= AGE;
    }

    private boolean validateEmail(String email){

        if (email == null || email.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhone(String phone) {
        if (isNullOrEmpty(phone)) {
            return false;
        }
        String trimmedPhone = phone.trim();
        return trimmedPhone.length() <= MAX_PHONE_LENGTH && Pattern.matches(PHONE_REGEX, trimmedPhone);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


}
