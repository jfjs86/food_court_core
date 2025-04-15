package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.exception.InvalidUserException;
import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;

    @BeforeEach
    void setUp(){
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
        assertEquals(user.getUserFirstName(), createdUser.getUserFirstName());
        assertEquals(user.getUserSecondName(), createdUser.getUserSecondName());
        assertEquals(user.getUserFirstLastName(), createdUser.getUserFirstLastName());
        assertEquals(user.getUserSecondLastName(), createdUser.getUserSecondLastName());
        assertEquals(user.getUserBirthdate(), createdUser.getUserBirthdate());
        assertEquals(user.getUserPhone(), createdUser.getUserPhone());
        assertEquals(user.getUserEmail(), createdUser.getUserEmail());
        assertEquals(user.getUserPassword(), createdUser.getUserPassword());

    }

    @Test
    void createUser_invalid_email_format_without_at_symbol(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserEmail("pperemail.com");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid email format", exception.getMessage());
    }

    @Test
    void createUser_invalid_email_format_without_mail_domain(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserEmail("ppere@mail");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid email format", exception.getMessage());
    }

    @Test
    void createUser_invalid_user_age(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserBirthdate(LocalDate.now().minusYears(15));
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: User must be at least 18 years old", exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_character(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserPhone("#573102334312");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid phone number format or length (max 13 digits with optional '+')", exception.getMessage());
    }

    @Test
    void createUser_invalid_phone_bad_lenght(){
        when(userPersistencePort.createOwnerUser(any(User.class))).thenReturn(user);
        user.setUserPhone("+573102334312222");
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userUseCase.createOwnerUser(user));
        assertEquals("Invalid user data: Invalid phone number format or length (max 13 digits with optional '+')", exception.getMessage());
    }
}
