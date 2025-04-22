package com.pragma.foodcourt.core.domain.api;

import com.pragma.foodcourt.core.domain.model.User;

public interface IUserServicePort {

    User createOwnerUser(User user);

    User getUserByIdentity(int identityType, String identityNumber);

}
