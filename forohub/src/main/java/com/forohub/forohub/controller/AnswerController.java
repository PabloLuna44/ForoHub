package com.forohub.forohub.controller;


import com.forohub.forohub.domain.answer.AnswerNewDTO;
import com.forohub.forohub.domain.answer.AnswerResponseDTO;
import com.forohub.forohub.domain.answer.AnswerService;
import com.forohub.forohub.domain.answer.AnswerUpdatedDTO;
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
@RequestMapping("/answer")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {


    @Autowired
    AnswerService answerService;

    @PostMapping
    @Transactional
    public ResponseEntity<AnswerResponseDTO> create(@RequestBody @Valid AnswerNewDTO answer) {
        return ResponseEntity.ok(answerService.save(answer));
    }


    @GetMapping
    public ResponseEntity<Page<AnswerResponseDTO>> findAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(answerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.findById(id));
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Page<AnswerResponseDTO>> findById(@PathVariable Long id,@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(answerService.findByTopic(id,pageable));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<AnswerResponseDTO> edit(@RequestBody @Valid AnswerUpdatedDTO answer){
        return ResponseEntity.ok(answerService.edit(answer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
//        System.out.println(id);
        answerService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
