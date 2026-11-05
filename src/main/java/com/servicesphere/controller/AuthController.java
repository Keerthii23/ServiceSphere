
package com.servicesphere.controller;

import com.servicesphere.dto.RegisterRequest;
import com.servicesphere.dto.RegisterResponse;
import com.servicesphere.entity.User;
import com.servicesphere.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request) {

        try {
            User user = userService.registerUser(request);

            RegisterResponse response = new RegisterResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getRole(),
                    user.getCreatedAt()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(exception.getMessage());
        }
    }
}
