package com.ecommerce.domain.dto;

import com.ecommerce.domain.valueobjects.Address;
import com.ecommerce.domain.valueobjects.Email;

public class RegisterUserDTO {
    private String username;
    private Email email;
    private String password;
    private Address address;

    public RegisterUserDTO(String username, Email email, String password, Address address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Email getEmail() { return email; }
    public void setEmail(Email email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Address getAddress() {return address;}

    public void setAddress(Address address) {this.address = address;}
}

