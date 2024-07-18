package com.forohub.forohub.controller;

import com.forohub.forohub.domain.course.CourseNewDTO;
import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.course.CourseService;
import org.junit.jupiter.api.DisplayName;
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

import java.io.IOException;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CourseControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseService;

    @Autowired
    private JacksonTester<CourseNewDTO>  courseNewDTOJacksonTester;

    @Autowired
    private JacksonTester<CourseResponseDTO> courseResponseDTOJacksonTester; ;


    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void createStatus200() throws Exception {
        // Given
        CourseNewDTO courseNewDTO = new CourseNewDTO("New Title", "New Description");
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(null, "New Title", "New Description", LocalDateTime.now(), LocalDateTime.now());

        // When
        when(courseService.save(any())).thenReturn(courseResponseDTO);

        var response = mvc.perform(post("/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseNewDTOJacksonTester.write(courseNewDTO).getJson()))
                .andReturn().getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var expectedJson = courseResponseDTOJacksonTester.write(courseResponseDTO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }


    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void edit() {
    }

    @Test
    void delete() {
    }
}