package com.pragma.foodcourt.core.infrastructure.input.rest;


import com.pragma.foodcourt.core.application.dto.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.UserResponseDto;
import com.pragma.foodcourt.core.application.handler.IUserHandler;
import com.pragma.foodcourt.core.infrastructure.input.rest.resource.UserResource;
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
@RequestMapping(UserResource.ROOT)
@RequiredArgsConstructor
@Tag(name = "Users", description = "User operations")

public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping(value = UserResource.CREATE_OWNER_USER)
    @Operation(summary = "Owner user creation",
            description = "Owner user creation for restaurant administration.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Owner user created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error (unknow exception)"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "409", description = "Conflict with external resources")
            })
    public ResponseEntity<?> createOwnerUser(@RequestBody UserRequestDto userRequest){
        return new WrapperResponse<>(true,"",userHandler.createOwnerUser(userRequest)).createSuccessResponse();
    }

}
