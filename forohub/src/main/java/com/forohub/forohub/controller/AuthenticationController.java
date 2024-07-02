package com.forohub.forohub.controller;


import com.forohub.forohub.domain.user.*;
import com.forohub.forohub.infra.security.JWTTokenDTO;
import com.forohub.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity Authentication(@RequestBody @Valid UserAuthenticationDTO user){
        Authentication authToken=new UsernamePasswordAuthenticationToken(user.email(),user.password());
        var authenticatedUser=authenticationManager.authenticate(authToken);
        var JWTtoken=tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTtoken));
    }


    @PostMapping("/sing-up")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO user){

        UserResponseDTO userResponseDTO=new UserResponseDTO(userService.register(user));

        return ResponseEntity.ok(userResponseDTO);
    }

}


