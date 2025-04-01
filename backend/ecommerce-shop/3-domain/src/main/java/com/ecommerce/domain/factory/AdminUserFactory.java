package com.ecommerce.domain.factory;

import com.ecommerce.domain.entities.Permission;
import com.ecommerce.domain.entities.Role;
import com.ecommerce.domain.entities.User;

public class AdminUserFactory extends UserFactory{

    public AdminUserFactory() {
        super(Role.ADMIN);
    }

    @Override
    protected void afterCreation(User user) {
        user.addPermission(Permission.CREATE_PRODUCTS);
        user.addPermission(Permission.DELETE_PRODUCTS);
        user.addPermission(Permission.MANAGE_DISCOUNTS);
        
        System.out.println("Admin-User rechte "+ user.getPermissions());
        System.out.println("Admin-User erstellt: " + user.getUsername());
    }
    
}
