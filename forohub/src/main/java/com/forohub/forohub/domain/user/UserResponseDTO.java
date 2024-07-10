package com.forohub.forohub.domain.user;


public record UserResponseDTO (
        Long id,
        String username,
        String email
) {

    public UserResponseDTO(User user) {

        this(
                user.getId(),
                user.getUsername1(),
                user.getEmail()
        );
    }

}
