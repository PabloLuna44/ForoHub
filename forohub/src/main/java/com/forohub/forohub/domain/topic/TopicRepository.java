package com.forohub.forohub.domain.topic;

import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    Page<Topic> findByStatusTrue(Pageable pageable);

    Page<Topic> findByUser(Pageable pageable, User user);

    Page<Topic> findByCourseAndStatusTrue(Pageable pageable, Course course);

    Optional<Topic> findByIdAndStatusTrue(Long aLong);
}
