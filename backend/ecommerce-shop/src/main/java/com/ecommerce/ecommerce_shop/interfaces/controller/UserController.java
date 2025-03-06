package com.ecommerce.ecommerce_shop.interfaces.controller;

import com.ecommerce.ecommerce_shop.application.usecase.user.DeleteUserUseCase;
import com.ecommerce.ecommerce_shop.application.usecase.user.GetUserByIdUseCase;
import com.ecommerce.ecommerce_shop.application.usecase.user.UpdateUserUseCase;
import com.ecommerce.ecommerce_shop.domain.entities.User;
import com.ecommerce.ecommerce_shop.interfaces.dto.UserDTO;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(GetUserByIdUseCase getUserByIdUseCase,UpdateUserUseCase updateUserUseCase,DeleteUserUseCase deleteUserUseCase) {
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
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
