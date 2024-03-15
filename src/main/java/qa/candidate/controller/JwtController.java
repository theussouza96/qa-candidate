package qa.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qa.candidate.model.GenerateTokenRequest;
import qa.candidate.service.JwtService;

@RestController
public class JwtController {

    private final JwtService jwtService;
    @Autowired
        JwtController(JwtService service){
            jwtService = service;
        };

    @PostMapping("/gerar-token")
    public ResponseEntity gerarToken(@RequestBody GenerateTokenRequest request){

            String token = jwtService.generateToken(request);
            return ResponseEntity.ok(token);

    }
    @PostMapping("/verificar-token")
    public ResponseEntity<String> verificarToken(@RequestBody String token) {
        boolean isValid = jwtService.verificarToken(token);
        if (isValid) {
            return ResponseEntity.ok("Token válido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }

    @PostMapping("/verificar-claims")
    public ResponseEntity<String> verificarClaims(@RequestBody String token) {
        boolean isValid = jwtService.verificarToken(token);
        if (isValid) {
            return ResponseEntity.ok("Token válido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }
}