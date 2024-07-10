package com.forohub.forohub.domain.course;

import com.forohub.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;




    public Course save(CourseNewDTO courseNew){
        Course course= new Course(courseNew);
        return courseRepository.save(course);
    }

    public Page<CourseResponseDTO> findAll(Pageable pageable){

        return courseRepository.findByStatusTrue(pageable).map(CourseResponseDTO::new);
    }

    public Course findById(Long id){

        Optional<Course> course=courseRepository.findByIdAndStatusTrue(id);

        if(course.isEmpty()){
            throw new IntegrityValidation("Course Not Found");
        }
        return course.get();
    }

    public Course edit(CourseUpdateDTO courseUpdateDTO){

        Course course=courseRepository.getReferenceById(courseUpdateDTO.id());

        if(course==null){
            throw new IntegrityValidation("Course Not Found");
        }        course.update(courseUpdateDTO);

        return course;

    }

    //    Soft Delete
    public void delete(Long id){

        Course course=courseRepository.getReferenceById(id);

        if(course==null){
            throw new IntegrityValidation("Course Not Found");
        }

        course.setStatus(false);
    }

//Borrado de la base de datos
//    public void delete(Long id){
//
//        Topic topic=topicRepository.getReferenceById(id);
//        topicRepository.delete(topic);
//
//    }


}
