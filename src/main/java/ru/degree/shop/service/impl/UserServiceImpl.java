package ru.degree.shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import ru.degree.shop.service.EmailService;
import ru.degree.shop.service.UserService;

import javax.naming.AuthenticationException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Override
    public JwtAuthenticationDto signIn(UserAuthPostDto userAuthPostDto) {
        User user = findByCredentials(userAuthPostDto);
        return jwtService.generateTokens(user);
    }

    @Override
    @SneakyThrows
    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();

        var jwt = jwtService.decodeToken(refreshToken);
        var email = jwt.getSubject();
        var tokenType = jwt.getClaimAsString("tokenType");

        if (!"refresh".equals(tokenType)) {
            throw new AuthenticationException("Недопустимый refresh токен");
        }

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        return jwtService.generateTokens(user);
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

    @Override
    public UserDto updateUser(String oldEmail, UserDto userDto) {
        User userToUpdate = userRepository.findUserByEmail(oldEmail)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        if(!passwordEncoder.matches(userDto.getCurrentPassword(), userToUpdate.getPassword())) {
            throw new RuntimeException("Неверный пароль!");
        }

        if (!userToUpdate.getEmail().equals(userDto.getEmail())) {
            userToUpdate.setEmail(userDto.getEmail());
        }

        if (!Objects.equals(userDto.getPassword(), "") && !passwordEncoder.matches(userDto.getPassword(), userToUpdate.getPassword())) {
            userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if(!userToUpdate.getUsername().equals(userDto.getUsername())) {
            userToUpdate.setUsername(userDto.getUsername());
        }

        userToUpdate.setRole(Role.USER);
        User savedUser = userRepository.save(userToUpdate);
        return userMapper.toDto(savedUser);
    }

    @Override
    public void sendResetPasswordEmail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        String token = jwtService.generateResetPasswordToken(user);
        String resetLink = "http://localhost:8081/reset-password?token=" + token;

        emailService.sendResetPasswordEmail(email, resetLink);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        var jwt = jwtService.decodeToken(token);

        if (!"reset".equals(jwt.getClaimAsString("tokenType"))) {
            throw new RuntimeException("Недопустимый токен для сброса пароля");
        }

        String email = jwt.getSubject();

        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
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
