package com.pragma.foodcourt.core.infrastructure.output.feign.client;

import com.pragma.foodcourt.core.application.dto.in.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.out.UserResponseDto;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "external-api-user-client", url = "${external.api.user.base-url}")
public interface IUserApiClient {

    @PostMapping("/api/user/create-user")
    ApiResponse<UserResponseDto> createOwnerUser(UserRequestDto userRequestDto);

    @GetMapping("/api/user/get-user-by-type-number-identity")
    ApiResponse<UserResponseDto> getUserByIdentity(@RequestParam int identityType, @RequestParam String identityNumber);

}
