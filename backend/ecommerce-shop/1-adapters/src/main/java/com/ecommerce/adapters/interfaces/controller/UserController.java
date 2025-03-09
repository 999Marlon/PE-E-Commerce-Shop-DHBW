package com.ecommerce.adapters.interfaces.controller;

import com.ecommerce.application.usecase.user.DeleteUserUseCase;
import com.ecommerce.application.usecase.user.GetUserByIdUseCase;
import com.ecommerce.application.usecase.user.RegisterUserUseCase;
import com.ecommerce.application.usecase.user.UpdateUserUseCase;
import com.ecommerce.domain.dto.RegisterUserDTO;
import com.ecommerce.domain.dto.UserDTO;
import com.ecommerce.domain.entities.User;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(GetUserByIdUseCase getUserByIdUseCase,UpdateUserUseCase updateUserUseCase,DeleteUserUseCase deleteUserUseCase, RegisterUserUseCase registerUserUseCase) {
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
        return registerUserUseCase.execute(registerUserDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {
        return getUserByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        return updateUserUseCase.execute(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        deleteUserUseCase.execute(id);
    }
}
