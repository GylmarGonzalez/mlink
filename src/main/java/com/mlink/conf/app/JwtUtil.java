package com.mlink.conf.app;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 Clave secreta (debe ser de al menos 32 caracteres)
    private static final String SECRET_KEY = "EsteEsUnSuperSecretoDe32Caracteres!!";

    // 🔥 Usamos SecretKey en lugar de Key
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 🛠 Generar Token JWT
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())  // Cambiado de setSubject() a subject()
                .issuedAt(new Date()) 
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(key)  // 🔥 Firma corregida con SecretKey
                .compact();
    }

    // 📥 Extraer Usuario desde el Token
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)  // 🔥 Ahora sí acepta la clave correcta
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // ✅ Validar Token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 🔎 Verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }
}