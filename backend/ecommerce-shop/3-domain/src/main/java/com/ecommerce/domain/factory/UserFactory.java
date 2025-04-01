package com.ecommerce.domain.factory;

import com.ecommerce.domain.entities.Role;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.valueobjects.Address;
import com.ecommerce.domain.valueobjects.Email;

public abstract class UserFactory {

    private final Role role;

    protected UserFactory(Role role) {
        this.role = role;
    }

    public User createUser(String username, Email email, String password, Address address){
        User user = new User(username, email, password, address, this.role);
        afterCreation(user);
        return user;
    };

    protected void afterCreation(User user) {}
}
