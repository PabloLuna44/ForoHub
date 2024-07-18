package com.forohub.forohub.controller;


import com.forohub.forohub.domain.answer.*;
import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.topic.Topic;
import com.forohub.forohub.domain.topic.TopicResponseDTO;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserResponseDTO;
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

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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

    @Autowired
    private JacksonTester<AnswerUpdatedDTO> answerUpdatedDTOJacksonTester;

    @MockBean
    private AnswerService answerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void create() throws Exception {
        //Given
        LocalDateTime now = LocalDateTime.now();
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        Topic topic = new Topic(1L, "Sample Topic", "This is a sample topic", LocalDateTime.now(), LocalDateTime.now(), true,user,course);
        AnswerNewDTO answerNewDTO = new AnswerNewDTO("New Answer", 1L, 1L);
        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO(1L, "New Answer", new UserResponseDTO(user), new TopicResponseDTO(topic), now, now);

        //When
        when(answerService.save(any())).thenReturn(answerResponseDTO);


        var response= mvc.perform(post("/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(answerNewDTOJacksonTester.write(answerNewDTO).getJson())
        )
        .andReturn().getResponse();


        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var expectedJson = answerResponseDTOJacksonTester.write(answerResponseDTO).getJson();
        System.out.println(response.getContentAsString());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }



    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void findAll() throws Exception {
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        Topic topic = new Topic(1L, "Sample Topic", "This is a sample topic", LocalDateTime.now(), LocalDateTime.now(), true,user,course);
        AnswerNewDTO answerNewDTO = new AnswerNewDTO("New Answer", 1L, 1L);
        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO(1L, "New Answer", new UserResponseDTO(user), new TopicResponseDTO(topic), LocalDateTime.now(), LocalDateTime.now());

        // Pageable settings
        int page = 0;
        int size = 10;
        String sort = "id,desc"; // sort by 'id' in descending order

        // When
        when(answerService.save(any())).thenReturn(answerResponseDTO);

        var response = mvc.perform(get("/answer")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void findById() throws Exception  {
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        Topic topic = new Topic(1L, "Sample Topic", "This is a sample topic", LocalDateTime.now(), LocalDateTime.now(), true,user,course);
        AnswerNewDTO answerNewDTO = new AnswerNewDTO("New Answer", 1L, 1L);
        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO(1L, "New Answer", new UserResponseDTO(user), new TopicResponseDTO(topic), LocalDateTime.now(), LocalDateTime.now());

        // When
        when(answerService.findById(anyLong())).thenReturn(answerResponseDTO);

        var response = mvc.perform(get("/answer/1"))
                .andReturn().getResponse();


        System.out.println(response.getContentAsString());
        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void findByTopic() throws Exception {

        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        Topic topic = new Topic(1L, "Sample Topic", "This is a sample topic", LocalDateTime.now(), LocalDateTime.now(), true,user,course);
        AnswerNewDTO answerNewDTO = new AnswerNewDTO("New Answer", 1L, 1L);
        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO(1L, "New Answer", new UserResponseDTO(user), new TopicResponseDTO(topic), LocalDateTime.now(), LocalDateTime.now());

        // When
        when(answerService.findById(anyLong())).thenReturn(answerResponseDTO);

        int page = 0;
        int size = 10;
        String sort = "id,desc"; // sort by 'id' in descending order



        var response = mvc.perform(get("/answer/topic/1")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sort", sort))
                .andReturn().getResponse();


        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void edit() throws Exception {
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        Topic topic = new Topic(1L, "Sample Topic", "This is a sample topic", LocalDateTime.now(), LocalDateTime.now(), true,user,course);
        AnswerUpdatedDTO answerUpdatedDTO = new AnswerUpdatedDTO(1L,"Update Comment",true);
        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO(1L, "Update Comment", new UserResponseDTO(user), new TopicResponseDTO(topic), LocalDateTime.now(), LocalDateTime.now());

        // When
        when(answerService.edit(any())).thenReturn(answerResponseDTO);

        var response = mvc.perform(put("/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(answerUpdatedDTOJacksonTester.write(answerUpdatedDTO).getJson()))
                .andReturn().getResponse();

        //Then
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("This method should return status http 200 when the data are valid")
    @WithMockUser
    void deleteTets() throws Exception {

        doNothing().when(answerService).delete(anyLong());

        // When
        var response = mvc.perform(delete("/answer/1"))
                .andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());

    }
}
