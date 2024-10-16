package com.witnes.SpringSecEx.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private String secretKey = "";

    public JWTService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk = keyGen.generateKey();
        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    }

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>(); // You can add custom claims if needed

        return Jwts.builder()
                .setClaims(claims) // Set claims here (if any)
                .setSubject(username) // Set the username (subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set issue date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set expiration (10 hours)
                .signWith(getKey()) // Sign with the generated secret key
                .compact(); // Generate the token
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Decode the secret key from Base64
        return Keys.hmacShaKeyFor(keyBytes); // Use the key for HMAC-SHA
    }
}
