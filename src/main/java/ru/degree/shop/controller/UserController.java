package ru.degree.shop.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.token.JwtAuthenticationDto;
import ru.degree.shop.DTO.token.RefreshTokenDto;
import ru.degree.shop.DTO.user.UserAuthPostDto;
import ru.degree.shop.DTO.user.UserDto;
import ru.degree.shop.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getUserByEmail(Principal principal) {
        String email = principal.getName();
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/sign_in")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody UserAuthPostDto user) {
        return new ResponseEntity<>(userService.signIn(user), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthenticationDto> refreshToken(@RequestBody RefreshTokenDto refreshToken) {
        return new ResponseEntity<>(userService.refreshToken(refreshToken), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }
}
