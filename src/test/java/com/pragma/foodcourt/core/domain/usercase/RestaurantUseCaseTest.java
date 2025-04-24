package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.enums.ProfileEnum;
import com.pragma.foodcourt.core.domain.exception.ErrorMessages;
import com.pragma.foodcourt.core.domain.exception.InvalidRestaurantException;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private RestaurantUserCase restaurantUserCase;

    private Restaurant restaurant;
    private User user;

    private static final String INVALID_RESTAURANT_DATA = "Invalid restaurant data: ";
    private static final String INVALID_PHONE_MESSAGE = INVALID_RESTAURANT_DATA + "Invalid phone number format or length (max 13 digits with optional '+')";
    private static final String INVALID_IDENTITY_MESSAGE = INVALID_RESTAURANT_DATA + "Restaurant identity must be number";
    private static final String INVALID_NAME_MESSAGE = INVALID_RESTAURANT_DATA + "Restaurant name must have at least one letter";
    private static final String USER_MUST_BE_ADMIN_MESSAGE = INVALID_RESTAURANT_DATA + "User must be a Administrator";

    private static final String RESTAURANT_NAME = "Restaurante Prueba";
    private static final String RESTAURANT_IDENTITY = "8003212211";
    private static final String RESTAURANT_ADDRESS = "kr 33 # 51-23";
    private static final String RESTAURANT_PHONE = "+573102345530";
    private static final String RESTAURANT_LOGO = "https://www.logosrestaurantes.com/images/logo_restaurante_prueba.png";

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
        user.setUserProfiles(new HashSet<>(Collections.singletonList(ProfileEnum.ADMINISTRADOR.getId())));

        restaurant = new Restaurant();
        restaurant.setRestaurantName(RESTAURANT_NAME);
        restaurant.setRestaurantIdentity(RESTAURANT_IDENTITY);
        restaurant.setRestaurantAddress(RESTAURANT_ADDRESS);
        restaurant.setRestaurantPhone(RESTAURANT_PHONE);
        restaurant.setRestaurantLogo(RESTAURANT_LOGO);
        restaurant.setRestaurantUser(user);
    }

    @Test
    void createRestaurant_valid() {
        when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        Restaurant createdRestaurant = restaurantUserCase.createRestaurant(restaurant);

        assertNotNull(createdRestaurant, ErrorMessages.RESTAURANT_DATA_NULL);
        assertAll("Restaurant Details",
                () -> assertEquals(restaurant.getRestaurantName(), createdRestaurant.getRestaurantName(), "Nombre"),
                () -> assertEquals(restaurant.getRestaurantIdentity(), createdRestaurant.getRestaurantIdentity(), "Identidad"),
                () -> assertEquals(restaurant.getRestaurantAddress(), createdRestaurant.getRestaurantAddress(), "Dirección"),
                () -> assertEquals(restaurant.getRestaurantLogo(), createdRestaurant.getRestaurantLogo(), "Logo"),
                () -> assertEquals(restaurant.getRestaurantPhone(), createdRestaurant.getRestaurantPhone(), "Teléfono"),
                () -> assertNotNull(createdRestaurant.getRestaurantUser(), "Usuario no nulo"),
                () -> assertEquals(Collections.singleton(ProfileEnum.ADMINISTRADOR.getId()), createdRestaurant.getRestaurantUser().getUserProfiles(), "Perfil Administrador")
        );

        verify(restaurantPersistencePort, times(1)).createRestaurant(any(Restaurant.class));
        verifyNoMoreInteractions(restaurantPersistencePort);
    }

    @Test
    void createRestaurant_invalid_user_profile() {
        user.setUserProfiles(new HashSet<>(Collections.singletonList(ProfileEnum.CLIENTE.getId())));

        InvalidRestaurantException exception = assertThrows(InvalidRestaurantException.class,
                () -> restaurantUserCase.createRestaurant(restaurant));

        assertEquals(USER_MUST_BE_ADMIN_MESSAGE, exception.getMessage());
        verifyNoInteractions(restaurantPersistencePort);
    }

    @ParameterizedTest
    @CsvSource({
            "800ABC456," + INVALID_IDENTITY_MESSAGE,
            "+573102334312222," + INVALID_PHONE_MESSAGE,
            "*573105774234," + INVALID_PHONE_MESSAGE,
            "57+3105774234," + INVALID_PHONE_MESSAGE,
            "12345678," + INVALID_NAME_MESSAGE
    })
    void createRestaurant_invalid_data(String invalidValue, String expectedMessage) {
        when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        if (invalidValue.equalsIgnoreCase("800ABC456")) {
            restaurant.setRestaurantIdentity(invalidValue);
        }

        if (invalidValue.equalsIgnoreCase("+573102334312222")) {
            restaurant.setRestaurantPhone("+573102334312222");
        }

        if (invalidValue.equalsIgnoreCase("*573105774234")) {
            restaurant.setRestaurantPhone("*573105774234");
        }

        if (invalidValue.equalsIgnoreCase("57+3105774234")) {
            restaurant.setRestaurantPhone("57+3105774234");
        }

        if (invalidValue.equalsIgnoreCase("12345678")) {
            restaurant.setRestaurantName("12345678");
        }

        InvalidRestaurantException exception = assertThrows(InvalidRestaurantException.class,
                () -> restaurantUserCase.createRestaurant(restaurant));

        assertEquals(expectedMessage, exception.getMessage());
        verifyNoInteractions(restaurantPersistencePort);
    }
}