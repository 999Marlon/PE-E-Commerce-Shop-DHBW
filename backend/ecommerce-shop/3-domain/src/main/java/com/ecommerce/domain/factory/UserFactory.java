package com.ecommerce.domain.factory;

import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.valueobjects.Address;
import com.ecommerce.domain.valueobjects.Email;

public abstract class UserFactory {

    public abstract User createUser(String username, Email email, String password, Address address);
}
