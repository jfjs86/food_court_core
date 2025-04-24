package com.pragma.foodcourt.core.domain.usercase;

import com.pragma.foodcourt.core.domain.enums.DishCategoryEnum;
import com.pragma.foodcourt.core.domain.enums.ProfileEnum;
import com.pragma.foodcourt.core.domain.exception.ErrorMessages;
import com.pragma.foodcourt.core.domain.exception.InvalidDishException;
import com.pragma.foodcourt.core.domain.model.Dish;
import com.pragma.foodcourt.core.domain.model.Restaurant;
import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @InjectMocks
    private DishUserCase dishUserCase;

    private Dish dish;
    private Restaurant restaurant;
    private User user;

    private static final int USER_IDENTITY_TYPE = 1;
    private static final String USER_IDENTITY_NUMBER = "5200433";
    private static final String USER_FIRST_NAME = "Pedro";
    private static final String USER_SECOND_NAME = "Pablo";
    private static final String USER_FIRST_LASTNAME = "Pérez";
    private static final String USER_SECOND_LASTNAME = "Pereira";
    private static final String USER_PHONE = "+573115208877";
    private static final LocalDate USER_BIRTHDATE = LocalDate.now().minusYears(30);
    private static final String USER_EMAIL = "pperez@mail.com";
    private static final String USER_PASSWORD = "pperez@mail.com";


    private static final String RESTAURANT_NAME = "Restaurante Prueba";
    private static final String RESTAURANT_IDENTITY = "8003212211";
    private static final String RESTAURANT_ADDRESS = "kr 33 # 51-23";
    private static final String RESTAURANT_PHONE = "+573102345530";
    private static final String RESTAURANT_LOGO = "https://www.logosrestaurantes.com/images/logo_restaurante_prueba.png";

    private static final String DISH_NAME = "Pizza";
    private static final String DISH_DESCRIPTION = "Pizza con distintos ingredientes";
    private static final BigDecimal DISH_PRICE = new BigDecimal("60000");
    private static final String DISH_IMAGE_URL = "https://www.logosrestaurantes.com/dish/images/pizza.png";;

    private static final String INVALID_DISH_DATA = "Invalid dish data: ";
    public static final String INVALID_DISH_RESTAURANT = INVALID_DISH_DATA+"Dish must be associated with a restaurant";
    public static final String INVALID_DISH_PRICE = INVALID_DISH_DATA+"Dish price must be integer positive";
    public static final String DISH_DATA_NULL = INVALID_DISH_DATA+"Dish data cannot be null";

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserIdentityType(USER_IDENTITY_TYPE);
        user.setUserIdentityNumber(USER_IDENTITY_NUMBER);
        user.setUserFirstName(USER_FIRST_NAME);
        user.setUserSecondName(USER_SECOND_NAME);
        user.setUserFirstLastName(USER_FIRST_LASTNAME);
        user.setUserSecondLastName(USER_SECOND_LASTNAME);
        user.setUserPhone(USER_PHONE);
        user.setUserBirthdate(USER_BIRTHDATE);
        user.setUserEmail(USER_EMAIL);
        user.setUserPassword(USER_PASSWORD);
        user.setUserProfiles(new HashSet<>(Collections.singletonList(ProfileEnum.PROPIETARIO.getId())));

        restaurant = new Restaurant();
        restaurant.setRestaurantName(RESTAURANT_NAME);
        restaurant.setRestaurantIdentity(RESTAURANT_IDENTITY);
        restaurant.setRestaurantAddress(RESTAURANT_ADDRESS);
        restaurant.setRestaurantPhone(RESTAURANT_PHONE);
        restaurant.setRestaurantLogo(RESTAURANT_LOGO);
        restaurant.setRestaurantUser(user);

        dish = new Dish();
        dish.setDishName(DISH_NAME);
        dish.setDishDescription(DISH_DESCRIPTION);
        dish.setDishPrice(DISH_PRICE);
        dish.setUrlImage(DISH_IMAGE_URL);
        dish.setDishCategory(DishCategoryEnum.PLATO_FUERTE.getId());
        dish.setDishRestaurant(restaurant);
    }

    @Test
    void createDish_valid(){
        when(dishPersistencePort.createDish(any(Dish.class))).thenReturn(dish);

        Dish createdDish = dishUserCase.createDish(dish);

        assertNotNull(createdDish, ErrorMessages.DISH_DATA_NULL);
        assertAll("Dish Details",
                () -> assertEquals(dish.getDishName(), createdDish.getDishName(), "Nombre"),
                () -> assertEquals(dish.getDishDescription(), createdDish.getDishDescription(), "Descripcion"),
                () -> assertEquals(dish.getDishPrice(), createdDish.getDishPrice(), "Precio"),
                () -> assertEquals(dish.getDishCategory(), createdDish.getDishCategory(), " Categoria"),
                () -> assertEquals(dish.getDishRestaurant().getRestaurantName(), dish.getDishRestaurant().getRestaurantName(), "Restaurante"),
                () -> assertEquals(dish.getUrlImage(), createdDish.getUrlImage(),"Imagen"),
                () -> assertEquals(createdDish.isActive(), true)
                );

        verify(dishPersistencePort, times(1)).createDish(any(Dish.class));
        verifyNoMoreInteractions(dishPersistencePort);

    }

    @Test
    void createDish_invalid_data_null(){
        when(dishPersistencePort.createDish(any(Dish.class))).thenReturn(dish);

        dish = null;
        InvalidDishException exception = assertThrows( InvalidDishException.class,
                () -> dishUserCase.createDish(dish)
        );

        assertEquals(DISH_DATA_NULL, exception.getMessage());
        verifyNoInteractions(dishPersistencePort);
    }

    @Test
    void createDish_invalid_restaurant_null(){
        when(dishPersistencePort.createDish(any(Dish.class))).thenReturn(dish);

        dish.setDishRestaurant(null);
        InvalidDishException exception = assertThrows( InvalidDishException.class,
                () -> dishUserCase.createDish(dish)
        );

        assertEquals(INVALID_DISH_RESTAURANT, exception.getMessage());
        verifyNoInteractions(dishPersistencePort);
    }

    @Test
    void createDish_invalid_dish_price_negative(){
        when(dishPersistencePort.createDish(any(Dish.class))).thenReturn(dish);

        dish.setDishPrice(new BigDecimal("-600000"));
        InvalidDishException exception = assertThrows( InvalidDishException.class,
                () -> dishUserCase.createDish(dish)
        );

        assertEquals(INVALID_DISH_PRICE, exception.getMessage());
        verifyNoInteractions(dishPersistencePort);
    }

    @Test
    void createDish_invalid_dish_price_no_integer(){
        when(dishPersistencePort.createDish(any(Dish.class))).thenReturn(dish);

        dish.setDishPrice(new BigDecimal("590000.99"));
        InvalidDishException exception = assertThrows( InvalidDishException.class,
                () -> dishUserCase.createDish(dish)
        );

        assertEquals(INVALID_DISH_PRICE, exception.getMessage());
        verifyNoInteractions(dishPersistencePort);
    }

}
