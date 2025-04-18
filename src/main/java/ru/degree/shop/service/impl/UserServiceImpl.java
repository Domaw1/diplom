package ru.degree.shop.service.impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.degree.shop.DTO.token.JwtAuthenticationDto;
import ru.degree.shop.DTO.token.RefreshTokenDto;
import ru.degree.shop.DTO.user.UserAuthPostDto;
import ru.degree.shop.DTO.user.UserDto;
import ru.degree.shop.exception.EmailIsTaken;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.mapper.UserMapper;
import ru.degree.shop.model.Role;
import ru.degree.shop.model.User;
import ru.degree.shop.repository.UserRepository;
import ru.degree.shop.security.jwt.JwtService;
import ru.degree.shop.service.UserService;

import javax.naming.AuthenticationException;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public JwtAuthenticationDto signIn(UserAuthPostDto userAuthPostDto) {
        User user = findByCredentials(userAuthPostDto);
        return jwtService.generateTokens(user);
    }

    @Override
    @SneakyThrows
    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) {
        throw new UnsupportedOperationException("Refresh tokens not implemented yet");
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь с данной почтой не найден!")));
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);

        if(userRepository.findUserByEmail(userDto.getEmail())
                        .orElse(null) != null) {
            throw new EmailIsTaken("Данная почта уже занята!");
        };
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @SneakyThrows
    public User findByCredentials(UserAuthPostDto userAuthPostDto) {
        Optional<User> optionalUser = userRepository.findUserByEmail(userAuthPostDto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(passwordEncoder.matches(userAuthPostDto.getPassword(), user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("Неверная почта или пароль!");
    }
}
