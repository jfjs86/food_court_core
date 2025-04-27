package com.pragma.foodcourt.core.domain.spi;

import com.pragma.foodcourt.core.domain.model.User;

public interface IUserPersistencePort {

    User getUserByIdentity(int identityType, String identityNumber);

}
