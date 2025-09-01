package com.github.guiziin227.sistemadereceitas.controller;

import com.github.guiziin227.sistemadereceitas.dtos.request.LoginRequestDTO;
import com.github.guiziin227.sistemadereceitas.dtos.response.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import com.github.guiziin227.sistemadereceitas.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        var loginResp = tokenService.generateToken(loginRequestDTO);
        return ResponseEntity.ok(loginResp);
    }


}
