package com.forohub.forohub.domain.topic;

import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.course.CourseRepository;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserRepository;
import com.forohub.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    public Topic save(TopicNewDTO topic){

        Optional<User> user=userRepository.findById(topic.user_id());
        Optional<Course> course=courseRepository.findById(topic.course_id());
        LocalDateTime date=LocalDateTime.now();

        if(user.isEmpty()){
            throw new IntegrityValidation("User Not Found");
        }
        if(course.isEmpty()){
            throw new IntegrityValidation("Course Not Found");
        }

        Topic newTopic=new Topic(topic.title(),topic.message(),date,Status.ANGRY,user.get(),course.get());
        topicRepository.save(newTopic);
        return newTopic;
    }

    public Page<TopicResponseDTO> findAll(Pageable pageable){
        return topicRepository.findAll(pageable).map(TopicResponseDTO::new);
    }



}
