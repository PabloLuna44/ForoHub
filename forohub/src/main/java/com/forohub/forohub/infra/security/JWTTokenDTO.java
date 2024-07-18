package com.forohub.forohub.infra.security;


import com.fasterxml.jackson.annotation.JsonAlias;

public record JWTTokenDTO(

        @JsonAlias("JWT") String JWT
) {

}
