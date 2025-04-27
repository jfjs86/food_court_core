package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.enums.ProfileEnum;
import com.pragma.foodcourt.core.domain.exception.ErrorMessages;
import com.pragma.foodcourt.core.domain.exception.InvalidRestaurantException;
import com.pragma.foodcourt.core.domain.model.Profile;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;
    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private RestaurantUserCase restaurantUserCase;

    private Restaurant restaurant;
    private User user;

    private static final String INVALID_RESTAURANT_DATA = "Invalid restaurant data: ";
    private static final String INVALID_PHONE_MESSAGE = INVALID_RESTAURANT_DATA + "Invalid phone number format or length (max 13 digits with optional '+')";
    private static final String INVALID_IDENTITY_MESSAGE = INVALID_RESTAURANT_DATA + "Restaurant identity must be number";
    private static final String INVALID_NAME_MESSAGE = INVALID_RESTAURANT_DATA + "Restaurant name must have at least one letter";
    private static final String INVALID_USER_ADMINISTRATOR = INVALID_RESTAURANT_DATA + "User must be a Administrator";

    private static final String RESTAURANT_NAME = "Restaurante Prueba";
    private static final String RESTAURANT_IDENTITY = "8003212211";
    private static final String RESTAURANT_ADDRESS = "kr 33 # 51-23";
    private static final String RESTAURANT_PHONE = "+573102345530";
    private static final String RESTAURANT_LOGO = "https://www.logosrestaurantes.com/images/logo_restaurante_prueba.png";

    private static final int IDENTITY_TYPE = 1;
    private static final String IDENTITY_NUMBER = "5200433";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserIdentityType(IDENTITY_TYPE);
        user.setUserIdentityNumber(IDENTITY_NUMBER);

        Profile profile = new Profile();
        profile.setProfileId(ProfileEnum.ADMINISTRADOR.getId());
        profile.setProfileName(ProfileEnum.ADMINISTRADOR.getName());

        user.setUserProfiles(Collections.singleton(profile));

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
        when(userPersistencePort.getUserByIdentity(IDENTITY_TYPE, IDENTITY_NUMBER)).thenReturn(user);
        when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        Restaurant createdRestaurant = restaurantUserCase.createRestaurant(restaurant);

        assertNotNull(createdRestaurant, ErrorMessages.RESTAURANT_DATA_NULL);
        assertAll("Restaurant Details",
                () -> assertEquals(restaurant.getRestaurantName(), createdRestaurant.getRestaurantName(), "Nombre"),
                () -> assertEquals(restaurant.getRestaurantIdentity(), createdRestaurant.getRestaurantIdentity(), "Identidad"),
                () -> assertEquals(restaurant.getRestaurantAddress(), createdRestaurant.getRestaurantAddress(), "Dirección"),
                () -> assertEquals(restaurant.getRestaurantLogo(), createdRestaurant.getRestaurantLogo(), "Logo"),
                () -> assertEquals(restaurant.getRestaurantPhone(), createdRestaurant.getRestaurantPhone(), "Teléfono"),
                () -> assertNotNull(createdRestaurant.getRestaurantUser(), "Usuario no nulo"));

        assertTrue(
                createdRestaurant.getRestaurantUser().getUserProfiles().stream()
                        .map(Profile::getProfileId)
                        .collect(Collectors.toSet())
                        .contains(ProfileEnum.ADMINISTRADOR.getId())
        );


        verify(restaurantPersistencePort, times(1)).createRestaurant(any(Restaurant.class));
        verifyNoMoreInteractions(restaurantPersistencePort);
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
        when(userPersistencePort.getUserByIdentity(IDENTITY_TYPE, IDENTITY_NUMBER)).thenReturn(user);
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

    @Test
    void createRestaurant_invalid_user_administrator(){
        when(userPersistencePort.getUserByIdentity(IDENTITY_TYPE, IDENTITY_NUMBER)).thenReturn(user);
        when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        Profile profile = new Profile();
        profile.setProfileId(ProfileEnum.CLIENTE.getId());
        profile.setProfileName(ProfileEnum.CLIENTE.getName());

        user.setUserProfiles(Collections.singleton(profile));

        InvalidRestaurantException exception = assertThrows(InvalidRestaurantException.class,
                () -> restaurantUserCase.createRestaurant(restaurant));

        assertEquals(INVALID_USER_ADMINISTRATOR, exception.getMessage());

        verify(userPersistencePort).getUserByIdentity(IDENTITY_TYPE, IDENTITY_NUMBER);
        verifyNoInteractions(restaurantPersistencePort);

    }


}