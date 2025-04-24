package com.pragma.foodcourt.core.infrastructure.input.rest;

import com.pragma.foodcourt.core.application.dto.in.RestaurantRequestDto;
import com.pragma.foodcourt.core.application.dto.out.RestaurantResponseDto;
import com.pragma.foodcourt.core.application.handler.IRestaurantHandler;
import com.pragma.foodcourt.core.infrastructure.input.rest.resource.RestaurantResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestaurantResource.ROOT)
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "Restaurant operations")
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PostMapping(value = RestaurantResource.CREATE_RESTAURANTE)
    @Operation(summary = "Restaurant creation",
            description = "Restaurant creation for Foodcourt.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurant created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RestaurantResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error (unknow exception)"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "409", description = "Conflict with external resources")
            })
    public ResponseEntity<WrapperResponse<RestaurantResponseDto>> createOwnerUser(@RequestBody RestaurantRequestDto restaurantRequestDto){
        return new WrapperResponse<RestaurantResponseDto>(true,"",restaurantHandler.createRestaurant(restaurantRequestDto)).createSuccessResponse();
    }
}
