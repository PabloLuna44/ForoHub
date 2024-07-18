package com.forohub.forohub.controller;


import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.topic.*;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<TopicNewDTO> topicNewDTOJacksonTester;

    @Autowired
    private JacksonTester<TopicResponseDTO> topicResponseDTOJacksonTester;

    @Autowired
    private JacksonTester<TopicUpdateDTO> topicUpdateDTOJacksonTester;

    @MockBean
    private TopicService topicService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser
    void create() throws Exception {

        TopicNewDTO topicNewDTO=new TopicNewDTO("New topic","New Message",1L,1L);
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(1L,"New Topic","New Message",LocalDateTime.now(),LocalDateTime.now(), new UserResponseDTO(user),new CourseResponseDTO(course));

        when(topicService.save(any())).thenReturn(topicResponseDTO);

        var response= mvc.perform(post("/topic")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(topicNewDTOJacksonTester.write(topicNewDTO).getJson())
        )
        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var expectedJson = topicResponseDTOJacksonTester.write(topicResponseDTO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    @Test
    @WithMockUser
    void findAll() throws Exception {
        // Pageable settings
        int page = 0;
        int size = 10;
        String sort = "id,desc"; // sort by 'id' in descending order

        var response = mvc.perform(get("/topic")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser
    void findById() throws Exception {
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(1L,"New Topic","New Message",LocalDateTime.now(),LocalDateTime.now(), new UserResponseDTO(user),new CourseResponseDTO(course));

        // When
        when(topicService.findById(anyLong())).thenReturn(topicResponseDTO);

        var response = mvc.perform(get("/topic/1"))
                .andReturn().getResponse();


        System.out.println(response.getContentAsString());
        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser
    void findByUser() throws Exception {
        //Given
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(1L,"New Topic","New Message",LocalDateTime.now(),LocalDateTime.now(), new UserResponseDTO(user),new CourseResponseDTO(course));

        // When
        when(topicService.findById(anyLong())).thenReturn(topicResponseDTO);

        var response = mvc.perform(get("/topic/1"))
                .andReturn().getResponse();


        System.out.println(response.getContentAsString());
        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser
    void findByCourse() throws Exception {
        //Given
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(1L,"New Topic","New Message",LocalDateTime.now(),LocalDateTime.now(), new UserResponseDTO(user),new CourseResponseDTO(course));
        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<TopicResponseDTO> page = new PageImpl<>(List.of(topicResponseDTO), pageable, 1);

        // When
        when(topicService.findByCourse(any(Pageable.class), anyLong())).thenReturn(page);

        var response = mvc.perform(get("/topic/1"))
                .andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }



    @Test
    @WithMockUser
    void edit() throws Exception {
        //Given
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(5L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(1L,"Update Topic","Update Message",LocalDateTime.now(),LocalDateTime.now(), new UserResponseDTO(user),new CourseResponseDTO(course));
        TopicUpdateDTO topicUpdateDTO=new TopicUpdateDTO(1L,"Update Topic","Update Message", true,1L);

        // When
        when(topicService.edit(any())).thenReturn(topicResponseDTO);

        var response = mvc.perform(put("/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(topicUpdateDTOJacksonTester.write(topicUpdateDTO).getJson()))
                .andReturn().getResponse();

        //Then
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @WithMockUser
    void deleteTest() throws Exception{

        doNothing().when(topicService).delete(anyLong());

        // When
        var response = mvc.perform(delete("/answer/1"))
                .andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}