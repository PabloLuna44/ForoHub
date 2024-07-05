package com.forohub.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegisterDTO(
         @NotBlank
         String name,
         @NotBlank
         String last_name,
         @NotBlank
         String username,
         @NotBlank
         String email,
         @Pattern(
                 regexp = "\\d{8,20}",
                 message = "La contraseña debe tener entre 8 y 20 caracteres"
         )
         String password
) {
}
