package com.ecommerce.domain.dto;

import java.util.UUID;

import com.ecommerce.domain.entities.Role;
import com.ecommerce.domain.valueobjects.Address;
import com.ecommerce.domain.valueobjects.Email;

public class UserDTO {

    private UUID id;
    private String username;
    private Email email;
    private Address address;
    private Role role;

    
    public UserDTO(UUID id, String username, Email email, Address address, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.role = role;
    }
    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}
    
    public String getUsername() {return username;}
    public void setUsername(String username) { this.username = username;}

    public Email getEmail() {return email;}
    public void setEmail(Email email) { this.email = email;}

    public Address getAddress() { return address;}
    public void setAddress(Address address) {this.address = address;}

    public Role getRole() { return role;}
    public void setRole(Role role) {this.role = role;}
    
    
}
