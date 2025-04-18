package ru.degree.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.degree.shop.service.JwkService;

import java.util.Map;

@RestController
@RequestMapping(".well-known")
@AllArgsConstructor
public class JwkController {
    private final JwkService jwkService;

    @GetMapping("/jwks.json")
    public Map<String, Object> jwks() {
        return jwkService.getJwkSet().toJSONObject();
    }
}
