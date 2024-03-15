package qa.candidate.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import qa.candidate.model.GenerateTokenRequest;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTokenGenerator {

    private static final String SECRET_KEY = "yourSecretKey"; // Use uma chave secreta forte

    public static String generateToken(String name, String role, int seed) {
        if (!name.matches("^[^0-9]*$")) {
            throw new IllegalArgumentException("Name cannot contain numbers.");
        }

        if (!role.equals("Admin") && !role.equals("Member") && !role.equals("External")) {
            throw new IllegalArgumentException("Role must be one of the following values: Admin, Member, External.");
        }

        if (!isPrime(seed)) {
            throw new IllegalArgumentException("Seed must be a prime number.");
        }

        if (name.length() > 256) {
            throw new IllegalArgumentException("Name cannot exceed 256 characters.");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("Name", name);
        claims.put("Role", role);
        claims.put("Seed", seed);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expira em 10 horas
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
                .compact();
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return claims.get("roles", String.class);
    }

    public static void main(String[] args) {
        try {
            String token = generateToken("UserName", "Admin", 17);
            System.out.println("JWT Token: " + token);
            System.out.println("Roles: " + getRolesFromToken(token));
        } catch (IllegalArgumentException e) {
            System.out.println("Error generating token: " + e.getMessage());
        }
    }


}