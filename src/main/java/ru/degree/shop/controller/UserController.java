package ru.degree.shop.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.degree.shop.DTO.token.JwtAuthenticationDto;
import ru.degree.shop.DTO.token.RefreshTokenDto;
import ru.degree.shop.DTO.user.ResetPasswordDto;
import ru.degree.shop.DTO.user.ResetPasswordRequestDto;
import ru.degree.shop.DTO.user.UserAuthPostDto;
import ru.degree.shop.DTO.user.UserDto;
import ru.degree.shop.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
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

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto user, Authentication authentication) {
        return new ResponseEntity<>(userService.updateUser(authentication.getName(), user), HttpStatus.OK);
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<Void> resetPasswordRequest(@RequestBody ResetPasswordRequestDto dto) {
        userService.sendResetPasswordEmail(dto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordDto dto) {
        userService.resetPassword(dto.getToken(), dto.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
