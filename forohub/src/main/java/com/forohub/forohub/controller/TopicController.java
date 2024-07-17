package com.forohub.forohub.controller;


import com.forohub.forohub.domain.topic.TopicNewDTO;
import com.forohub.forohub.domain.topic.TopicResponseDTO;
import com.forohub.forohub.domain.topic.TopicService;
import com.forohub.forohub.domain.topic.TopicUpdateDTO;
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
@RequestMapping("/topic")
@SecurityRequirement(name = "bearer-key")
public class TopicController {


    @Autowired
    TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> create(@RequestBody @Valid TopicNewDTO topic) {
            return ResponseEntity.ok(topicService.save(topic));
    }


    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(topicService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Page<TopicResponseDTO>> findByUser(@PageableDefault Pageable pageable,@PathVariable Long id){

        return ResponseEntity.ok(topicService.findByUser(pageable, id));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Page<TopicResponseDTO>> findByCourse(@PageableDefault Pageable pageable,@PathVariable Long id){

        return ResponseEntity.ok(topicService.findByCourse(pageable,id));
    }




    @PutMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> edit(@RequestBody @Valid TopicUpdateDTO topic){

        return ResponseEntity.ok(topicService.edit(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
//        System.out.println(id);
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
