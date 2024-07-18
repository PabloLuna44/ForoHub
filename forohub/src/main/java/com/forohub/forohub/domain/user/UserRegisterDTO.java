package com.forohub.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
         @NotBlank
         String name,
         @NotBlank
         String last_name,
         @NotBlank
         String username,
         @Email
         String email,
         @NotBlank
         @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
         String password
) {
}
