package com.forohub.forohub.domain.enroll;


import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.course.CourseRepository;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserRepository;
import com.forohub.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollService {

    @Autowired
    EnrollRepository enrollRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;


    public Enroll enrollToCourse (EnrollNewDTO enrollNewDTO){

        Optional<Course> course=courseRepository.findByIdAndStatusTrue(enrollNewDTO.course_id());
        if(course.isEmpty()){
            throw new IntegrityValidation("Course Not Found");
        }

        Optional<User> user=userRepository.findById(enrollNewDTO.user_id());
        if(user.isEmpty()){
            throw new IntegrityValidation("User Not Found");
        }

        Enroll enroll=new Enroll(user.get(),course.get());

        return enrollRepository.save(enroll);

    }

    public Page<EnrollResponseDTO> allCourseByUser(Long id, Pageable pageable){

        Optional<User> user=userRepository.findById(id);

        if(user.isEmpty()){
            throw new IntegrityValidation("User Not Found");
        }

        return enrollRepository.findByUser(pageable,user.get()).map(EnrollResponseDTO::new);
    }


}
