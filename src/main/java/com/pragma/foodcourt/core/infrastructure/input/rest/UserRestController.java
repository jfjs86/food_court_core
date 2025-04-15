package com.pragma.foodcourt.core.infrastructure.input.rest;


import com.pragma.foodcourt.core.application.dto.UserRequestDto;
import com.pragma.foodcourt.core.application.handler.IUserHandler;
import com.pragma.foodcourt.core.infrastructure.input.rest.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserResource.ROOT)
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping(value = UserResource.CREATE_OWNER_USER)
    public ResponseEntity<?> createOwnerUser(@RequestBody UserRequestDto userRequest){
        return new WrapperResponse<>(true,"",userHandler.createOwnerUser(userRequest)).createSuccessResponse();
    }

}
