package com.forohub.forohub.domain.topic;

import com.forohub.forohub.domain.course.Course;
import com.forohub.forohub.domain.course.CourseRepository;
import com.forohub.forohub.domain.topic.Validations.TopicValidator;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserRepository;
import com.forohub.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    List<TopicValidator> validations;

    public TopicResponseDTO save(TopicNewDTO topic){

        Optional<User> user=userRepository.findById(topic.userId());
        Optional<Course> course=courseRepository.findById(topic.courseId());
        LocalDateTime date=LocalDateTime.now();

        if(user.isEmpty()){
            throw new IntegrityValidation("User Not Found");
        }
        if(course.isEmpty()){
            throw new IntegrityValidation("Course Not Found");
        }

        Topic newTopic=new Topic(topic.title(),topic.message(),date,user.get(),course.get());


        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(topicRepository.save(newTopic));

        return topicResponseDTO;
    }

    public Page<TopicResponseDTO> findAll(Pageable pageable){
        return topicRepository.findByStatusTrue(pageable).map(TopicResponseDTO::new);
    }

    public TopicResponseDTO findById(Long id){
        Optional<Topic> topic=topicRepository.findByIdAndStatusTrue(id);

        if(topic.isEmpty()){
            throw new IntegrityValidation("Topic Not Found");
        }
        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(topic.get());

        return topicResponseDTO;
    }

    public TopicResponseDTO edit(TopicUpdateDTO topicUpdateDTO){
        if (topicRepository.findById(topicUpdateDTO.id()).isEmpty()){
            throw new IntegrityValidation("Topic Not Found");
        }
        validations.forEach(v-> v.validate(topicUpdateDTO));

        Topic topic=topicRepository.getReferenceById(topicUpdateDTO.id());

        if(topicUpdateDTO.courseId()!=null){
            Optional<Course> course=courseRepository.findById(topicUpdateDTO.courseId());
            topic.update(topicUpdateDTO.title(),topicUpdateDTO.message(),topicUpdateDTO.status(),course.orElse(null));
        }else{
            topic.update(topicUpdateDTO.title(),topicUpdateDTO.message(),topicUpdateDTO.status(),null);
        }

        TopicResponseDTO topicResponseDTO=new TopicResponseDTO(topic);

        return topicResponseDTO;
    }

    public Page<TopicResponseDTO> findByUser(Pageable pageable,Long id){

        Optional<User> user=userRepository.findById(id);

        if(user.isEmpty()){
            throw new IntegrityValidation("User not found");
        }

        return topicRepository.findByUser(pageable,user.get()).map(TopicResponseDTO::new);

    }

    public Page<TopicResponseDTO> findByCourse(Pageable pageable,Long id){

        Optional<Course> course=courseRepository.findById(id);

        if(course.isEmpty()){
            throw new IntegrityValidation("Course not found");
        }

        return topicRepository.findByCourseAndStatusTrue(pageable,course.get()).map(TopicResponseDTO::new);
    }

//    Soft Delete
    public void delete(Long id){

        if (topicRepository.findByIdAndStatusTrue(id).isEmpty()){
            throw new IntegrityValidation("Topic Not Found");
        }

        Topic topic=topicRepository.getReferenceById(id);

        topic.setStatus(false);
    }

//Borrado de la base de datos
//    public void delete(Long id){
//
//        Topic topic=topicRepository.getReferenceById(id);
//        topicRepository.delete(topic);
//
//    }


}
