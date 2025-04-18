package ru.degree.shop.service;

import ru.degree.shop.DTO.token.JwtAuthenticationDto;
import ru.degree.shop.DTO.token.RefreshTokenDto;
import ru.degree.shop.DTO.user.UserAuthPostDto;
import ru.degree.shop.DTO.user.UserDto;

public interface UserService {
    JwtAuthenticationDto signIn(UserAuthPostDto userAuthPostDto);
    JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto);
    UserDto getUserByEmail(String email);
    UserDto registerUser(UserDto userDto);
}
