package ru.degree.shop.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    @NotBlank
    private String username;
    private String password;
    @Email
    private String email;

    private String currentPassword;
}
