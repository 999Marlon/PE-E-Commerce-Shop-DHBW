package com.ecommerce.domain.factory;

import com.ecommerce.domain.entities.Permission;
import com.ecommerce.domain.entities.Role;
import com.ecommerce.domain.entities.User;

public class DefaultUserFactory extends UserFactory {

    public DefaultUserFactory() {
        super(Role.USER);
    }

    @Override
    protected void afterCreation(User user) {
        user.addPermission(Permission.VIEW_ORDERS);
        user.addPermission(Permission.PURCHASE_PRODUCTS);
        
        System.out.println("Standard-User rechte "+ user.getPermissions());
        System.out.println("Standard-User erstellt: " + user.getUsername());
    }
    
}
