package com.forohub.forohub.controller;


import com.forohub.forohub.domain.topic.TopicNewDTO;
import com.forohub.forohub.domain.topic.TopicResponseDTO;
import com.forohub.forohub.domain.topic.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {


    @Autowired
    TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> create(@RequestBody @Valid TopicNewDTO topic) {

            TopicResponseDTO topicResponseDTO=new TopicResponseDTO(topicService.save(topic));
            return ResponseEntity.ok(topicResponseDTO);
    }


}
