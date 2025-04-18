package ru.degree.shop.service;


import com.nimbusds.jose.jwk.JWKSet;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public interface JwkService {
    JWKSet getJwkSet();
    RSAPrivateKey getPrivateKey(String filename) throws Exception;
    RSAPublicKey getPublicKey(String filename) throws Exception;
    String readKeyFile(String filename) throws Exception;
}
