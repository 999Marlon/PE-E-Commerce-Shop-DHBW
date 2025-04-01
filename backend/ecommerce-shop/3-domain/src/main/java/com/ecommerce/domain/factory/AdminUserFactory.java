package com.ecommerce.domain.factory;

import com.ecommerce.domain.entities.Role;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.valueobjects.Address;
import com.ecommerce.domain.valueobjects.Email;

public class AdminUserFactory extends UserFactory{

    @Override
    public User createUser(String username, Email email, String password, Address address) {
        return new User(username, email, password, address, Role.ADMIN);
    }
    
}
