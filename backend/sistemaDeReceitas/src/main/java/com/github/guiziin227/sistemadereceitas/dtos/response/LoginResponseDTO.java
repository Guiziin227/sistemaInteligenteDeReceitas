package com.github.guiziin227.sistemadereceitas.dtos.response;

public record LoginResponseDTO(
        String email,
        String token,
        Long expireIn
) {
}
