package com.ecommerce.application.usecase.user;

import com.ecommerce.domain.entities.Role;
import com.ecommerce.domain.entities.User;
import com.ecommerce.domain.exceptions.UserRegistrationException;
import com.ecommerce.domain.factory.AdminUserFactory;
import com.ecommerce.domain.factory.DefaultUserFactory;
import com.ecommerce.domain.factory.UserFactory;
import com.ecommerce.domain.mappers.UserMapper;
import com.ecommerce.domain.repository.UserRepository;
import com.ecommerce.domain.dto.RegisterUserDTO;
import com.ecommerce.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO execute(RegisterUserDTO registerUserDTO) {

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

        User user = userFactory.createUser(
            registerUserDTO.getUsername(),
            registerUserDTO.getEmail(),
            registerUserDTO.getPassword(),
            registerUserDTO.getAddress()
        );

        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }
}
