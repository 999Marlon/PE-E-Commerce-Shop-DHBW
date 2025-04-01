package com.ecommerce.domain.entities;

import jakarta.persistence.*;
import java.util.UUID;

import com.ecommerce.domain.valueobjects.Address;
import com.ecommerce.domain.valueobjects.Email;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Embedded
    private Address address;
 
    @Column(nullable = false, unique = true)
    private Email email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    
    public User() {}

    public User(String username, Email email, String password, Address address, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public Email getEmail() { return email; }
    public String getPassword() { return password; }
    public Address getAddress() { return address;}
    public Role getRole() { return role; }

    public void setId(UUID id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setEmail(Email email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setAddress(Address address) { this.address = address;}
    public void setRole(Role role) { this.role = role; }

}
