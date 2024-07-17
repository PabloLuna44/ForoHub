package com.forohub.forohub.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void authentication() {
    }

    @Test
    void register() {
    }
}