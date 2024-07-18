package com.forohub.forohub.controller;

import com.forohub.forohub.domain.course.CourseNewDTO;
import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.course.CourseService;
import com.forohub.forohub.domain.course.CourseUpdateDTO;

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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


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
    private JacksonTester<CourseResponseDTO> courseResponseDTOJacksonTester;

    @Autowired
    private JacksonTester<CourseUpdateDTO> courseUpdateDTOJacksonTester;


    @Test
    @WithMockUser
    void create() throws Exception {
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
    @WithMockUser
    void findAll() throws Exception {

        int page = 0;
        int size = 10;
        String sort = "id,desc"; // sort by 'id' in descending order

        var response = mvc.perform(get("/course")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andReturn().getResponse();
    }

    @Test
    @WithMockUser
    void findById() throws Exception {
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(1L, "New Title", "New Description", LocalDateTime.now(), LocalDateTime.now());

        when(courseService.findById(any())).thenReturn(courseResponseDTO);

        var response=mvc.perform(get("/course/1"))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser
    void edit() throws Exception {

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(1L, "Update Title", "New Description", LocalDateTime.now(), LocalDateTime.now());
        CourseUpdateDTO courseUpdateDTO=new CourseUpdateDTO(1L,"Update Title","New Description",true);

        when(courseService.edit(any())).thenReturn(courseResponseDTO);

        var response= mvc.perform(put("/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(courseUpdateDTOJacksonTester.write(courseUpdateDTO).getJson()))
                .andReturn().getResponse();


        System.out.println(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @WithMockUser
    void deleteTest() throws Exception {

        doNothing().when(courseService).delete(anyLong());

        // When
        var response = mvc.perform(delete("/course/1"))
                .andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}