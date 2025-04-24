package com.pragma.foodcourt.core.domain.usercase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    private static final String INVALID_EMAIL_MESSAGE = "Invalid user data: Invalid email format";
    private static final String INVALID_AGE_MESSAGE = "Invalid user data: User must be at least 18 years old";
    private static final String INVALID_PHONE_MESSAGE = "Invalid user data: Invalid phone number format or length (max 13 digits with optional '+')";
    private static final String INVALID_USER_ID_TYPE = "User identity type invalid";
    private static final String INVALID_USER_ID_NUMBER = "User identity number cannot be null";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserIdentityType(1);
        user.setUserIdentityNumber("5200433");
        user.setUserFirstName("Pedro");
        user.setUserSecondName("Pablo");
        user.setUserFirstLastName("Pérez");
        user.setUserSecondLastName("Pereira");
        user.setUserPhone("+573115208877");
        user.setUserBirthdate(LocalDate.now().minusYears(30));
        user.setUserEmail("pperez@mail.com");
        user.setUserPassword("contraseña123#$%");
    }

    @Test
    void createUser_valid() {
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);

        User createdUser = userUseCase.createOwnerUser(user);

        assertNotNull(createdUser);
        assertAll("User Details",
                () -> assertEquals(user.getUserFirstName(), createdUser.getUserFirstName()),
                () -> assertEquals(user.getUserSecondName(), createdUser.getUserSecondName()),
                () -> assertEquals(user.getUserFirstLastName(), createdUser.getUserFirstLastName()),
                () -> assertEquals(user.getUserSecondLastName(), createdUser.getUserSecondLastName()),
                () -> assertEquals(user.getUserBirthdate(), createdUser.getUserBirthdate()),
                () -> assertEquals(user.getUserPhone(), createdUser.getUserPhone()),
                () -> assertEquals(user.getUserEmail(), createdUser.getUserEmail()),
                () -> assertEquals(user.getUserPassword(), createdUser.getUserPassword())
        );
    }

    @Test
    void createUser_invalid_email_format_without_at_symbol() {
        user.setUserEmail("pperemail.com");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals(INVALID_EMAIL_MESSAGE, exception.getMessage());
    }

    @Test
    void createUser_invalid_email_format_without_mail_domain() {
        user.setUserEmail("ppere@mail");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals(INVALID_EMAIL_MESSAGE, exception.getMessage());
    }

    @Test
    void createUser_invalid_user_age() {
        user.setUserBirthdate(LocalDate.now().minusYears(15));
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals(INVALID_AGE_MESSAGE, exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_character() {
        user.setUserPhone("#573102334312");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals(INVALID_PHONE_MESSAGE, exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_lenght() {
        user.setUserPhone("+573102334312222");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals(INVALID_PHONE_MESSAGE, exception.getMessage());
    }

    @Test
    void getUserByIdentity_invalid_identity_type_negative() {
        int invalidType = -1;
        String validNumber = "12345678";

        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.getUserByIdentity(invalidType, validNumber));
        assertEquals(INVALID_USER_ID_TYPE, exception.getMessage());
    }

    @Test
    void getUserByIdentity_invalid_identity_type_zero() {
        int invalidType = 0;
        String validNumber = "12345678";

        // Act & Assert
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.getUserByIdentity(invalidType, validNumber));

        assertEquals(INVALID_USER_ID_TYPE, exception.getMessage());
    }

    @Test
    void getUserByIdentity_invalid_identity_number_null() {
        int validType = 1;
        String invalidNumber = null;
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.getUserByIdentity(validType, invalidNumber));
        assertEquals(INVALID_USER_ID_NUMBER, exception.getMessage());
    }

    @Test
    void getUserByIdentity_invalid_identity_number_null_empty() {

        int validType = 1;
        String invalidNumber = "";

        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.getUserByIdentity(validType, invalidNumber));
        assertEquals(INVALID_USER_ID_NUMBER, exception.getMessage());
    }

    @Test
    void getUserByIdentity_invalid_identity_number_null_is_blank() {

        int validType = 1;
        String invalidNumber = "   ";

        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.getUserByIdentity(validType, invalidNumber));
        assertEquals(INVALID_USER_ID_NUMBER, exception.getMessage());
    }
}