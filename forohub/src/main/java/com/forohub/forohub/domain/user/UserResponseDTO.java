package com.forohub.forohub.domain.user;


public record UserResponseDTO (
        String username,
        String email
) {

    public UserResponseDTO(User user) {

        this(
                user.getUsername1(),
                user.getEmail()
        );
    }

}
