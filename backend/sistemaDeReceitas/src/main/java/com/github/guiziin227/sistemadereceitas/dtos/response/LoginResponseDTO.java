package com.github.guiziin227.sistemadereceitas.dtos.response;

public record LoginResponseDTO(
        String firstName,
        String token,
        Long expireIn
) {
}
