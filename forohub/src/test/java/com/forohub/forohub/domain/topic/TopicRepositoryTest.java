package com.forohub.forohub.domain.topic;

import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.course.CourseRepository;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;



    @Test
    void findByStatusTrue() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<Topic> result =topicRepository.findByStatusTrue(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(10);
    }

    @Test
    void findByUser() {

        Long id=1L;

        Optional<User> user=userRepository.findById(id);
        Pageable pageable = PageRequest.of(0, 10);


        Page<Topic> result=topicRepository.findByUser(pageable,user.get());

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(10);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("New Title");

    }

    @Test
    void findByCourseAndStatusTrue() {
        Long id=1L;

        Optional<Course> course=courseRepository.findById(id);
        Pageable pageable = PageRequest.of(0, 10);

        Page<Topic> result=topicRepository.findByCourseAndStatusTrue(pageable,course.get());

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(9);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Business-focused heuristic help-desk");

    }

    @Test
    void findByIdAndStatusTrue() {

        Long id=1L;

        Optional<Topic> topic=topicRepository.findByIdAndStatusTrue(id);

        assertThat(topic).isNotEmpty();
        assertThat(topic.get().getTitle()).isEqualTo("New Title");


    }
}