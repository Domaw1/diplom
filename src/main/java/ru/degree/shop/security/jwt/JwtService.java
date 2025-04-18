package ru.degree.shop.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;
import ru.degree.shop.DTO.token.JwtAuthenticationDto;
import ru.degree.shop.model.User;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;

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
}
