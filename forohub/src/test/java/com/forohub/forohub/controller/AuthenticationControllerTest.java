package com.forohub.forohub.controller;

import com.auth0.jwt.JWTVerifier;
import com.forohub.forohub.domain.dataConvert.DataConvert;
import com.forohub.forohub.domain.user.UserAuthenticationDTO;
import com.forohub.forohub.domain.user.UserRegisterDTO;
import com.forohub.forohub.domain.user.UserResponseDTO;
import com.forohub.forohub.domain.user.UserService;

import com.forohub.forohub.infra.security.JWTTokenDTO;
import com.forohub.forohub.infra.security.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AuthenticationControllerTest {


    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<UserResponseDTO> userResponseDTOJacksonTester;

    @Autowired
    private JacksonTester<UserRegisterDTO> userRegisterDTOJacksonTester;

    @Autowired
    private JacksonTester<UserAuthenticationDTO> userAuthenticationDTOJacksonTester;

    @Autowired
    private TokenService tokenService;

    @Test
    void authentication() throws Exception {

        UserAuthenticationDTO userAuthenticationDTO=new UserAuthenticationDTO("forohub@example.com","123456789");
        DataConvert dataConvert=new DataConvert();

        var response = mvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userAuthenticationDTOJacksonTester.write(userAuthenticationDTO).getJson()))
                .andReturn().getResponse();



        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        JWTTokenDTO jwt=dataConvert.getData(response.getContentAsString(),JWTTokenDTO.class);
        var subject=tokenService.getSubject(jwt.JWT());
        assertThat(userAuthenticationDTO.email()).isEqualTo(subject);
    }

    @Test
    void register() throws  Exception{
        UserRegisterDTO userRegisterDTO=new UserRegisterDTO("forohub", "forohub", "ForoHub", "forohub@example.com", "12345678");
        UserResponseDTO userResponseDTO=new UserResponseDTO(1L,"Forohub","forohub@example.com");

        when(userService.register(userRegisterDTO)).thenReturn(userResponseDTO);

        var response = mvc.perform(post("/sing-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegisterDTOJacksonTester.write(userRegisterDTO).getJson()))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var expectedJson = userResponseDTOJacksonTester.write(userResponseDTO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}