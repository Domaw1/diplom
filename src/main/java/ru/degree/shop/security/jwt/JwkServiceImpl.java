package ru.degree.shop.security.jwt;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Service;
import ru.degree.shop.service.JwkService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@Service
public class JwkServiceImpl implements JwkService {
    private final RSAKey rsaKey;

    public JwkServiceImpl() {
        try {
            RSAPrivateKey privateKey = getPrivateKey("private.pem");
            RSAPublicKey publicKey = getPublicKey("public.pem");

            this.rsaKey = new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки RSA ключей: " + e.getMessage(), e);
        }
    }

    @Override
    public JWKSet getJwkSet() {
        return new JWKSet(rsaKey);
    }

    @Override
    public RSAPrivateKey getPrivateKey(String filename) throws Exception {
        String key = readKeyFile(filename);
        byte[] decoded = Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(keySpec);
    }

    @Override
    public RSAPublicKey getPublicKey(String filename) throws Exception {
        String key = readKeyFile(filename);
        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(keySpec);
    }

    @Override
    public String readKeyFile(String filename) throws Exception {
        String path = System.getProperty("user.dir") + "/" + filename;
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
    }
}