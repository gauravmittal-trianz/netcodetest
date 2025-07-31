package com.bitwarden.server.controller;

import com.bitwarden.server.dto.UserKeyResponseDto;
import com.bitwarden.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('Application')")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/public-key")
    public ResponseEntity<UserKeyResponseDto> getPublicKey(@PathVariable String id) {
        try {
            UUID userId = UUID.fromString(id);
            String publicKey = userService.getPublicKey(userId);
            
            UserKeyResponseDto response = new UserKeyResponseDto(userId, publicKey);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
