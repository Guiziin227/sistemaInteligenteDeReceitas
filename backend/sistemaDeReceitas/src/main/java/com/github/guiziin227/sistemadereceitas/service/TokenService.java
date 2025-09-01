package com.github.guiziin227.sistemadereceitas.service;

import com.github.guiziin227.sistemadereceitas.dtos.request.LoginRequestDTO;
import com.github.guiziin227.sistemadereceitas.dtos.response.LoginResponseDTO;
import com.github.guiziin227.sistemadereceitas.entities.User;
import com.github.guiziin227.sistemadereceitas.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    Logger logger = LoggerFactory.getLogger(TokenService.class);

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //Injetando minhas dependências
    public TokenService(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO generateToken(LoginRequestDTO loginRequestDTO) {
        // 1 - validar usuario e senha

        var user = userRepository.findByFirstName(loginRequestDTO.firstName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user = userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //senha incorreta
        if (!user.isLoginCorrect(loginRequestDTO, passwordEncoder)) {
            logger.warn("Tentativa de login falhou para o usuário {}", loginRequestDTO.firstName());
            throw new RuntimeException("Algo deu errado");
        }

        // 2 - definindo tempo de expiração do token e scope
        var now = Instant.now();
        var expiresIn = 300L;

        var scope = user.getRoles()
                .stream()
                .map(role -> "ROLE_" + role.getName())
                .collect(Collectors.joining(" "));

        System.out.println(scope);


        // criando as claims do token
        var claims = JwtClaimsSet.builder()
                .issuer("sistema-de-receitas")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(user.getEmail())
                .claim("scope", scope)
                .build();


        //gerando o token
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        logger.info("Token gerado para o usuário {}: {}", user.getEmail(), jwtValue);
        return new LoginResponseDTO(user.getEmail(), jwtValue, expiresIn);
    }

}
