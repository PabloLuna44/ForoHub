package com.forohub.forohub.domain.course;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findByIdAndStatusTrue() {

        Long id=1L;

        Optional<Course> course=courseRepository.findByIdAndStatusTrue(id);


        assertThat(course).isNotEmpty();
        assertThat(course.get().getTitle()).isEqualTo("Steel");

    }

    @Test
    void findByStatusTrue() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<Course> result=courseRepository.findByStatusTrue(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(11);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Steel");


    }
}