package com.forohub.forohub.controller;

import com.forohub.forohub.domain.enroll.EnrollNewDTO;
import com.forohub.forohub.domain.enroll.EnrollResponseDTO;
import com.forohub.forohub.domain.enroll.EnrollService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enroll")
@SecurityRequirement(name = "bearer-key")
public class EnrollController {

    @Autowired
    EnrollService enrollService;

    @PostMapping
    public ResponseEntity<EnrollResponseDTO> enrollToCourse(@RequestBody @Valid EnrollNewDTO courseNewDTO){

         EnrollResponseDTO enroll=new EnrollResponseDTO(enrollService.enrollToCourse(courseNewDTO));

        return ResponseEntity.ok(enroll);
    }


    @GetMapping("user/{id}")
    public ResponseEntity<Page<EnrollResponseDTO>> allCourseByUser(@PathVariable Long id,@PageableDefault Pageable pageable ){

        return ResponseEntity.ok(enrollService.allCourseByUser(id,pageable));

    }


}
