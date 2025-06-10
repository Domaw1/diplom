package ru.degree.shop.security;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.degree.shop.DTO.token.JwtAuthenticationDto;
import ru.degree.shop.model.Role;
import ru.degree.shop.model.User;
import ru.degree.shop.repository.UserRepository;
import ru.degree.shop.security.jwt.JwtService;
import ru.degree.shop.service.JwkService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService, UserRepository userRepository) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/v1/user/sign_in", "/api/v1/products",
                                        "/api/v1/products/{id}", "/api/v1/products/filter",
                                        "/api/v1/category", "/api/v1/color", "/api/v1/size",
                                        "/api/v1/brand", "/api/v1/reviews/{id}",
                                        "/api/v1/variant", "/api/v1/variant/{id}",
                                        "/.well-known/**", "/login/oauth2/code/google",
                                        "/oauth2/**", "/login/oauth2/**",
                                        "/api/v1/user/reset-password", "/api/v1/user/reset-password-request")
                                .permitAll()
                                .requestMatchers(
                                        HttpMethod.POST,"/api/v1/user"
                                )
                                .anonymous()
                                .requestMatchers(
                                        "/api/v1/user/refresh-token","/api/v1/user/profile",
                                        "/api/v1/reviews", "/api/v1/order","/api/v1/order/user",
                                        "/api/v1/cart/user", "/api/v1/wishlist/user", "/api/v1/wishlist",
                                        "/api/v1/cart/clear", "/api/v1/cart/post", "/api/v1/cart",
                                        "/api/v1/order/user/{id}", "/api/v1/email/send", "/api/v1/user/update"
                                )
                                .authenticated()
                                .requestMatchers("/api/v1/products/create", "/api/v1/products/delete/{id}",
                                        "/api/v1/products/update", "/api/v1/order/update",
                                        "/api/v1/order/all", "/api/v1/brand/update", "/api/v1/brand/add",
                                        "/api/v1/brand/delete", "/api/v1/category/delete",
                                        "/api/v1/category/add", "/api/v1/category/update"
                                        )
                                .hasAnyRole("ADMIN")
                )
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(
                                (request, response, exception) -> {
                                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                    response.setContentType("application/json");
                                    response.getWriter().write("""
                                    {
                                        "message": "Unauthorized",
                                        "code": 401,
                                        "timestamp": "%s"
                                    }
                                    """.formatted(new Date()));
                        }))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(jwtConfigurer ->
                                jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .oauth2Login(oauth ->
                        oauth.successHandler(oAuth2AuthorizationSuccessHandler(jwtService, userRepository)));
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(List.of("http://localhost:8081"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationSuccessHandler oAuth2AuthorizationSuccessHandler
            (JwtService jwtService, UserRepository userRepository) {
        return (request, response, authentication) -> {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            String email = oAuth2User.getAttribute("email");

            User user = userRepository.findUserByEmail(email)
                    .orElseGet(() -> {
                        Map<String, Object> attributes = oAuth2User.getAttributes();
                        User newUser = new User();
                        newUser.setEmail(email);
                        newUser.setRole(Role.USER);
                        newUser.setUsername(attributes.get("username").toString());
                        return userRepository.save(newUser);
                    });

            JwtAuthenticationDto tokens = jwtService.generateTokens(user);
            String redirectUrl = String.format(
                    "http://localhost:8081/oauth-success?token=%s&refresh_token=%s",
                    tokens.getToken(),
                    tokens.getRefreshToken()
            );
            response.sendRedirect(redirectUrl);
        };
    }

    @Bean
    public JwtEncoder jwtEncoder(JwkService jwkService) {
        JWKSource<SecurityContext> jwkSource = (jwkSelector, context) ->
                jwkSelector.select(jwkService.getJwkSet());
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder(JwkService jwkService) throws Exception {
        RSAKey rsaKey = (RSAKey) jwkService.getJwkSet().getKeys().get(0);
        RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            String role = jwt.getClaimAsString("role");
            return List.of(new SimpleGrantedAuthority(role));
        });

        return converter;
    }
}
