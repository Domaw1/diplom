package ru.degree.shop.DTO.user;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String token;
    private String newPassword;
}
