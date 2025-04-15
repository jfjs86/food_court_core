package com.pragma.foodcourt.core.infrastructure.output.feign.adapter;

import com.pragma.foodcourt.core.domain.model.User;
import com.pragma.foodcourt.core.domain.spi.IUserPersistencePort;
import com.pragma.foodcourt.core.infrastructure.output.feign.client.IUserApiClient;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.ApiResponse;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.UserClientRequest;
import com.pragma.foodcourt.core.infrastructure.output.feign.dto.UserClientResponse;
import com.pragma.foodcourt.core.infrastructure.output.feign.mapper.IUserClientMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserClientAdapter implements IUserPersistencePort {

    private final IUserApiClient userApiClient;

    @Override
    public User createOwnerUser(User user) {
        UserClientRequest userClientRequest = IUserClientMapper.INSTANCE.toUserClientRequest(user);
        ApiResponse<UserClientResponse> userClientResponse = userApiClient.createOwnerUser(userClientRequest);
        return IUserClientMapper.INSTANCE.toUserModelFromResponse(userClientResponse.getBody());
    }
}
