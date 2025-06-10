package ru.degree.shop.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import ru.degree.shop.DTO.token.JwtAuthenticationDto;
import ru.degree.shop.model.User;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${jwt.access-token-expiration}")
    private Long accessTokenExpirationSeconds;

    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenExpirationSeconds;

    public JwtAuthenticationDto generateTokens(User user) {
        Instant now = Instant.now();

        String accessToken = generateAccessToken(user, now);
        String refreshToken = generateRefreshToken(user, now);

        JwtAuthenticationDto authDto = new JwtAuthenticationDto();
        authDto.setToken(accessToken);
        authDto.setRefreshToken(refreshToken);
        return authDto;
    }

    private String generateAccessToken(User user, Instant issuedAt) {
        Instant expiration = issuedAt.plusSeconds(accessTokenExpirationSeconds);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getEmail())
                .issuedAt(issuedAt)
                .expiresAt(expiration)
                .claim("userId", user.getId())
                .claim("role","ROLE_"+ user.getRole().name())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Jwt decodeToken(String token) {
        return jwtDecoder.decode(token);
    }

    private String generateRefreshToken(User user, Instant issuedAt) {
        Instant expiration = issuedAt.plusSeconds(refreshTokenExpirationSeconds);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getEmail())
                .issuedAt(issuedAt)
                .expiresAt(expiration)
                .claim("tokenType", "refresh")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateResetPasswordToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(15 * 60); // 15 минут

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getEmail())
                .issuedAt(now)
                .expiresAt(expiration)
                .claim("tokenType", "reset")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
