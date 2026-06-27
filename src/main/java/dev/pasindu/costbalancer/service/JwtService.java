package dev.pasindu.costbalancer.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
    Claims extractAllClaims(String token);
    String extractUsername(String token);
    String extractRole(String token);
    Date extractExpiration(String token);
    SecretKey getSigningKey();
}
