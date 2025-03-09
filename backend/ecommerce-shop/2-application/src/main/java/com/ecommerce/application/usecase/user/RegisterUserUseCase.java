package com.ecommerce.application.usecase.user;

import com.ecommerce.domain.entities.User;
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
        User user = UserMapper.toEntity(registerUserDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }
}
