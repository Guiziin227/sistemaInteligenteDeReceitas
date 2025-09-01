package com.github.guiziin227.sistemadereceitas.dtos.request;

import jakarta.validation.constraints.Email;

public record LoginRequestDTO(
        String firstName,
        @Email(message = "Email inválido")
        String email,
        String password
) {
}
