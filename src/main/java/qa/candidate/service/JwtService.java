package qa.candidate.service;


import qa.candidate.model.GenerateTokenRequest;

import java.util.List;

public interface JwtService{

    public boolean verificarToken(String token);

    public String generateToken(GenerateTokenRequest claimsRequest);

    public List<String> getRolesFromToken(String token);

}