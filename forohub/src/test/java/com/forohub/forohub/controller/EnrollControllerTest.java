package com.forohub.forohub.controller;

import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.enroll.EnrollNewDTO;
import com.forohub.forohub.domain.enroll.EnrollResponseDTO;
import com.forohub.forohub.domain.enroll.EnrollService;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserResponseDTO;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class EnrollControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<EnrollNewDTO> enrollNewDTOJacksonTester;

    @Autowired
    private JacksonTester<EnrollResponseDTO> enrollResponseDTOJacksonTester;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private EnrollService enrollService;


    @Test
    @WithMockUser
    void enrollToCourse() throws Exception {
        String pwdEncode=passwordEncoder.encode("password");
        User user = new User(1L, "forohub", "forohub", "ForoHub", "forohub@example.com", pwdEncode, true, LocalDateTime.now(), LocalDateTime.now());
        Course course=new Course(1L,"New Course","New description",true,LocalDateTime.now(),LocalDateTime.now());
        EnrollNewDTO enrollNewDTO=new EnrollNewDTO(1L,1L);
        EnrollResponseDTO enrollResponseDTO=new EnrollResponseDTO(1L,new UserResponseDTO(user),new CourseResponseDTO(course));


        when(enrollService.enrollToCourse(any())).thenReturn(enrollResponseDTO);

        var response= mvc.perform(post("/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enrollNewDTOJacksonTester.write(enrollNewDTO).getJson())
                )
                .andReturn().getResponse();

        System.out.println(response.getContentAsString());


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var expectedJson = enrollResponseDTOJacksonTester.write(enrollResponseDTO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);

    }

    @Test
    @WithMockUser
    void allCourseByUser() throws Exception {

        int page = 0;
        int size = 10;
        String sort = "id,desc"; // sort by 'id' in descending order

        var response = mvc.perform(get("/enroll/user/1")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sort))
                .andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}