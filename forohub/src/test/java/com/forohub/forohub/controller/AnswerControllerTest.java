package com.forohub.forohub.controller;

import com.forohub.forohub.domain.answer.Answer;
import com.forohub.forohub.domain.answer.AnswerNewDTO;
import com.forohub.forohub.domain.answer.AnswerResponseDTO;
import com.forohub.forohub.domain.answer.AnswerService;
import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.topic.TopicResponseDTO;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AnswerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AnswerNewDTO> answerNewDTOJacksonTester ;

    @Autowired
    private JacksonTester<AnswerResponseDTO> answerResponseDTOJacksonTester;

    @MockBean
    private AnswerService answerService;


    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void create() throws Exception {
        //Given
        LocalDateTime now=LocalDateTime.now();
        AnswerNewDTO answerNewDTO=new AnswerNewDTO("New Answer",1L,1L);



        //When


        var response= mvc.perform(post("/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(answerNewDTOJacksonTester.write(answerNewDTO).getJson())
        )
        .andReturn().getResponse();


        //Then
        assertThat(response).isEqualTo(HttpStatus.OK.value());
    }



    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void testFindById() {
    }

    @Test
    void edit() {
    }

    @Test
    void delete() {
    }
}