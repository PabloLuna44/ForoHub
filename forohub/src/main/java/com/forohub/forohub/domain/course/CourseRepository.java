package com.forohub.forohub.domain.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {



    Optional<Course> findByIdAndStatusTrue(Long aLong);


    Page<Course> findByStatusTrue(Pageable pageable);
}
