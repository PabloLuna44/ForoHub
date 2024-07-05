package com.forohub.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserAuthenticationDTO (
        @NotBlank
        String email,
        @Pattern(
                regexp = "\\d{8,20}",
                message = "La contraseña debe tener entre 8 y 20 caracteres"
        )
        String password
){
}
