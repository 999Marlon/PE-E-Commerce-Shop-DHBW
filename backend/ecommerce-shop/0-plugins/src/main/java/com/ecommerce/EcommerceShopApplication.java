package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ecommerce.adapters.interfaces.controller", "com.ecommerce"})
public class EcommerceShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceShopApplication.class, args);
    }
}
