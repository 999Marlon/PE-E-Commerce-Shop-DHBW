package com.ecommerce.domain.service;

import com.ecommerce.domain.entities.Role;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.exceptions.UserNotFoundException;
import com.ecommerce.domain.exceptions.UserRegistrationException;
import com.ecommerce.domain.factory.AdminUserFactory;
import com.ecommerce.domain.factory.DefaultUserFactory;
import com.ecommerce.domain.factory.UserFactory;
import com.ecommerce.domain.repository.UserRepository;
import com.ecommerce.domain.dto.RegisterUserDTO;
import com.ecommerce.domain.dto.UserDTO;
import com.ecommerce.domain.mappers.UserMapper;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(RegisterUserDTO registerUserDTO) {

        if (userRepository.findByEmail(registerUserDTO.getEmail()).isPresent()) {
            throw new UserRegistrationException("Diese E-Mail wird bereits verwendet.");
        }
        if (userRepository.findByUsername(registerUserDTO.getUsername()).isPresent()) {
            throw new UserRegistrationException("Dieser Benutzername wird bereits verwendet.");
        }

        UserFactory userFactory;
        
        if (registerUserDTO.getRole().equals(Role.ADMIN)) {
            userFactory = new AdminUserFactory();

        } else {
            userFactory = new DefaultUserFactory();
        }

        User userCreated = userFactory.createUser(
            registerUserDTO.getUsername(),
            registerUserDTO.getEmail(),
            registerUserDTO.getPassword(),
            registerUserDTO.getAddress()
        );

        User savedUser = userRepository.save(userCreated);
        return UserMapper.toDTO(savedUser);
    }

    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden"));
        return UserMapper.toDTO(user);
    }

    public UserDTO updateUser(UUID id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Benutzer nicht gefunden"));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        return UserMapper.toDTO(userRepository.save(existingUser));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
