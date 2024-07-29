package com.ideacop.ecommerce.backend.infraestructure.jwt;

import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Constants {
    public static  final String HEADER_AUTHORIZATION= "Authorization";
    public static final String TOKEN_BEARER_PREFIX= "Bearer ";
    public static final String SUPER_SECRET_KEY= "d792d637695ee43db845a8c940a3d2c1d792d637695ee43db845a8c940a3d2c1d792d6h";
    public static final long TOKEN_EXPIRATION_TIME= 1500000; //15 minutos

    public static Key getSignedKey(String secretKey){
        byte[] keyBytes= secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
