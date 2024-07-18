package com.forohub.forohub.controller;

import com.forohub.forohub.domain.course.CourseNewDTO;

import com.forohub.forohub.domain.course.CourseResponseDTO;
import com.forohub.forohub.domain.course.CourseService;
import com.forohub.forohub.domain.course.CourseUpdateDTO;
import com.forohub.forohub.domain.topic.TopicResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/course")
@SecurityRequirement(name = "bearer-key")
public class CourseController {


    @Autowired
    CourseService courseService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Register New Course",
            description="",
            tags = {"course","post"}
    )
    public ResponseEntity<CourseResponseDTO> create(@RequestBody @Valid CourseNewDTO course) {
        return ResponseEntity.ok(courseService.save(course));
    }


    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<CourseResponseDTO> edit(@RequestBody @Valid CourseUpdateDTO course){
        return ResponseEntity.ok(courseService.edit(course));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        System.out.println(id);
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
