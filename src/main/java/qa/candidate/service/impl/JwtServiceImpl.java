package qa.candidate.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import qa.candidate.controller.JwtTokenGenerator;
import qa.candidate.model.GenerateTokenRequest;
import qa.candidate.service.JwtService;

import java.util.Date;
import java.util.List;


@Service
public class JwtServiceImpl implements JwtService {

    private String issuer = "seuIssuer"; // O emissor do token
    private static String secret = "seuSecret"; // A chave secreta usada para assinar o token

    public boolean verificarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build(); // Reusable verifier instance
            verifier.verify(token);
            return true; // Token é válido
        } catch (JWTVerificationException exception){
            // Token inválidoassim
            return false;
        }
    }

    public List<String> getRolesFromToken(String token) {
        // Decodifica o token
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);

        // Extrai as roles do payload. Assumindo que estão sob a chave "roles"
        List<String> roles = jwt.getClaim("roles").asList(String.class);

        return roles;
    }

    public String generateToken(GenerateTokenRequest claimsRequest) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hour expiration
                    .withClaim("claims", claimsRequest.getClains().stream().map(GenerateTokenRequest.Claim::getValue).toList())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Could not generate JWT", e);
        }
    }
}