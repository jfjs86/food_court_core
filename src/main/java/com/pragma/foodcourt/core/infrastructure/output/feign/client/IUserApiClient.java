package com.pragma.foodcourt.core.infrastructure.output.feign.client;

import com.pragma.foodcourt.core.application.dto.UserRequestDto;
import com.pragma.foodcourt.core.application.dto.UserResponseDto;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.ApiResponse;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.UserClientRequest;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.UserClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "external-api-user-client", url = "${external.api.user.base-url}")
public interface IUserApiClient {

    @PostMapping("/api/user/create-user")
    ApiResponse<UserResponseDto> createOwnerUser(UserRequestDto userRequestDto);

}
