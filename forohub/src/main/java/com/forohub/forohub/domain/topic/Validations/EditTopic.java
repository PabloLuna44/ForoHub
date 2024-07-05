package com.forohub.forohub.domain.topic.Validations;

import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.course.CourseRepository;
import com.forohub.forohub.domain.topic.TopicService;
import com.forohub.forohub.domain.topic.TopicUpdateDTO;
import com.forohub.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EditTopic implements TopicValidator{


    @Autowired
    private CourseRepository courseRepository;

    public void validate(TopicUpdateDTO topicUpdateDTO) {

        if(topicUpdateDTO.course_id()!=null){

            Optional<Course> course = courseRepository.findById(topicUpdateDTO.course_id());

            if(course.isEmpty()){
                throw new IntegrityValidation("Course Not found");
            }
        }

    }
}
